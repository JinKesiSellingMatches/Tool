package data.Enum;

public enum LoginErrorEnum {

	ERRORNAME("0", "用户名错误"), ERRORDELETED("1", "您的账户已被禁用。"); //订单关闭审核中

	private String value;

	private String description;

	private LoginErrorEnum(String value, String description) {

		this.value = value;

		this.description = description;

	}

	public String value() {
		return value;
	}

	public String description() {
		return description;
	}

	public static LoginErrorEnum valueOfs(String value) {
		if (value != null) {
			for (LoginErrorEnum type : LoginErrorEnum.values()) {
				if (type.value().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}
}
