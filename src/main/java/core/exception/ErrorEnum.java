package core.exception;


public enum ErrorEnum {
	
	ISNULL(00001,"不允许为空"),
	
	NOTHING(-1,"无意义");
	
	private Integer value;
	
	private String description;
	
	private ErrorEnum(Integer value,String description){
		
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
	
	public Integer value() {
		return value;
	}

	public String description() {
		return description;
	}

}