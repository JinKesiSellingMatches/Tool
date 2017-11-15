package data.lucene.manager.impl;


import java.util.UUID;

import org.springframework.stereotype.Service;

import core.result.ResultHelper;
import data.common.manager.impl.LuceneDaoImpl;
import data.lucene.entity.LuceneNode;
import data.lucene.manager.LuceneNodeManager;
import data.module.pojo.DataBaseModuleSearchPOJO;

@Service(value="luceneNodeManager")
public class LuceneNodeManagerImpl extends LuceneDaoImpl implements LuceneNodeManager {
	
    private static int zero=0;
	
	private static int one=1;
	
	private static int two=2;
	
	/**
	 * 被查询的字段
	 */
	static String[] fieldsUpdate={"tableId","moduleCode"};

	@Override
	public void addLucene(LuceneNode node) throws Exception {
		
		this.save(node);
	}

	@Override
	public ResultHelper CenterProcess(DataBaseModuleSearchPOJO pojo) {
		
		//TODO 必须重构代码 
		ResultHelper result=new ResultHelper();
		try {
			if (pojo.getType()==zero) {
				//添加
				addLucene(dataBaseModuleSearchPOJOToLuceneNode(pojo));
			}else if (pojo.getType()==one) {
				//修改
				updateLucene(dataBaseModuleSearchPOJOToLuceneNode(pojo));
			}else if (pojo.getType()==two) {
				//删除
				deletedLucene(dataBaseModuleSearchPOJOToLuceneNode(pojo));
			}
		} catch (Exception e) {
			//TODO 日志记录 在操作Lucene时异常记录
		}
		return null;
	}

	@Override
	public void updateLucene(LuceneNode node) throws Exception {
		this.update(node, node.getTableId(), node.getModuleCode());
	}

	@Override
	public void deletedLucene(LuceneNode node) throws Exception {
		this.delted(node.getTableId(), node.getModuleCode());
	}

	@Override
	public LuceneNode dataBaseModuleSearchPOJOToLuceneNode(DataBaseModuleSearchPOJO pojo) {
		
		if (pojo!=null) {
			LuceneNode node=new LuceneNode();
			node.setContent(pojo.getContent());
			node.setSerach(pojo.getSearch());
			node.setTableId(pojo.getId());
			node.setTitle(pojo.getTitle());
			node.setCreateDate(pojo.getCreateDate());
			
			node.setCreateUser(pojo.getCreateUser());
			node.setModuleCode(pojo.getModuleCode());
			node.setId(UUID.randomUUID().toString());
			return node;
		}
		return null;
	}
}
