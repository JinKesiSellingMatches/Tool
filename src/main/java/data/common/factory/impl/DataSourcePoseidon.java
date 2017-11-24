package data.common.factory.impl;

import javax.annotation.Resource;

import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.exception.ToolException;
import core.result.ResultHelper;
import data.common.factory.DataSourceInterface;
import data.common.manager.BaseGaiaDao;

public class DataSourcePoseidon implements DataSourceInterface {

	@Resource
	private BaseGaiaDao baseGaiaDao;
	
	@Override
	public ResultHelper dataSource(RocketEQContentPOJO pojo) throws ToolException {
		return null;
	}

}
