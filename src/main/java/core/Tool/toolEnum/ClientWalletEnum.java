package core.Tool.toolEnum;

import java.util.ArrayList;
import java.util.List;


/**
 * 客户钱包
 * @author jinx
 *
 */
public class ClientWalletEnum {
	
	/**
	 * 默认result
	 * @author jinx
	 *
	 */
	public enum Result{
		CODE("code", "code"), DATA("data", "data"), TRUE("true", "true"), FALSE("false", "false");
		
		private String value;

		private String description;

		private Result(String value, String description) {

			this.value = value;

			this.description = description;

		}

		public String value() {
			return value;
		}

		public String description() {
			return description;
		}
	}

}
