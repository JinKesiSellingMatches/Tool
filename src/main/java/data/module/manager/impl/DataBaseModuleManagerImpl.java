package data.module.manager.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import core.Tool.rocketEQ.RocketEQContentPOJO;
import core.exception.ErrorEnum;
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
	
	private static int two=2;

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
	public ResultHelper sendLucene(RocketEQContentPOJO rocketEQContent)throws Exception{
		
		ResultHelper result=new ResultHelper();
		if (rocketEQContent!=null) {
			DataBaseModule dataBaseModule=getByClassName(rocketEQContent.getClassName());
			if (dataBaseModule!=null) {
				//删除区别于新增和删除
				DataBaseModuleSearchPOJO pojo=new DataBaseModuleSearchPOJO();
				if (rocketEQContent.getType()==-1) {
					pojo.setId(rocketEQContent.getTableId());
					pojo.setModuleCode(dataBaseModule.getCode());
				}else {
					pojo=findModuleSearchInfo(dataBaseModule.getSqlContent(),rocketEQContent.getTableId());
					pojo.setType(rocketEQContent.getType());
					pojo.setCreateUser(rocketEQContent.getOperatingUser());
					pojo.setModuleCode(dataBaseModule.getCode());
					
					//唯一检查接口
					result=checkDataBaseModuleSearchPOJOData(pojo);
				}
				
				//对lucene 唯一接口
				luceneNodeManager.CenterProcess(pojo);
			}
		}
		return null;
	}

	@Override
	public ResultHelper checkDataBaseModuleSearchPOJOData(DataBaseModuleSearchPOJO baseModuleSearchPOJO) {
		ResultHelper result=new ResultHelper();
		try {
			result=checkNull(baseModuleSearchPOJO);
		} catch (IllegalArgumentException e) {
			//TODO 日志异常处理
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//TODO 日志异常处理
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultHelper checkNull(DataBaseModuleSearchPOJO dataBaseModuleSearchPOJO) throws IllegalArgumentException, IllegalAccessException {
		 
		ResultHelper result=new ResultHelper();
		Class clasz=dataBaseModuleSearchPOJO.getClass();
		Field[] fields=clasz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field=fields[i];
			field.setAccessible(true);
			if (field.get(dataBaseModuleSearchPOJO)==null) {
				result.setCode(ErrorEnum.ISNULL.value());
			}
		}
		return null;
	}

}
