package au.com.david.exchange.task;

import com.microsoft.schemas.exchange.services._2006.types.MapiPropertyTypeType;

public class AdditionalProperty {
	public enum Type {ExtendedField, Field};
	
	private String name;
	private MapiPropertyTypeType dataType;
	private Type type;
	
	public AdditionalProperty(Type type, String name, MapiPropertyTypeType dataType) {
		this.type = type;
		this.name = name;
		this.dataType = dataType;
	}
	
	public AdditionalProperty(Type type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public MapiPropertyTypeType getDataType() {
		return dataType;
	}
}
