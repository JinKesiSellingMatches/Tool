package data.common.factory;

import core.exception.ErrorEnum;

public enum DataSourceEnum {
	
	
	GAIA("GAIA","GAIA"),
	
    POSEIDON("POSEIDON","POSEIDON");
	
	private String value;
	
	private String description;
	
	private DataSourceEnum(String value,String description){
		
		this.value=value;
		
		this.description=description;
	}

	public static ErrorEnum valueOfs(Integer value) {
		if (value != null) {
			for (ErrorEnum type : ErrorEnum.values()) {
				if (type.value().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}
	
	public String value() {
		return value;
	}

	public String description() {
		return description;
	}

}
