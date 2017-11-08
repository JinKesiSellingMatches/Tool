package data.common.manager;

import java.util.List;

import data.lucene.entity.LuceneNode;
import data.lucene.pojo.LuceneSerachPOJO;

public interface LuceneDao {
	
	/**
	 * 添加   这里返回是为了日志记录
	 * @param luceneNode
	 * @return
	 */
    public void save(LuceneNode luceneNode) throws Exception;
    
    /**
     * 更新
     * @param luceneNode
     * @param tableId
     * @param tableName
     */
    public void update(LuceneNode luceneNode,String tableId,String tableName) throws Exception;
    
    /**
     * 删除
     * @param tableId
     * @param tableName
     */
    public void delted(String tableId,String tableName) throws Exception;
    
    /**
     * 查询
     * @param pageIndex  页码
     * @param pageSize   每页条数
     * @param search     搜索
     * @return
     * @throws Exception
     */
    public List<LuceneSerachPOJO> find(Integer pageIndex,Integer pageSize,String search) throws Exception;
    
    /**
     * 多字段搜索（and   多字段多内容）
     * @param fields 字段
     * @param content 内容
     * @return
     */
    public LuceneSerachPOJO get(String[] fields,String[] content)throws Exception; 
    
}
