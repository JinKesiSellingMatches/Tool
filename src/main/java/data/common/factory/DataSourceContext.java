package data.common.factory;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.exception.ToolException;
import core.result.ResultHelper;


@Service("OrderContext")
public class DataSourceContext {

	private DataSourceInterface orderInterface;

	public ResultHelper dataSource(RocketEQContentPOJO pojo) throws ToolException {

		orderInterface = DataSourceFactory.getInstance().creator(pojo.getSource());

		return orderInterface.dataSource(pojo);

	}

	public DataSourceInterface getStrategy() {

		return orderInterface;

	}

	public void setStrategy(DataSourceInterface orderInterface) {

		this.orderInterface = orderInterface;

	}

	public static void main(String[] args) throws ParseException {
		DataSourceContext reportEntityContext = new DataSourceContext();
		//OrderDTO orderDTO = new OrderDTO();
		//reportEntityContext.orderSplit(OrderSplitProduceEnum.ORDERSPLITONE.value(), orderDTO, null);
	}

}
