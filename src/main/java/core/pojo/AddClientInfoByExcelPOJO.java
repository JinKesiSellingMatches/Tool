package core.pojo;

import java.math.BigDecimal;

/**
 * excel数据导入
 * @author jinx
 *
 */
public class AddClientInfoByExcelPOJO {
	
	/**
	 * id code ,预付款百分比,账单日，付款周期， 结帐方式，下蛋前，发货前，签收，票到
	 */
	private String id,code,billDay,paymentPeriod,settleType,beforeOrderAp,beforeShipmentsAp,signInAp,ticketAp;
	
	/**
	 * 长期额度
	 */
	private BigDecimal limitMoney;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBillDay() {
		return billDay;
	}

	public void setBillDay(String billDay) {
		this.billDay = billDay;
	}

	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

    
    public String getBeforeOrderAp() {
    
        return beforeOrderAp;
    }

    
    public void setBeforeOrderAp(String beforeOrderAp) {
    
        this.beforeOrderAp = beforeOrderAp;
    }

    
    public String getBeforeShipmentsAp() {
    
        return beforeShipmentsAp;
    }

    
    public void setBeforeShipmentsAp(String beforeShipmentsAp) {
    
        this.beforeShipmentsAp = beforeShipmentsAp;
    }

    
    public String getSignInAp() {
    
        return signInAp;
    }

    
    public void setSignInAp(String signInAp) {
    
        this.signInAp = signInAp;
    }

    
    public String getTicketAp() {
    
        return ticketAp;
    }

    
    public void setTicketAp(String ticketAp) {
    
        this.ticketAp = ticketAp;
    }

	public BigDecimal getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(BigDecimal limitMoney) {
		this.limitMoney = limitMoney;
	}

}
