package data.common.manager.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.MessageBodyWriter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import core.utils.FileUtils;
import data.common.manager.LuceneDao;
import data.lucene.entity.LuceneNode;
import data.lucene.pojo.LuceneSerachPOJO;

/**
 *对lucene  索引操作
 * @author hutao
 *
 */
public class LuceneDaoImpl implements LuceneDao {
	
	static String indexPath="E:/lucene/index04";
	
	private String searchFiled="serach";
	
	private Integer ONE=1;
	
	private Integer HUNDRED=100;
	
	static String SERACH="serach";
	
	static String[] fieldsUpdate={"tableId","moduleCode"};
	
	static String ID="id";
	
	private FileUtils fileUtils=new FileUtils();
	
	/**
	 * 初始化写入
	 * @return
	 * @throws Exception
	 */
	private IndexWriter  getWriter() throws Exception{
		
//		boolean flag=fileUtils.isEmptyFolder(indexPath);
//		if (flag) {
//			return null;
//		}
    	Directory directory = FSDirectory.open(new File(indexPath));  
        Analyzer analyzer = new StandardAnalyzer();  
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_4,analyzer);  
        conf.setOpenMode(OpenMode.CREATE_OR_APPEND);  
        conf.setMaxBufferedDocs(100); 
		return new IndexWriter(directory, conf);
	}
	
	/**
	 * 初始化读取
	 * @return
	 * @throws Exception
	 */
	private IndexSearcher getSearcher() throws Exception {
		boolean flag=fileUtils.isEmptyFolder(indexPath);
		if (flag) {
			return null;
		}
    	Directory directory = FSDirectory.open(new File(indexPath));
        IndexReader reader = DirectoryReader.open(directory);  
        IndexSearcher searcher = new IndexSearcher(reader); 
        return searcher;  
    }
	
	private LuceneSerachPOJO documentToPojo(Document doc) throws Exception{
		LuceneSerachPOJO pojo=new LuceneSerachPOJO();
		Class luClass = pojo.getClass();
		java.lang.reflect.Field[] fields = luClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			java.lang.reflect.Field f = fields[i];
			f.setAccessible(true);
			f.set(pojo, doc.get(f.getName()));
		}
		return pojo;
	}
	
	/**
	 * 分页
	 * @param pageIndex 页码
	 * @param pageSize  每页条数
	 * @param query    query
	 * @param searcher 查询字段
	 * @return  上一页最后一个结果
	 * @throws Exception  异常
	 */
    private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher) throws Exception {
        if(pageIndex==1)return null;//如果是第一页就返回空
        int num = pageSize*(pageIndex-1);//获取上一页的数量
        TopDocs tds = searcher.search(query, num);
        return tds.scoreDocs[num-1];
    }

	@Override
	public void save(LuceneNode luceneNode) throws Exception {
		
		String[] constents={luceneNode.getTableId(),luceneNode.getModuleCode()};
		LuceneSerachPOJO pojo=this.get(fieldsUpdate, constents);
		if (pojo==null) {
			IndexWriter indexWriter=getWriter();
			if (indexWriter!=null) {
				Document document=nodeToDocument(luceneNode);
				indexWriter.addDocument(document);
				indexWriter.close();
			}
		}
	}

	@Override
	public void update(LuceneNode luceneNode, String tableId, String moduleCode) throws Exception {
		 
		//1需要根据tableId 和 moduleCode  这两个字段来确认一条索引数据
		//2通过luceneNode对象填充Document
		//3修改
		
		//1
		String [] content={tableId,moduleCode};
		LuceneSerachPOJO pojo=get(fieldsUpdate, content);
		IndexWriter indexWriter=getWriter();
		if (indexWriter!=null) {
			//2
			Document document=nodeToDocument(luceneNode);
			indexWriter.addDocument(document);
			//3
			indexWriter.updateDocument(new Term("tableId",pojo.getTableId()), document);
			indexWriter.close();
		}
	}

	@Override
	public void delted(String tableId, String moduleCode) throws Exception {
		//1需要根据tableId 和 moduleCode  这两个字段来确认一条索引数据
		//2根据ID删除
		
		//1
		String [] content={tableId,moduleCode};
		LuceneSerachPOJO pojo=get(fieldsUpdate, content);
		IndexWriter indexWriter=getWriter();
		if (indexWriter!=null) {
			//2
			indexWriter.deleteDocuments(new Term(ID,pojo.getLuceneId()));
		}
	}

	@Override
	public List<LuceneSerachPOJO> find(Integer pageIndex,Integer pageSize,String search) throws Exception {
		
		if (search!=null) {
			IndexSearcher searcher = getSearcher();
			if (searcher!=null) {
				QueryParser parser = new QueryParser(searchFiled,new KeywordAnalyzer());
				parser.setAllowLeadingWildcard(true);
				Query query=parser.parse(search);
				//先获取上一页的最后一个元素
				ScoreDoc lastSd = getLastScoreDoc(pageIndex,pageSize,query,searcher);
				//通过最后一个元素搜索下页的pageSize个元素
				TopDocs tds = searcher.searchAfter(lastSd,query,pageSize);
				List<LuceneSerachPOJO> pojos=new ArrayList<>();
				for (ScoreDoc sd:tds.scoreDocs) {
					Document doc = searcher.doc(sd.doc);
					//反射
					pojos.add(documentToPojo(doc));
				}
				return pojos;
			}
		}
		return new ArrayList<>();
	}

	@Override
	public LuceneSerachPOJO get(String[] fields,String[] content) throws Exception{	
		
		//字段数必须与内容数相等
		if (fields!=null&&content!=null&&fields.length==fields.length) {
			IndexSearcher searcher=getSearcher();
			if (searcher!=null) {
				BooleanQuery booleanQuery=new BooleanQuery();
				for (int i = 0; i < fields.length; i++) {
					QueryParser parser=new QueryParser(fields[i], new KeywordAnalyzer());
					parser.setAllowLeadingWildcard(true);
					Query query=parser.parse(content[i]);
					booleanQuery.add(query, Occur.MUST);
				}
				TopDocs tds = searcher.search(booleanQuery, ONE);
				for (ScoreDoc sd:tds.scoreDocs) {
					Document doc = searcher.doc(sd.doc);
					//反射
					return documentToPojo(doc);
				}
 			}
		}
		return null;
	}
	
	

	@Override
	public Document nodeToDocument(LuceneNode node) throws Exception {
		
		Document document=new Document();
    	//反射
    	Class luClass = node.getClass();
    	java.lang.reflect.Field[] fields = luClass.getDeclaredFields();
    	for (int i = 0; i < fields.length; i++) {
    		java.lang.reflect.Field f = fields[i];
    		f.setAccessible(true);
    		document.add(new StringField(f.getName(),f.get(node)==null?"":f.get(node).toString(),Field.Store.YES));
    	}
    	return document;
	}

	@Override
	public List<LuceneSerachPOJO> find(String search) throws Exception {
		
		IndexSearcher searcher=getSearcher();
		if (searcher!=null) {
			QueryParser parser=new QueryParser(SERACH, new KeywordAnalyzer());
			parser.setAllowLeadingWildcard(true);
			Query query=parser.parse(search);
			TopDocs tds = searcher.search(query, HUNDRED);
			List<LuceneSerachPOJO> pojos=new ArrayList<>();
			for (ScoreDoc sd:tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				//反射
				pojos.add(documentToPojo(doc));
			}
			return pojos;
		}
		return new ArrayList<>();
	}
	
	public static void main(String[] args) throws Exception {
		LuceneDaoImpl impl=new LuceneDaoImpl();
		impl.find("*hutao*");
	}
}
