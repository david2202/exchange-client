package au.com.david.exchange.task;

import au.com.david.exchange.Builder;

import com.microsoft.schemas.exchange.services._2006.messages.DeleteItemType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemType;
import com.microsoft.schemas.exchange.services._2006.types.AffectedTaskOccurrencesType;
import com.microsoft.schemas.exchange.services._2006.types.ItemIdType;
import com.microsoft.schemas.exchange.services._2006.types.NonEmptyArrayOfBaseItemIdsType;

/**
 * Builder for {@link FindItemType} objects.
 * 
 * @author howed
 */
public class DeleteItemBuilder implements Builder<DeleteItem, DeleteItemType> {

	/**
	 * @see au.com.david.exchange.Builder#build(java.lang.Object)
	 */
	public DeleteItemType build(DeleteItem deleteItem) {
		DeleteItemType request = new DeleteItemType();
		request.setDeleteType(deleteItem.getDisposalType());
		request.setAffectedTaskOccurrences(AffectedTaskOccurrencesType.ALL_OCCURRENCES);
		request.setItemIds(buildItemIds(deleteItem));
		return request;
	}

	private NonEmptyArrayOfBaseItemIdsType buildItemIds(DeleteItem deleteItem) {
		NonEmptyArrayOfBaseItemIdsType items = new NonEmptyArrayOfBaseItemIdsType();
		for (String guid : deleteItem.getItemIds()) {
			ItemIdType itemId = new ItemIdType();
			itemId.setId(guid);
			items.getItemIdOrOccurrenceItemIdOrRecurringMasterItemId().add(
					itemId);
		}
		return items;
	}
}
