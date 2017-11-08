package Junit;

import java.util.Date;


import data.common.manager.LuceneDao;
import data.common.manager.impl.LuceneDaoImpl;
import data.lucene.entity.LuceneNode;

public class MainTest {
	
	private static LuceneDaoImpl luceneDaoImpl=new LuceneDaoImpl();
	
	public static void main(String[] args) throws Exception {
		LuceneNode node=new LuceneNode();
		node.setCreateDate(new Date());
		node.setCreateUser("123");
		node.setId("3");
		node.setModule("Module");
		node.setSerach("hao");
		node.setTableId("client");
		node.setTableName("客户");
		node.setTableNameEntity("客户");
		//luceneDaoImpl.save(node);
		//luceneDaoImpl.find(1, 1, "*你*");
		luceneDaoImpl.get(new String[]{"serach"}, new String[]{"*ni* *hao*"});
	}
}
