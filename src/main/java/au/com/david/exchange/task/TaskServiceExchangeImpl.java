package au.com.david.exchange.task;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.Holder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import au.com.david.exchange.Builder;
import au.com.david.exchange.ExtendedExchangeServicePortType;

import com.microsoft.schemas.exchange.services._2006.messages.CreateItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.CreateItemType;
import com.microsoft.schemas.exchange.services._2006.messages.DeleteItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.DeleteItemType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemResponseMessageType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemType;
import com.microsoft.schemas.exchange.services._2006.messages.ItemInfoResponseMessageType;
import com.microsoft.schemas.exchange.services._2006.messages.ResponseMessageType;
import com.microsoft.schemas.exchange.services._2006.types.ArrayOfRealItemsType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdNameType;
import com.microsoft.schemas.exchange.services._2006.types.ExchangeVersionType;
import com.microsoft.schemas.exchange.services._2006.types.ItemType;
import com.microsoft.schemas.exchange.services._2006.types.RequestServerVersion;
import com.microsoft.schemas.exchange.services._2006.types.ServerVersionInfo;
import com.microsoft.schemas.exchange.services._2006.types.TaskType;

/**
 * Implementation of the {@link TaskService} backed by Microsoft Exchange.
 * 
 * @author howed
 */
@SuppressWarnings("restriction")
@Service
public class TaskServiceExchangeImpl implements TaskService, InitializingBean {
	private ExtendedExchangeServicePortType portType;
	private Builder<Task, CreateItemType> createItemBuilder;
	private Builder<FindItem, FindItemType> findItemBuilder;
	private Builder<DeleteItem, DeleteItemType> deleteItemBuilder;

	/**
	 * @see au.com.david.exchange.task.TaskService#createTask(au.com.david.exchange.task.Task)
	 */
	public String createTask(Task exchangeTask) {
		CreateItemType request = createItemBuilder.build(exchangeTask);

		RequestServerVersion requestVersion = new RequestServerVersion();
		requestVersion.setVersion(ExchangeVersionType.EXCHANGE_2010_SP_1);
		final Holder<CreateItemResponseType> createItemResult = new Holder<CreateItemResponseType>();
		final Holder<ServerVersionInfo> serverVersion = new Holder<ServerVersionInfo>();

		portType.createItem(request, requestVersion, createItemResult,
				serverVersion);

		JAXBElement<? extends ResponseMessageType> responseMessages = createItemResult.value
				.getResponseMessages()
				.getCreateItemResponseMessageOrDeleteItemResponseMessageOrGetItemResponseMessage()
				.get(0);
		ItemType item = ((ItemInfoResponseMessageType) responseMessages
				.getValue()).getItems().getItemOrMessageOrCalendarItem().get(0);
		return item.getItemId().getId();
	}

	public List<TaskType> findItems(FindItem findItem) {
		FindItemType request = findItemBuilder.build(findItem);
		RequestServerVersion requestVersion = new RequestServerVersion();
		requestVersion.setVersion(ExchangeVersionType.EXCHANGE_2010_SP_1);

		final Holder<FindItemResponseType> findItemResult = new Holder<FindItemResponseType>();
		final Holder<ServerVersionInfo> serverVersion = new Holder<ServerVersionInfo>();

		portType.findItem(request, requestVersion, findItemResult,
				serverVersion);

		JAXBElement<? extends ResponseMessageType> responseMessages = findItemResult.value
				.getResponseMessages()
				.getCreateItemResponseMessageOrDeleteItemResponseMessageOrGetItemResponseMessage()
				.get(0);
		ArrayOfRealItemsType items = ((FindItemResponseMessageType) responseMessages
				.getValue()).getRootFolder().getItems();
		List<TaskType> result = new ArrayList<TaskType>();
		for (ItemType item : items.getItemOrMessageOrCalendarItem()) {
			if (item instanceof TaskType) {
				result.add((TaskType) item);
			}
		}
		return result;
	}

	public void deleteItems(DeleteItem deleteItem) {
		DeleteItemType request = deleteItemBuilder.build(deleteItem);

		RequestServerVersion requestVersion = new RequestServerVersion();
		requestVersion.setVersion(ExchangeVersionType.EXCHANGE_2010_SP_1);

		final Holder<DeleteItemResponseType> deleteItemResult = new Holder<DeleteItemResponseType>();
		final Holder<ServerVersionInfo> serverVersion = new Holder<ServerVersionInfo>();

		portType.deleteItem(request, requestVersion, deleteItemResult,
				serverVersion);
	}

	public boolean taskExists(String id) {
		FindItem findItem = new FindItem(DistinguishedFolderIdNameType.TASKS);
		List<TaskType> tasks = findItems(findItem);
		for (TaskType task : tasks) {
			if (task.getItemId().getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void setPortType(ExtendedExchangeServicePortType portType) {
		this.portType = portType;
	}

	public void setCreateItemBuilder(
			Builder<Task, CreateItemType> createItemBuilder) {
		this.createItemBuilder = createItemBuilder;
	}

	public void setFindItemBuilder(
			Builder<FindItem, FindItemType> findItemBuilder) {
		this.findItemBuilder = findItemBuilder;
	}

	public void setDeleteItemBuilder(
			Builder<DeleteItem, DeleteItemType> deleteItemBuilder) {
		this.deleteItemBuilder = deleteItemBuilder;
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		if (portType == null) {
			throw new IllegalStateException("portType has not been initialised");
		}
		if (createItemBuilder == null) {
			throw new IllegalStateException(
					"createItemBuilder has not been initialised");
		}
		if (findItemBuilder == null) {
			throw new IllegalStateException(
					"findItemBuilder has not been initialised");
		}
		if (deleteItemBuilder == null) {
			throw new IllegalStateException(
					"deleteItemBuilder has not been initialised");
		}
	}
}
