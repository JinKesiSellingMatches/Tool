package core.exception;


public class ToolException extends RuntimeException {
	
	
private static final long serialVersionUID = 4313405318821067543L;
	
	private Integer errorCode;

	public ToolException(ErrorEnum errorEnum) {
		this(errorEnum.description(), errorEnum.value());
	}


	public ToolException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ToolException(String message, Integer errorCode, Exception e) {
        super(message, e);
        this.errorCode=errorCode;
	}


	public Integer getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
