package core.Tool.lucene.v4_10_0;
//package Tool.lucene.file;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Date;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.index.CorruptIndexException;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.WildcardQuery;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.*;
//
//public class SearchFile {
//	
//	/**  
//	   * @param sourceFile 需要添加到索引中的路径  
//	   * @param indexFile  存放索引的路径  
//	   * @throws Exception  
//	   */  
//	public static void textFileIndexer(String sourceFile,String indexFile) throws Exception{
//		 File sourceDir = new File(sourceFile);
//		IndexWriter writer = null;  
//        try {  
//        	Directory directory = FSDirectory.open( new File(indexFile));  
//            Analyzer analyzer = new StandardAnalyzer();  
//            IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_4,analyzer);  
//            conf.setOpenMode(OpenMode.CREATE_OR_APPEND);  
//            conf.setMaxBufferedDocs(100);  
//            writer = new IndexWriter(directory, conf);  
//            //insertData();  
//            File[] textFiles = sourceDir.listFiles(); 
//            for(int i=0;i<textFiles.length;i++){ 
//            	if(textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")){  
//	                System.out.println("File--->" + textFiles[i].getCanonicalPath() + " 正在被索引.....");  
//	                String str_temp = fileReaderAll(textFiles[i].getCanonicalPath(),"UTF-8");  
//	                System.out.println("文件内容：" + str_temp); 
//	                Document document = new Document();  
//	                document.add(new StringField("id", str_temp,Field.Store.YES));  
//	                //document.add(new StringField("name", result.getString("name"), Field.Store.YES));  
//	                //document.add(new StringField("math", result.getString("math"), Field.Store.YES));  
//	                writer.addDocument(document);  
//            	}
//               
//            }  
//            writer.close();
//        } finally {  
//            try {  
//                if (writer != null) {  
//                    writer.close();  
//                }  
//            } catch (CorruptIndexException e) {  
//                e.printStackTrace();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }
//	 } 
//
//	private static String fileReaderAll(String filename,String charset) throws IOException{  
//        BufferedReader buffer_read = new BufferedReader(  
//                new InputStreamReader(new FileInputStream(filename),charset));  
//        String line = new String();  
//        String temp = new String();  
//          
//        while((line = buffer_read.readLine()) != null){  
//            temp += line ;  
//        }  
//          
//        buffer_read.close();  
//          
//        return temp ;  
//    } 
//	
//	
//	 /**
//     * @return
//     * @throws IOException
//     */
//    public static IndexSearcher getSearcher(String indexFile) throws IOException { 
//    	long start = System.currentTimeMillis();  
//    	Directory directory = FSDirectory.open(new File(indexFile));
//        IndexReader reader = DirectoryReader.open(directory);  
//        IndexSearcher searcher = new IndexSearcher(reader); 
//        System.out.println(" getSearcher:"+(System.currentTimeMillis() - start) + " ms");  
//        return searcher;  
//    } 
//    
//	/**  
//     * @param indexFile 索引所在的路径  
//     * @param keyWords  需要检索的关键字  
//     * @throws IOException  
//     * @throws ParseException  
//     */  
//     public static void queryKeyWords(String indexFile,String keyWords) throws Exception{
//    	 
//    	 IndexSearcher searcher = getSearcher(indexFile);  
//         // WildcardQuery 模糊查找  
//         // TermQuery 精确查找  
//         Query query = new WildcardQuery(new Term("id", keyWords));  
//         TopDocs tds = searcher.search(query, 10);
//         for (ScoreDoc sd : tds.scoreDocs) {  
//             Document doc = searcher.doc(sd.doc);  
//             System.out.println("body:" + doc.get("id"));  
//         }
//         ScoreDoc[] hits = tds.scoreDocs;  
//        if(hits.length > 0){  
//            System.out.println("关键字：" + keyWords + "，在  " + indexFile + "中，一共检索到" + hits.length + "个...");  
//        }  
//    } 
//     
//     public static void main(String[] args) throws Exception{  
//         String sourcePath = "E:/lucene/file" ;  
//         String indexPath = "E:/lucene/index03";  
//         String key_words = "你";
//          
//         textFileIndexer(sourcePath, indexPath);  
//         queryKeyWords(indexPath, key_words);  
//           
//     }
//}
