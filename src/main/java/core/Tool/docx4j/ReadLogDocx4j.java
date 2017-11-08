package core.Tool.docx4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.parts.JaxbXmlPart;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.SpreadsheetML.SharedStrings;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.samples.PartsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.STCellType;
import org.xlsx4j.sml.SheetData;
import org.xlsx4j.sml.Worksheet;

import core.Tool.toolEnum.ClientWalletEnum;
import core.Tool.toolEnum.ErrorEnum;
import core.Tool.toolEnum.ReadExcelEnum;
import core.pojo.AddClientInfoByExcelPOJO;


public class ReadLogDocx4j {
	
	private static Logger log = LoggerFactory.getLogger(PartsList.class);						

	private static List<WorksheetPart> worksheets = null;
	
	private static SharedStrings sharedStrings = null;
	
	private static String info="错误，";
	
	private static String info2="有问题！！";
	
	public static String settleType1="订单";
	
	public static String settleType2="月结";
	
	public static String dictId1="402881f053d4eccd0153d512a8580002";
	
	public static String dictId2="402881f053d4eccd0153d513a0260003";
	
	static DecimalFormat decimalFormat = new DecimalFormat("###################.###########"); 
	/**
	 * 数据结果集
	 */
	private static List<AddClientInfoByExcelPOJO> pojos=new ArrayList<AddClientInfoByExcelPOJO>();
	
	/**
	 * This HashMap is intended to prevent loops.
	 */
	public static HashMap<Part, Part> handled = new HashMap<Part, Part>();
	
//	public static void main(String[] args) throws Exception {
//		
//		File file=new File("‪E:/123.xlsx");
//		InputStream in = new FileInputStream(file);
//		ExcelDate(in);
//	}
	
	/**
	 * 2.0   去除商品名称
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> ExcelDate(InputStream inputStream) throws Exception{
		Map<String, Object> result=new HashMap<String,Object>();
		//默认为true
			try {
				result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.TRUE.description());
				org.docx4j.openpackaging.packages.OpcPackage xlsxPkg = org.docx4j.openpackaging.packages.OpcPackage.load(inputStream);
				//这里初始化
				worksheets = new ArrayList<WorksheetPart>();
				sharedStrings=null;
				pojos.clear();
				RelationshipsPart rp = xlsxPkg.getRelationshipsPart();
				StringBuilder sb = new StringBuilder();
				printInfo(rp, sb, "");
				traverseRelationships(xlsxPkg, rp, sb, "    ");
				for(WorksheetPart sheet: worksheets) {
						Worksheet ws = sheet.getJaxbElement();
						SheetData data = ws.getSheetData();
						for (Row r : data.getRow() ) {
							if (r.getR()>1) {
									result=valueInfo(r);
									if (ReadExcelEnum.ERROR.description().equals(result.get(ReadExcelEnum.CODE.description()))) {
										return result;
									}
							}
						}
						if (ReadExcelEnum.TRUE.description().equals(result.get(ReadExcelEnum.CODE.description()))) {
							result.put(ReadExcelEnum.DATAS.description(), pojos);
						}
						return result;
				}
			} catch (Exception e) {
				result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.ERROR.description());
				result.put(ReadExcelEnum.DATAS.description(),"请上传已.xlsx结尾的文件");
			}
			return result;
		}
	
	/**
	 * 数据拼装
	 * @param r
	 * @return
	 * @throws Exception
	 */
	private static Map<String, Object> valueInfo(Row r) throws Exception{
		Map<String, Object> result=new HashMap<String,Object>();
		result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.TRUE.description());
		AddClientInfoByExcelPOJO pojo=new AddClientInfoByExcelPOJO();
		for (Cell c : r.getC() ) {
			if ("AB".indexOf(c.getR().substring(0, 1))>-1) {
			Map<String, Object> infoMap=getValueInfo(c);
			if (infoMap.get(ReadExcelEnum.CODE.description()).equals(ReadExcelEnum.TRUE.description())) {
				String infoDate=infoMap.get(ReadExcelEnum.DATAS.description()).toString();
				if (c.getR().indexOf("A")!=-1) {
					pojo.setId(infoDate);
				}else if (c.getR().indexOf("B")!=-1) {
                    pojo.setCode(infoDate);
                }
			 }else{
				 result=infoMap;
				 return result;
			 }
			}
		}
//		result=checkField(pojo,"id,billDay,paymentPeriod,settleType,beforeOrderAp,beforeShipmentsAp,signInAp,ticketAp");
//		if (result.get(ReadExcelEnum.CODE.description()).equals(ReadExcelEnum.TRUE.description())) {
			pojos.add(pojo);
//		}
		return result;
	}
	
	private static String number(String info){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(info);
		return m.replaceAll("").trim();
	}
	
	
	private static Map<String, Object> checkField(AddClientInfoByExcelPOJO pojo, String ignoreField)throws Exception{
		
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.TRUE.description());
		try {
			 Class class2 = pojo.getClass();
			 Field[] fields = class2.getDeclaredFields();
			 for (int i = 0; i < fields.length; i++) {
				 Field f = fields[i];
				 f.setAccessible(true);
				 if (ignoreField.indexOf(f.getName())<0) {
					 if (f.get(pojo)!=null &&f.get(pojo).toString().length()>0) {
					 }else{
						 result.put(ClientWalletEnum.Result.CODE.description(), ClientWalletEnum.Result.FALSE.description());
						 result.put(ClientWalletEnum.Result.DATA.description(), ErrorEnum.ISNULL.description());
					 }
				 }
			 }
		} catch (Exception e) {
			 result.put(ClientWalletEnum.Result.CODE.description(), ClientWalletEnum.Result.FALSE.description());
			 result.put(ClientWalletEnum.Result.DATA.description(), ErrorEnum.ISNULL.description());
		}
		return result;
	}
	
	/**
	 * 该方法用于验证
	 * @param c
	 * @return
	 * @throws Exception
	 */
	private static Map<String, Object> getValueInfo(Cell c) throws Exception{
		
		Map<String, Object> result=new HashMap<String, Object>();
		String value=null;
		if (c.getT().equals(STCellType.S)) {
			if (sharedStrings.getJaxbElement().getSi().get(Integer.parseInt(c.getV())).getT()!=null) {
				value=sharedStrings.getJaxbElement().getSi().get(Integer.parseInt(c.getV())).getT().getValue();
			}
		} else {
			value=c.getV();
		}
		if (value!=null&&value.trim().length()>0) {
			result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.TRUE.description());
			result.put(ReadExcelEnum.DATAS.description(), value.trim());
		}else{
			result.put(ReadExcelEnum.CODE.description(), ReadExcelEnum.ERROR.description());
			result.put(ReadExcelEnum.DATAS.description(), info+c.getR()+info2);
		}
	return result;
}
	/**
	 * 查找/的次数
	 * @param srcText
	 * @param findText
	 * @return
	 */
	private static Integer appearNumber(String srcText, String findText){
		int count = 0;
	    Pattern p = Pattern.compile(findText);
	    Matcher m = p.matcher(srcText);
	    while (m.find()) {
	        count++;
	    }
	    return count;
	}
	
	public static void  printInfo(Part p, StringBuilder sb, String indent) {
		sb.append("\n" + indent + "Part " + p.getPartName() + " [" + p.getClass().getName() + "] " );		
		if (p instanceof JaxbXmlPart) {
			Object o = ((JaxbXmlPart)p).getJaxbElement();
			if (o instanceof javax.xml.bind.JAXBElement) {
				sb.append(" containing JaxbElement:" + XmlUtils.JAXBElementDebug((JAXBElement)o) );
			} else {
				sb.append(" containing JaxbElement:"  + o.getClass().getName() );
			}
		}
		if (p instanceof WorksheetPart) {
			worksheets.add((WorksheetPart)p);
		} else if (p instanceof SharedStrings) {
			sharedStrings = (SharedStrings)p;
		}
		
	}
	
	
	
	public static void traverseRelationships(org.docx4j.openpackaging.packages.OpcPackage wordMLPackage, 
			RelationshipsPart rp, 
			StringBuilder sb, String indent) {
		
		// TODO: order by rel id
		
		for ( Relationship r : rp.getRelationships().getRelationship() ) {
			
			log.info("For Relationship Id=" + r.getId() 
					+ " Source is " + rp.getSourceP().getPartName() 
					+ ", Target is " + r.getTarget() );
		
			if (r.getTargetMode() != null
					&& r.getTargetMode().equals("External") ) {
				
				sb.append("\n" + indent + "external resource " + r.getTarget() 
						   + " of type " + r.getType() );
				continue;				
			}
			
			Part part = rp.getPart(r);
			
			
			printInfo(part, sb, indent);
			if (handled.get(part)!=null) {
				sb.append(" [additional reference] ");
				continue;
			}
			handled.put(part, part);
			if (part.getRelationshipsPart()==null) {
				// sb.append(".. no rels" );						
			} else {
				traverseRelationships(wordMLPackage, part.getRelationshipsPart(), sb, indent + "    ");
			}
					
		}
	}
}
