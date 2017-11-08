package core.Tool.toolEnum;

public enum ReadExcelEnum {

	CODE("0","code"),
	DATAS("0","data"), 
	TRUE("1", "true"),
	ERROR("-1","错误"),
	DOWNLOAD("-2","download"),
	DOWNLOADINFO("-2","已生成上传信息。请点击查看"),
	LISTS("-2","LISTS"),
	NAME("name","商品名称"),
	TEXT("text","商品信息"),
	REMARK("remark","商品备注"),
	type("type","类型"),
	STANDARDPRODUCTLABELDTOLIST("standardProductLabelDTOList","配材"),
	STANDARDPRODUCTDETAILDTOLIST("standardProductLabelDTOList","配材"),
	STANDARDPRODUCTLABELVALUE1("standardProductLabelValue1","配材一"),
	STANDARDPRODUCTLABELVALUE2("standardProductLabelValue2","配材二"),
	STANDARDPRODUCTLABELVALUE3("standardProductLabelValue3","配材三"),
	LENGTH("length","长"),
	WEIDTH("weidth","宽"),
	HEIGTH("heigth","高"),
	PRICE("price","金额");
	

	private String value;

	private String description; 
	
	

	private ReadExcelEnum(String value, String description) {

		this.value = value;

		this.description = description;

	}

	public String value() {
		return value;
	}

	public String description() {
		return description;
	}

	public static ReadExcelEnum valueOfs(String value) {
		if (value != null) {
			for (ReadExcelEnum type : ReadExcelEnum.values()) {
				if (type.value().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(ReadExcelEnum.valueOfs("price").description);
	}

}
