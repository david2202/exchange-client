package au.com.david.exchange.task;

import au.com.david.exchange.Builder;

import com.microsoft.schemas.exchange.services._2006.messages.CreateItemType;
import com.microsoft.schemas.exchange.services._2006.types.ArrayOfStringsType;
import com.microsoft.schemas.exchange.services._2006.types.BodyType;
import com.microsoft.schemas.exchange.services._2006.types.BodyTypeType;
import com.microsoft.schemas.exchange.services._2006.types.ImportanceChoicesType;
import com.microsoft.schemas.exchange.services._2006.types.NonEmptyArrayOfAllItemsType;
import com.microsoft.schemas.exchange.services._2006.types.TaskStatusType;
import com.microsoft.schemas.exchange.services._2006.types.TaskType;

/**
 * Builder for {@link CreateItemType} objects from {@link Task}s.
 * 
 * @author howed
 */
public class CreateItemBuilder implements Builder<Task, CreateItemType> {

	/**
	 * @see au.com.david.exchange.Builder#build(java.lang.Object)
	 */
	public CreateItemType build(Task exchangeTask) {
		CreateItemType request = new CreateItemType();
		NonEmptyArrayOfAllItemsType items = new NonEmptyArrayOfAllItemsType();
		TaskType task = buildTask(exchangeTask);
		items.getItemOrMessageOrCalendarItem().add(task);
		request.setItems(items);

		return request;
	}

	private TaskType buildTask(Task exchangeTask) {
		TaskType task = new TaskType();
		task.setSubject(exchangeTask.getSubject());
		task.setBody(buildBody(exchangeTask));
		task.setCategories(buildCategories(exchangeTask));
		task.setImportance(ImportanceChoicesType.fromValue(exchangeTask
				.getImportance().toString()));
		task.setStatus(TaskStatusType.fromValue(exchangeTask.getStatus()
				.toString()));
		return task;
	}

	private ArrayOfStringsType buildCategories(Task exchangeTask) {
		ArrayOfStringsType categories = new ArrayOfStringsType();
		categories.getString().addAll(exchangeTask.getCategories());
		return categories;
	}

	private BodyType buildBody(Task exchangeTask) {
		BodyType body = new BodyType();
		body.setBodyType(BodyTypeType.fromValue(exchangeTask.getBodyType()
				.toString()));
		body.setValue(exchangeTask.getBody());
		return body;
	}
}
