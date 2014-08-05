package au.com.david.exchange.task;

import java.util.List;

import au.com.david.exchange.Builder;
import au.com.david.exchange.task.AdditionalProperty.Type;

import com.microsoft.schemas.exchange.services._2006.messages.FindItemType;
import com.microsoft.schemas.exchange.services._2006.types.DefaultShapeNamesType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdNameType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedPropertySetType;
import com.microsoft.schemas.exchange.services._2006.types.ItemQueryTraversalType;
import com.microsoft.schemas.exchange.services._2006.types.ItemResponseShapeType;
import com.microsoft.schemas.exchange.services._2006.types.NonEmptyArrayOfBaseFolderIdsType;
import com.microsoft.schemas.exchange.services._2006.types.NonEmptyArrayOfPathsToElementType;
import com.microsoft.schemas.exchange.services._2006.types.PathToExtendedFieldType;
import com.microsoft.schemas.exchange.services._2006.types.PathToUnindexedFieldType;
import com.microsoft.schemas.exchange.services._2006.types.UnindexedFieldURIType;

/**
 * Builder for {@link FindItemType} objects.
 * 
 * @author howed
 */
public class FindItemBuilder implements Builder<FindItem, FindItemType> {

	/**
	 * @see au.com.david.exchange.Builder#build(java.lang.Object)
	 */
	public FindItemType build(FindItem findItem) {
		FindItemType request = new FindItemType();
		request.setItemShape(buildItemShape(findItem.getAdditionalProperties()));
		request.setParentFolderIds(buildParentFolderIds(findItem.getFolder()));
		request.setTraversal(ItemQueryTraversalType.SHALLOW);
		return request;
	}

	private ItemResponseShapeType buildItemShape(
			List<AdditionalProperty> properties) {
		ItemResponseShapeType itemShape = new ItemResponseShapeType();
		itemShape.setBaseShape(DefaultShapeNamesType.ALL_PROPERTIES);
		if (properties != null) {
			itemShape
					.setAdditionalProperties(buildAdditionalProperties(properties));
		}
		return itemShape;
	}

	private NonEmptyArrayOfPathsToElementType buildAdditionalProperties(
			List<AdditionalProperty> additionalProperties) {
		NonEmptyArrayOfPathsToElementType properties = new NonEmptyArrayOfPathsToElementType();
		for (AdditionalProperty additionalProperty : additionalProperties) {
			if (additionalProperty.getType().equals(Type.ExtendedField)) {
				PathToExtendedFieldType path = new PathToExtendedFieldType();
				path.setDistinguishedPropertySetId(DistinguishedPropertySetType.PUBLIC_STRINGS);
				path.setPropertyName(additionalProperty.getName());
				path.setPropertyType(additionalProperty.getDataType());
				properties.getPathOrFieldURIOrIndexedFieldURI().add(path);
			} else if (additionalProperty.getType().equals(Type.Field)) {
				PathToUnindexedFieldType path = new PathToUnindexedFieldType();
				path.setFieldURI(UnindexedFieldURIType.TASK_STATUS);
			}
		}
		return properties;
	}

	private NonEmptyArrayOfBaseFolderIdsType buildParentFolderIds(
			DistinguishedFolderIdNameType distinguishedFolderIdNameType) {
		NonEmptyArrayOfBaseFolderIdsType folders = new NonEmptyArrayOfBaseFolderIdsType();
		DistinguishedFolderIdType folderId = new DistinguishedFolderIdType();
		folderId.setId(distinguishedFolderIdNameType);
		folders.getFolderIdOrDistinguishedFolderId().add(folderId);
		return folders;
	}
}
