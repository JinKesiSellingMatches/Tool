package data.common.factory;


import core.Tool.rocketEQ.RocketEQContentPOJO;
import core.exception.ToolException;
import core.result.ResultHelper;


public interface DataSourceInterface {

	/**
	 * 对数据源进行选择
	 * @param pojo
	 * @return
	 * @throws ToolException
	 */
	public ResultHelper dataSource(RocketEQContentPOJO pojo) throws ToolException,Exception;

}
