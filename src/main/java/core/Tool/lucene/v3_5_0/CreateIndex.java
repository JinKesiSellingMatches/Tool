package core.Tool.lucene.v3_5_0;
//package Tool.lucene.v3_5_0;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.print.Doc;
//
//import org.apache.lucene.analysis.WhitespaceAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.RAMDirectory;
//import org.docx4j.org.apache.poi.poifs.storage.BATBlock.BATBlockAndIndex;
//
//import junit.framework.TestCase;
//
//public class CreateIndex extends TestCase{
//	
//	protected String[] ids={"1","2"};
//	protected String[] text={"ni","hao"};
//	
//	protected String textString="text";
//	
//	
//	private Directory directory;
//	
//   public static void TestTerm() throws Exception{
//		
//		Directory dir=TestUtil.getBookIndexDirectory();
//		IndexSearcher searcher=new IndexSearcher(dir);
//		
//		Term t=new Term("subject","ant");
//		Query query=new TermQuery(t);
//		TopDocs docs=searcher.search(query, 10);
//		assertEquals("Ant in",1, docs.totalHits);
//		
//		searcher.close();
//		dir.close();
//	}
//	
//	public static void main(String[] args) throws Exception {
////		CreateIndex createIndex=new CreateIndex();
////		createIndex.setUp();
//////		//删除
//////		createIndex.testDeleteBeforeOptimize();
////		//修改
////		createIndex.textUpdae();
//		
//		TestTerm();
//	}
//	
//	protected void setUp() throws IOException{
//		directory=new RAMDirectory();
//		IndexWriter writer=getWriter();
//		writer.setInfoStream(System.out);
//		for (int i = 0; i < ids.length; i++) {
//			Document doc=new Document();
//			doc.add(new Field("id",ids[i],Field.Store.YES,Field.Index.NOT_ANALYZED));
//			doc.add(new Field(textString,text[i],Field.Store.YES,Field.Index.ANALYZED));
//			//writer.op
//			writer.addDocument(doc);
//		}
//		writer.close();
//	}
//	
//	private IndexWriter getWriter() throws IOException{
//		return new IndexWriter(directory, new WhitespaceAnalyzer(),IndexWriter.MaxFieldLength.UNLIMITED);
//	}
//	
////	private IndexWriter getWriter(IndexWriterConfig con) throws IOException{
////		
////	}
//	
//	protected int getHitCount(String fieldName,String searchString) throws IOException{
//		IndexSearcher searcher=new IndexSearcher(directory);
//		Term t=new Term(fieldName, searchString);
//		Query query=new TermQuery(t);
//		int hitCount=TestUtil.hitCount(searcher, query);
//		searcher.close();
//		return hitCount;
//	}
//	
//	public void testIndexWriter() throws IOException{
//		IndexWriter writer=getWriter();
//		assertEquals(ids.length, writer.numDocs());
//		writer.close();
//	}
//	
//	public void testDeleteBeforeOptimize() throws IOException{
//		IndexWriter writer=getWriter();
//		assertEquals(2, writer.numDocs());
//		writer.deleteDocuments(new Term("id","1"));
//		//writer.optimize();
//		writer.commit();
//		assertTrue(writer.hasDeletions());
//		writer.close();
//	}
//	
//	public void textUpdae()throws IOException{
//		
//		assertEquals(1, getHitCount(textString, "ni"));
//		
//		IndexWriter writer=getWriter();
//		Document doc=new Document();
//		doc.add(new Field("id","1",Field.Store.YES,Field.Index.NOT_ANALYZED));
//		doc.add(new Field(textString,"hutao",Field.Store.YES,Field.Index.ANALYZED));
//		doc.setBoost(5L);
//		writer.updateDocument(new Term("id","1"), doc);
//		writer.close();
//		
//		assertEquals(0, getHitCount(textString, "ni"));
//		assertEquals(1, getHitCount(textString, "hutao"));
//	}
//
//}
