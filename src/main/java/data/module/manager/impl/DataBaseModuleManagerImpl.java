package data.module.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import core.Tool.rocketEQ.POJO.RocketEQContent;
import core.result.ResultHelper;
import data.common.manager.impl.BaseDaoImpl;
import data.lucene.manager.LuceneNodeManager;
import data.module.entity.DataBaseModule;
import data.module.manager.DataBaseModuleManger;
import data.module.pojo.DataBaseModulePOJO;
import data.module.pojo.DataBaseModuleSearchPOJO;

@Service(value="dataBaseModuleManger")
public class DataBaseModuleManagerImpl extends BaseDaoImpl implements DataBaseModuleManger {
	
	@Resource
	private LuceneNodeManager luceneNodeManager;

	@Override
	public DataBaseModule getByClassName(String className) {
		
		if (className!=null) {
			String hql="from DataBaseModule d where d.className=:className ";
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("className", className);
			return this.get(hql, params);
		}
		return null;
	}

	@Override
	public void saveDateBaseModule(DataBaseModulePOJO pojo) {
		
	}

	@Override
	public DataBaseModule getByName(String name) {
		
		if (name!=null) {
			String hql="from DataBaseModule d where d.name=:name ";
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("name", name);
			return this.get(hql, params);
		}
		return null;
	}

	@Override
	public DataBaseModule getByCode(String code) {
		
		if (code!=null) {
			String hql="from DataBaseModule d where d.code=:code ";
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("code", code);
			return this.get(hql, params);
		}
		return null;
	}

	@Override
	public List<DataBaseModule> findByCode(List<String> codes) {
		
		if (codes!=null) {
			String hql="from DataBaseModule d where d.code in (:codes) ";
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("codes", codes);
			List<DataBaseModule> dataBaseModules=this.find(hql, params);
			if (dataBaseModules!=null) {
				return dataBaseModules;
			}
		}
		return new ArrayList<>();
	}

	@Override
	public DataBaseModuleSearchPOJO findModuleSearchInfo(String sql, String id) {
		
		if (sql!=null&&id!=null) {
			Map<String, Object> params=new HashMap<String,Object>();
			params.put("tableId", id);
			DataBaseModuleSearchPOJO pojo=this.getBySql(sql, params, DataBaseModuleSearchPOJO.class);
			if (pojo!=null) {
				return pojo;
			}
		}
		return null;
	}

	@Override
	public ResultHelper sendLucene(RocketEQContent rocketEQContent)throws Exception{
		
		if (rocketEQContent!=null) {
			DataBaseModule dataBaseModule=getByClassName(rocketEQContent.getClassName());
			if (dataBaseModule!=null) {
				DataBaseModuleSearchPOJO pojo=findModuleSearchInfo(dataBaseModule.getSqlContent(),rocketEQContent.getId());
				//对lucene 唯一接口
				luceneNodeManager.CenterProcess(pojo);
			}
		}
		return null;
	}

}
