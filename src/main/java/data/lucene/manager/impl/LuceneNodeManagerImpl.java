package data.lucene.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import core.result.ResultHelper;
import data.common.manager.impl.BaseDaoImpl;
import data.lucene.manager.LuceneNodeManager;
import data.lucene.pojo.SaveLuceneRelationshipPOJO;
import data.module.pojo.DataBaseModuleSearchPOJO;

@Service(value="luceneNodeManager")
public class LuceneNodeManagerImpl extends BaseDaoImpl implements LuceneNodeManager {

	@Override
	public void addLucene(SaveLuceneRelationshipPOJO pojos) {
		
		if (pojos!=null) {
			
		}
	}

	@Override
	public ResultHelper CenterProcess(DataBaseModuleSearchPOJO pojo) {
		System.out.println(pojo);
		
		return null;
	}
}
