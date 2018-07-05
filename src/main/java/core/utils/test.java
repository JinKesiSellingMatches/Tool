package core.utils;

import java.math.BigDecimal;
import java.util.List;

import com.currencycloud.client.CurrencyCloudClient;
import com.currencycloud.client.model.Currency;
import com.currencycloud.client.model.DetailedRate;

public class test {
	
	public static void main(String[] args) {
    	String appid="hyl045512@163.com";
		String appKey="caa4d404a58f7b4f4f4294a37176dc5143e321c445bfa943554ea6f8577c80e9";
		CurrencyCloudClient client=new CurrencyCloudClient(CurrencyCloudClient.Environment.demo,appid,appKey);
		//System.out.println(client.beneficiaryRequiredDetails("GBP", "GB", "GB"));
		client.authenticate();
		List<Currency> currencies = client.currencies();
		DetailedRate detailedRates = client.detailedRates("GBP", "USD", "buy", new BigDecimal("10000"), null);
		System.out.println(currencies);
		System.out.println(client);
	}

}
