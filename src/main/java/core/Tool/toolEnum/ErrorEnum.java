package core.Tool.toolEnum;


/**
 * 销售订单的错误编码从10017~10099
 * 商品保存的错误编码10100~10199
 * 以下为商品价格计算10200~10299
 * @author hutao
 *
 */
public enum ErrorEnum {
	
	ISNULL("10000", "缺少必须填写的内容。"),
	GTZERO("data", "请填写大于零的金额。"),
	ITZERO("data", "请填写小于零的金额"),
	NEZERO("data", "请填写不等于零的金额"),
	NOTYPE("10001","错误的类型"),
	BAN_REVOCATION("10002","该条数据已被操作，无法撤回！"),
	BAN_UPDATE("10003","不允许修改！"),
	BAN_CHECK("10004","已撤销,不允许审核"),
	ERROR_STATE("10005","错误的状态"),
	BAN_DELETED("10006","无法删除！"),
	ERROR("10007","错误"),
	GO_ADMIN("10008","请联系管理员"),
	PERCENT_SECTION("10009","超出范围"),
	BAN_ADD_CLIENT_ADVANCEPAYMENT("10010","已经存在待审核的预付款调整信息，请等待结果！"),
	BAN_THIRTYDAY_ADD("10011","由于您上次提交信息被退回，请等待30天，剩余天数："),
	DEBIT_WITHIN_90_DAYS("10012","您提交申请通过或退回的，近90日内不允许再提交申请，剩余天数："),
	DEBIT_WITHIN_30_DAYS("10013","您提交申请通过或退回的，近30日内不允许再提交申请，剩余天数："),
	BAN_CLIENT_LIMIT_MONEY_ADD_TYPE_1("10014","已经存在待审核的长期额度调整信息，请等待结果！"),
	BAN_CLIENT_LIMIT_MONEY_ADD_TYPE_0("10015","已经存在待审核的临时额度调整信息，请等待结果！"),
	ERROR_DATA("10016","错误的数据,请联系管理员：客户编码为："),
	//---------------------------以下为下单信息----------------------邪恶的分割线--------------
	ORDER_GOORDER_OVERDUE("10017","您好：该客户已逾期，请尽快催款，避免影响采购进度！逾期天数："),
	ORDER_GOORDER_SERIOUS_OVERDUE("10018","您好：该客户已严重逾期，请尽快催款，避免影响采购进度！逾期天数："),
	ORDER_BALANCE_OF_ORDER("10019","该客户可用额度不足，请追加预付款金额或调整信用额度，避免影响采购进度！"),
	ORDER_UPDATE_BALANCE_OF_ORDER("10020","该客户可用额度不足，请追加预付款金额或调整信用额度！"),
	ORDER_UPDATE_ISEFFECTIVE_BY_ORDER_ID("10021","更新带分配订单错误。"),
	ORDER_BATCH_UPDATE_CLIENT_BILL_PRODUCT_BY_ORDER("10022","订单修改，更新客户对账商品的数量统计信息错误。"),
	ORDER_BATCH_ADD_CLIENT_BILL_PRODUCT_BY_ORDER("10023","订单新增，添加客户对账商品的数量统计信息错误。"),
	
	ORDER_SAVE_YISIDE_WAREHOUSE_ERROR("10024","销售订单添加Yiside仓库信息错误。"),
	ORDER_SAVE_ACTIVITY_ERROR("10025","销售订单保存活动信息错误。"),

	ORDER_SPLIT_BOM_NOT_EQUAL_META("10026","主件商品价格与元件商品价格之和不等，请修改bom价格。"),
	ORDER_UN_KNOWN("10027","操作异常。"),
	
	//---------------------------以下为商品保存----------------------邪恶的分割线--------------
	SAVE_PRODUCT_PRICE_ERROR("10100","保存商品时，商品对应的仓库价格保存错误."),
	SAVE_PRODUCT_PRICE_WAREHOUSE_MISMATCHING_ERROR("10101","错误的仓库信息"),
	SAVE_PRODUCT_PRICE_FROM_WAREHOUSE_ERROR("10102","金额保存失败"),
	UPDATE_WAREHOUSE_ERROR("10103","更新仓库信息失败"),
	YISIDE_TYPE_CODE_OBTAIN_FAIL("10104","yiside编码获取失败"),
	ERROR_IN_KNIFE_LAYOUT_ID("10105","上传的刀版图错误。"),
	//---------------------------以下为商品价格计算------------------非常邪恶的分割线----------
	PRODUCT_PRICE_REGION("10200", "没有找到仓库所属大区"),
	PRODUCT_PRICE_PRICE("10201", "没有找到商品的指定区域的标准价格"),
	
	//----------------------------邪恶的分割线--------------
	NOTHING("-1","什么都没有,没有实际作用请勿调用！");
	
	private String value;

	private String description;

	private ErrorEnum(String value, String description) {

		this.value = value;

		this.description = description;

	}

	public String value() {
		return value;
	}

	public String description() {
		return description;
	}

	public static ErrorEnum valueOfs(String value) {
		if (value != null) {
			for (ErrorEnum type : ErrorEnum.values()) {
				if (type.value().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}
