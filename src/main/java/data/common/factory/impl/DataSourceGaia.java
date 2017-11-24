package data.common.factory.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.exception.ToolException;
import core.result.ResultHelper;
import core.springSupport.utils.SpringHelper;
import data.common.factory.DataSourceInterface;
import data.common.manager.BaseGaiaDao;
import data.lucene.manager.LuceneNodeManager;
import data.module.entity.DataBaseModule;
import data.module.manager.DataBaseModuleManger;
import data.module.pojo.DataBaseModuleSearchPOJO;

public class DataSourceGaia implements DataSourceInterface {
	
	DataBaseModuleManger dataBaseModuleManger = (DataBaseModuleManger) SpringHelper.getBean("dataBaseModuleManger");
	
	LuceneNodeManager luceneNodeManager = (LuceneNodeManager) SpringHelper.getBean("luceneNodeManager");
	
	BaseGaiaDao baseGaiaDao = (BaseGaiaDao) SpringHelper.getBean("baseGaiaDao");
	
	@Override
	public ResultHelper dataSource(RocketEQContentPOJO pojo) throws ToolException {
		
		ResultHelper result=new ResultHelper();
		if (pojo!=null) {
			DataBaseModule dataBaseModule=dataBaseModuleManger.getByClassName(pojo.getClassName());
			if (dataBaseModule!=null) {
				//删除区别于新增和删除
				DataBaseModuleSearchPOJO searchPOJO=new DataBaseModuleSearchPOJO();
				if (pojo.getType()==-1) {
					searchPOJO.setId(pojo.getTableId());
					searchPOJO.setModuleCode(dataBaseModule.getCode());
				}else {
					searchPOJO=findModuleSearchInfo(dataBaseModule.getSqlContent(),pojo.getTableId());
					searchPOJO.setType(pojo.getType());
					searchPOJO.setCreateUser(pojo.getOperatingUser());
					searchPOJO.setModuleCode(dataBaseModule.getCode());
					
					//唯一检查接口
					result=dataBaseModuleManger.checkDataBaseModuleSearchPOJOData(searchPOJO);
				}
				//对lucene 唯一接口
				luceneNodeManager.CenterProcess(searchPOJO);
			}
		}
		return result;
	}
	
	
     public DataBaseModuleSearchPOJO findModuleSearchInfo(String sql, String id) {
		
		if (sql!=null&&id!=null) {
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("tableId", id);
			DataBaseModuleSearchPOJO pojo=baseGaiaDao.getBySql(sql, params, DataBaseModuleSearchPOJO.class);
			if (pojo!=null) {
				return pojo;
			}
		}
		return null;
	}

}
