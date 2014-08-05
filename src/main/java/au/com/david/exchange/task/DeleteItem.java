package au.com.david.exchange.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.microsoft.schemas.exchange.services._2006.types.DisposalType;

public class DeleteItem {
	private List<String> itemIds;
	private DisposalType disposalType;

	public DeleteItem(List<String> itemIds, DisposalType disposalType) {
		this.itemIds = Collections.unmodifiableList(itemIds);
		this.disposalType = disposalType;
	}

	public DeleteItem(String itemId, DisposalType disposalType) {
		List<String> itemIds = new ArrayList<String>();
		itemIds.add(itemId);
		this.itemIds = Collections.unmodifiableList(itemIds);
		this.disposalType = disposalType;
	}

	public List<String> getItemIds() {
		return itemIds;
	}

	public DisposalType getDisposalType() {
		return disposalType;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(disposalType).append(itemIds)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof DeleteItem) {
			DeleteItem other = (DeleteItem) obj;
			return new EqualsBuilder()
					.append(this.disposalType, other.disposalType)
					.append(this.itemIds, other.itemIds).isEquals();
		} else {
			return false;
		}
	}
}
