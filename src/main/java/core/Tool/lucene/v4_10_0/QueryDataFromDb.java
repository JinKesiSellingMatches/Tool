  package core.Tool.lucene.v4_10_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;  
import org.apache.lucene.document.Document;  
import org.apache.lucene.document.Field;  
import org.apache.lucene.document.StringField;  
import org.apache.lucene.index.CorruptIndexException;  
import org.apache.lucene.index.DirectoryReader;  
import org.apache.lucene.index.IndexReader;  
import org.apache.lucene.index.IndexWriter;  
import org.apache.lucene.index.IndexWriterConfig;  
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;  
import org.apache.lucene.search.Query;  
import org.apache.lucene.search.ScoreDoc;  
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;  
import org.apache.lucene.store.FSDirectory;  
import org.apache.lucene.util.Version;
import org.apache.poi.ss.formula.ptg.StringPtg;

import core.pojo.DataPojo;



public class QueryDataFromDb {
	
	static String[] title={"month","day","time","year","name","role","controller"};
	static List<DataPojo> pojos=new ArrayList<DataPojo>();
	
	static String indexPath="E:/lucene/index04";
	
    public void index() throws SQLException, ClassNotFoundException, Exception {  
        IndexWriter writer=getWriter();
        try {  
        	String[] times={"2017","2018","2016","2019","2017","2017"};
        	String[] neme={"admin","admin","刘双","刘铭慧","admin","刘双"};
        	String[] controller={"刘铭慧","啊啊啊","6666","郁闷","测试","嘎嘎嘎"};
        	for (int i = 0; i < controller.length; i++) {
        		Document document = new Document();
        		document.add(new StringField("time",times[i],Field.Store.YES)); 
        		document.add(new StringField("neme",neme[i],Field.Store.YES)); 
        		document.add(new StringField("controller",controller[i],Field.Store.YES)); 
        		writer.addDocument(document);
        	}
        	writer.close();
        } finally {  
            try {  
                if (writer != null) {  
                    writer.close();  
                }  
            } catch (CorruptIndexException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    private static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
      
      
    /**
     * @return
     * @throws IOException
     */
    public IndexSearcher getSearcher() throws IOException { 
    	long start = System.currentTimeMillis();  
    	Directory directory = FSDirectory.open(new File(indexPath));
        IndexReader reader = DirectoryReader.open(directory);  
        IndexSearcher searcher = new IndexSearcher(reader); 
        return searcher;  
    }  
      
    /**
     * 根据页码和分页大小获取上一次的最后一个ScoreDoc
     */
    private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher) throws IOException {
        if(pageIndex==1)return null;//如果是第一页就返回空
        int num = pageSize*(pageIndex-1);//获取上一页的数量
        TopDocs tds = searcher.search(query, num);
        return tds.scoreDocs[num-1];
    }
    
    /***
     * 在使用时，searchAfter查询的是指定页数后面的数据，效率更高，推荐使用
     * @param query
     * @param pageIndex
     * @param pageSize
     */
    public void searchPageByAfter(String query,int pageIndex,int pageSize) {
        try {
            IndexSearcher searcher = getSearcher();
            QueryParser parser = new QueryParser("math",new StandardAnalyzer());
            Query q = null;
            try {
                q = parser.parse(query);
            } catch (org.apache.lucene.queryparser.classic.ParseException e) {
                e.printStackTrace();
            }
            //先获取上一页的最后一个元素
            ScoreDoc lastSd = getLastScoreDoc(pageIndex, pageSize, q, searcher);
            //通过最后一个元素搜索下页的pageSize个元素
            TopDocs tds = searcher.searchAfter(lastSd,q, pageSize);
            System.out.println(tds.totalHits);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println("id:" + doc.get("id"));  
                System.out.println("name:" + doc.get("name"));  
                System.out.println("math:" + doc.get("math")); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void searchByTerm(String[] fields, String[] name, int num,String role) throws Exception {
        IndexSearcher searcher = getSearcher();  
        
//        MultiFieldQueryParser parser=new MultiFieldQueryParser(fields, new StandardAnalyzer());
//        parser.setAllowLeadingWildcard(true);
        BooleanQuery booleanQuery = new BooleanQuery();
        
        QueryParser parser = new QueryParser(fields[0],new KeywordAnalyzer());
        parser.setAllowLeadingWildcard(true);
        Query titleQuery = parser.parse(name[0]);  
        booleanQuery.add(titleQuery, Occur.SHOULD); 
          
//        QueryParser parser1 = new QueryParser(fields[1],new KeywordAnalyzer()); 
//        parser1.setAllowLeadingWildcard(true);
//        Query contentQuery = parser1.parse(name[1]);  
//        booleanQuery.add(contentQuery,Occur.SHOULD);   
        
//        BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD}; 
//        Query query=MultiFieldQueryParser.parse(fields, name, flags, new StandardAnalyzer());
        TopDocs tds = searcher.search(booleanQuery, num);  
        System.out.println("count:" + tds.totalHits);
        for (ScoreDoc sd : tds.scoreDocs) {  
            Document doc = searcher.doc(sd.doc); 
            DataPojo dataPojo=new DataPojo();
            String time=doc.get("time");
            
            StringTokenizer tokenTime=new StringTokenizer(time);
    		//System.out.println(token.countTokens().get);
        	while(tokenTime.hasMoreTokens()){
       			 String aString=tokenTime.nextToken();
       			//System.out.println(aString);
       			if (tokenTime.countTokens()==4) {
       			 dataPojo.setMonth(aString);
       			}
       			if (tokenTime.countTokens()==3) {
       				dataPojo.setDay(aString);
    			}
       			
       			if (tokenTime.countTokens()==2) {
       				dataPojo.setTime(aString);
    			}
       			
       			if (tokenTime.countTokens()==0) {
       				dataPojo.setYear(aString);
    			}
        }
            dataPojo.setName(doc.get("name"));
            dataPojo.setRole(role);
            String controller= doc.get("controller");
            dataPojo.setController(controller);
            pojos.add(dataPojo);
        }
    }
    
    public void addIndex() throws Exception{
    	IndexWriter indexWriter=getWriter();
    	Document document=new Document();
    	document.add(new StringField("time","2019",Field.Store.YES));
    	document.add(new StringField("name","胡涛",Field.Store.YES));
    	document.add(new StringField("controller","胡涛",Field.Store.YES));
    	document.add(new StringField("age","23",Field.Store.YES));
    	indexWriter.addDocument(document);
    	indexWriter.close();
    }
    
    public void updateIndex()throws Exception{
    	IndexWriter indexWriter=getWriter();
    	Document document=new Document();
    	document.add(new StringField("time","2019",Field.Store.YES));
    	document.add(new StringField("name","刘铭慧",Field.Store.YES));
    	document.add(new StringField("controller","刘铭慧",Field.Store.YES));
    	indexWriter.updateDocument(new Term("controller","胡涛"), document);
    	indexWriter.close();
    }
    
    public void deletedIndex()throws Exception{
    	IndexWriter indexWriter=getWriter();
    	indexWriter.deleteDocuments(new Term("name", "胡涛"));
    	indexWriter.commit();
    	indexWriter.close();
    }
    
    private static IndexWriter  getWriter() throws Exception{
    	Directory directory = FSDirectory.open(new File(indexPath));  
        Analyzer analyzer = new StandardAnalyzer();  
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_4,analyzer);  
        conf.setOpenMode(OpenMode.CREATE_OR_APPEND);  
        conf.setMaxBufferedDocs(100); 
		return new IndexWriter(directory, conf);
	}
    
      
    public static void main(String[] args) throws Exception {  
        QueryDataFromDb indexUtil = new QueryDataFromDb();
       // indexUtil.index();
        //indexUtil.addIndex(); 
        //indexUtil.updateIndex();
        //indexUtil.deletedIndex();
        long start = System.currentTimeMillis();
        String[] serch={"*"}; 
        String[] fields={"tableName"};
        indexUtil.searchByTerm(fields, serch,100,null);
        System.out.println(pojos);
        System.out.println(System.currentTimeMillis()-start);
    }  
} 