package au.com.david.exchange.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdNameType;

public class FindItem {
	DistinguishedFolderIdNameType folder;
	List<AdditionalProperty> additionalProperties;

	public FindItem(DistinguishedFolderIdNameType folder) {
		this.folder = folder;
		this.additionalProperties = null;
	}

	public FindItem(DistinguishedFolderIdNameType folder,
			List<AdditionalProperty> additionalProperties) {
		this.folder = folder;
		this.additionalProperties = Collections
				.unmodifiableList(additionalProperties);
	}

	public FindItem(DistinguishedFolderIdNameType folder,
			AdditionalProperty additionalProperty) {
		this.folder = folder;
		List<AdditionalProperty> additionalProperties = new ArrayList<AdditionalProperty>();
		additionalProperties.add(additionalProperty);
		this.additionalProperties = Collections
				.unmodifiableList(additionalProperties);
	}

	public DistinguishedFolderIdNameType getFolder() {
		return folder;
	}

	public List<AdditionalProperty> getAdditionalProperties() {
		return additionalProperties;
	}
}
