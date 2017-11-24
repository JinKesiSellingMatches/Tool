package data.common.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import core.springSupport.utils.SpringHelper;
import data.common.factory.impl.DataSourceGaia;
import data.common.factory.impl.DataSourcePoseidon;

public class DataSourceFactory {
	

	private static DataSourceFactory factory = new DataSourceFactory();

	private DataSourceFactory() {

	}

	private static Map<Object, Object> strategyMap = new HashMap<Object, Object>();

	static {

		/**
		 * GAIA数据源
		 */
		strategyMap.put(DataSourceEnum.GAIA.value(), new DataSourceGaia());
		/**
		 * 波塞冬数据源
		 */
		strategyMap.put(DataSourceEnum.POSEIDON.value(), new DataSourcePoseidon());

	}

	public DataSourceInterface creator(String type) {

		return (DataSourceInterface) strategyMap.get(type);
	}

	public static DataSourceFactory getInstance() {

		return factory;
	}
}
