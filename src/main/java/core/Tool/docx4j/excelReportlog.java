//package core.Tool.docx4j;
//
//import java.io.File;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.xml.bind.DatatypeConverter;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//
//import org.docx4j.openpackaging.exceptions.Docx4JException;
//import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
//import org.docx4j.openpackaging.parts.PartName;
//import org.docx4j.openpackaging.parts.SpreadsheetML.Styles;
//import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
//import org.xlsx4j.jaxb.Context;
//import org.xlsx4j.sml.CTBooleanProperty;
//import org.xlsx4j.sml.CTBorder;
//import org.xlsx4j.sml.CTBorderPr;
//import org.xlsx4j.sml.CTBorders;
//import org.xlsx4j.sml.CTCellAlignment;
//import org.xlsx4j.sml.CTCellStyle;
//import org.xlsx4j.sml.CTCellStyles;
//import org.xlsx4j.sml.CTCellXfs;
//import org.xlsx4j.sml.CTColor;
//import org.xlsx4j.sml.CTDxfs;
//import org.xlsx4j.sml.CTExtension;
//import org.xlsx4j.sml.CTExtensionList;
//import org.xlsx4j.sml.CTFill;
//import org.xlsx4j.sml.CTFills;
//import org.xlsx4j.sml.CTFont;
//import org.xlsx4j.sml.CTFontFamily;
//import org.xlsx4j.sml.CTFontName;
//import org.xlsx4j.sml.CTFontScheme;
//import org.xlsx4j.sml.CTFontSize;
//import org.xlsx4j.sml.CTFonts;
//import org.xlsx4j.sml.CTNumFmt;
//import org.xlsx4j.sml.CTNumFmts;
//import org.xlsx4j.sml.CTPatternFill;
//import org.xlsx4j.sml.CTRst;
//import org.xlsx4j.sml.CTSheetFormatPr;
//import org.xlsx4j.sml.CTStylesheet;
//import org.xlsx4j.sml.CTTableStyles;
//import org.xlsx4j.sml.CTXf;
//import org.xlsx4j.sml.CTXstringWhitespace;
//import org.xlsx4j.sml.Cell;
//import org.xlsx4j.sml.Row;
//import org.xlsx4j.sml.STCellType;
//import org.xlsx4j.sml.STFontScheme;
//import org.xlsx4j.sml.STHorizontalAlignment;
//import org.xlsx4j.sml.STPatternType;
//import org.xlsx4j.sml.STVerticalAlignment;
//import org.xlsx4j.sml.SheetData;
//
//import core.pojo.DataPojo;
//
//
//public class excelReportlog {
//	
//	public static void docx4jExcel(String outputpath,String[] titles,List<DataPojo> pojos){
//		try {
//			String outputfilepath = outputpath;
//	        SpreadsheetMLPackage pkg = SpreadsheetMLPackage.createPackage();
//	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//	        String nowDate=format.format(new Date());
//	        Styles styles = new Styles(new PartName("/xl/styles.xml"));
//	        styles.setJaxbElement(createStyleSheet());
//	        pkg.getWorkbookPart().addTargetPart(styles);
//	        WorksheetPart sheet = pkg.createWorksheetPart(new PartName("/xl/worksheets/Sheet1.xml"), "log_"+nowDate,1);
//	        addContent(sheet,titles,pojos);
//	        pkg.save(new File(outputfilepath));
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//	
//	static void addContent(WorksheetPart sheet,String[] titles,List<DataPojo> pojos) throws JAXBException, Docx4JException {
//
//		CTSheetFormatPr formatPr=Context.getsmlObjectFactory().createCTSheetFormatPr();
//		formatPr.setDefaultColWidth(20.0);
//		formatPr.setDefaultRowHeight(20.0);
//		formatPr.setCustomHeight(Boolean.TRUE);
//		sheet.getContents().setSheetFormatPr(formatPr);
//        // Minimal content already present
//        SheetData sheetData = sheet.getContents().getSheetData();
//        //Title
//        	for (DataPojo pojo : pojos) {
//        		
//        		 Row row = Context.getsmlObjectFactory().createRow();
//        		 //这里用到反射
//        		 for (int j = 0; j < titles.length; j++) {
//        			 
//	        		 Class class2 = pojo.getClass();
//	        		 Field[] fields = class2.getDeclaredFields();
//	        		 for (int i = 0; i < fields.length; i++) {
//	        			 Field f = fields[i];
//	        			 f.setAccessible(true);
//	        			 if (f.getName().equals(titles[j])) {
//	        				 try {
//	        					 if (f.get(pojo)!=null) {
//									 row.getC().add(createCell(f.get(pojo).toString()));
//								 }
//							} catch (Exception e) {
//								e.printStackTrace();
//							} 
//						 }
//	        		 }
//        		 }
//        		 row.setHt(20.0);
//        		 row.setCustomHeight(Boolean.TRUE);
//        		 sheetData.getRow().add(row);
//			}
//    }
//
//	/**
//	 * 通用
//	 * @param content
//	 * @return
//	 */
//    private static Cell createCell(String content) {
//
//        Cell cell = Context.getsmlObjectFactory().createCell();
//        CTXstringWhitespace ctx = Context.getsmlObjectFactory().createCTXstringWhitespace();
//        ctx.setValue(content);
//        CTRst ctrst = new CTRst();
//        ctrst.setT(ctx);
//        cell.setT(STCellType.INLINE_STR);
//        cell.setIs(ctrst); // add ctrst as inline string
//        return cell;
//    }
//    
//    /**
//     * 数字
//     * @param content
//     * @return
//     */
//    private static Cell createIntCell(String content) {
//        Cell  cell = Context.smlObjectFactory.createCell();
//        cell.setV(content);
//        cell.setT(STCellType.N);
//        cell.setS(0L);
//        return cell;
//    }
//    
//    /**
//     * 金额
//     * @param content
//     * @return
//     */
//    private static Cell createPurcherDetailTotalPriceCell(String content) {
//        Cell  cell = Context.smlObjectFactory.createCell();
//        cell.setV(content);
//        cell.setT(STCellType.N);
//        cell.setS(2L);
//        return cell;
//    }
//    
//    /**
//     * 标题
//     * @param content
//     * @return
//     */
//    private static Cell createTitleCell(String content) {
//        Cell  cell = Context.smlObjectFactory.createCell();
//        cell.setV(content);
//        cell.setT(STCellType.STR);
//        cell.setS(1L);
//        return cell;
//    }
//    
//    /**
//     * %号
//     * @param content
//     * @return
//     */
//    private static Cell createPerCell(String content) {
//        Cell  cell = Context.smlObjectFactory.createCell();
//        cell.setV(content);
//        cell.setT(STCellType.N);
//        cell.setS(4L);
//        return cell;
//    }
//    
//    /**
//     * 时间
//     * @param content
//     * @return
//     */
//    private static Cell createDateCell(String content) {
//        Cell  cell = Context.smlObjectFactory.createCell();
//        cell.setV(content);
//        cell.setT(STCellType.N);
//        cell.setS(3L);
//        return cell;
//    }
//    
//    private static CTStylesheet createStyleSheet(){
//    	CTStylesheet ss = Context.getsmlObjectFactory().createCTStylesheet();
//	    CTNumFmts formate = new CTNumFmts();
//	    formate.setCount(2L);
//	    //数据格式化
//	    CTNumFmt format0 = new CTNumFmt();
//	    format0.setNumFmtId(176L);
//	    format0.setFormatCode("#,##0.00_ ");
//	    formate.getNumFmt().add(format0);
//	    
//	    //数据格式化
//	    CTNumFmt format1 = new CTNumFmt();
//	    format1.setNumFmtId(178L);
//	    format1.setFormatCode("yyyy/m/d;@");
//	    formate.getNumFmt().add(format1);
//	    ss.setNumFmts(formate);
//	    //设置字体
//        CTFonts fonts = new CTFonts();
//        fonts.setCount(2L);
//        
//        //字体一
//        CTFont font0 = new CTFont();
//        CTFontSize sz0 = new CTFontSize();
//        sz0.setVal(11L);
//        JAXBElement<CTFontSize> element0 = Context.smlObjectFactory.createCTFontSz(sz0);
//        font0.getNameOrCharsetOrFamily().add(element0);
//        
//        CTColor col0 = new CTColor();
//        col0.setTheme(1L);
//        JAXBElement<CTColor> element1 = Context.smlObjectFactory.createCTFontColor(col0);
//        font0.getNameOrCharsetOrFamily().add(element1);
//        
//        CTFontName fname0 = new CTFontName();
//        fname0.setVal("宋体");
//        JAXBElement<CTFontName> element2 = Context.smlObjectFactory.createCTFontName(fname0);
//        font0.getNameOrCharsetOrFamily().add(element2);
//        
//        CTFontFamily ctFontFamily0=new CTFontFamily();
//        ctFontFamily0.setVal(2);
//        JAXBElement<CTFontFamily> element3 = Context.smlObjectFactory.createCTFontFamily(ctFontFamily0);
//        font0.getNameOrCharsetOrFamily().add(element3);
//        
//        CTFontScheme fschema0 = new CTFontScheme();
//        fschema0.setVal(STFontScheme.MINOR);
//        JAXBElement<CTFontScheme> element4 = Context.smlObjectFactory.createCTFontScheme(fschema0);
//        font0.getNameOrCharsetOrFamily().add(element4);
//        
//        fonts.getFont().add(font0);
//        //字体二
//        CTFont font1 = new CTFont();
//        
//        JAXBElement<CTBooleanProperty> element10 = Context.smlObjectFactory.createCTFontB(new CTBooleanProperty());
//        font1.getNameOrCharsetOrFamily().add(element10);
//        
//        CTFontSize sz1 = new CTFontSize();
//        sz1.setVal(11L);
//        JAXBElement<CTFontSize> element5 = Context.smlObjectFactory.createCTFontSz(sz1);
//        font1.getNameOrCharsetOrFamily().add(element5);
//        
//        CTColor col1 = new CTColor();
//        col1.setTheme(1L);
//        JAXBElement<CTColor> element6 = Context.smlObjectFactory.createCTFontColor(col1);
//        font1.getNameOrCharsetOrFamily().add(element6);
//        
//        CTFontName fname1 = new CTFontName();
//        fname1.setVal("宋体");
//        JAXBElement<CTFontName> element7 = Context.smlObjectFactory.createCTFontName(fname1);
//        font1.getNameOrCharsetOrFamily().add(element7);
//        
//        CTFontFamily ctFontFamily1=new CTFontFamily();
//        ctFontFamily1.setVal(2);
//        JAXBElement<CTFontFamily> element8 = Context.smlObjectFactory.createCTFontFamily(ctFontFamily1);
//        font1.getNameOrCharsetOrFamily().add(element8);
//        
//        CTFontScheme fschema1 = new CTFontScheme();
//        fschema1.setVal(STFontScheme.MINOR);
//        JAXBElement<CTFontScheme> element9 = Context.smlObjectFactory.createCTFontScheme(fschema1);
//        font1.getNameOrCharsetOrFamily().add(element9);
//        
//        fonts.getFont().add(font1);
//        
//        ss.setFonts(fonts);
//        
//        //背景色
//        CTFills fills = new CTFills();
//        fills.setCount(3L);
//        CTFill fill0 = new CTFill();
//        CTFill fill1 = new CTFill();
//        CTFill fill2 = new CTFill();
//        CTPatternFill patfill0 = new CTPatternFill();
//        patfill0.setPatternType(STPatternType.NONE);
//        fill0.setPatternFill(patfill0);
//        
//        CTPatternFill patfill1 = new CTPatternFill();
//        patfill1.setPatternType(STPatternType.GRAY_125);
//        fill1.setPatternFill(patfill1);
//        
//        CTPatternFill patfill2 = new CTPatternFill();
//        patfill2.setPatternType(STPatternType.SOLID);
//        CTColor color0 = new CTColor();
//        color0.setRgb(DatatypeConverter.parseHexBinary("93d150"));
//        
//        CTColor color1 = new CTColor();
//        color1.setIndexed(64L);
//        
//        patfill2.setFgColor(color0);
//        patfill2.setBgColor(color1);
//        
//        fill2.setPatternFill(patfill2);
//        fills.getFill().add(fill0);
//        fills.getFill().add(fill1);
//        fills.getFill().add(fill2);
//        ss.setFills(fills);
//        
//        //边框
//        CTBorders borders = new CTBorders();
//        borders.setCount(1L);
//        CTBorder border = new CTBorder();
//        border.setLeft(new CTBorderPr());
//        border.setRight(new CTBorderPr());
//        border.setTop(new CTBorderPr());
//        border.setBottom(new CTBorderPr());
//        border.setDiagonal(new CTBorderPr());
//        borders.getBorder().add(border);
//        ss.setBorders(borders);
//      
////        //样式
////        CTCellStyleXfs xcfs = new CTCellStyleXfs();
////        xcfs.setCount(1L);
////        CTXf xcf = new CTXf();
////        xcf.setNumFmtId(0L);
////        xcf.setFontId(0L);
////        xcf.setFillId(0L);
////        xcf.setBorderId(0L);
////        xcfs.getXf().add(xcf);
////        ss.setCellStyleXfs(xcfs);
//        
//        CTCellXfs xfs = new CTCellXfs();
//        xfs.setCount(4L);
//        //空样式
//        CTXf xf0 = new CTXf();
//        xf0.setNumFmtId(0L);
//        xf0.setFontId(0L);
//        xf0.setFillId(0L);
//        xf0.setBorderId(0L);
//        xf0.setXfId(0L);
//        xfs.getXf().add(xf0);
//        //标题
//        CTXf xf1 = new CTXf();
//        xf1.setNumFmtId(0L);
//        xf1.setFontId(1L);
//        xf1.setFillId(2L);
//        xf1.setBorderId(0L);
//        xf1.setXfId(0L);
//        xfs.getXf().add(xf1);
//        CTCellAlignment align = new CTCellAlignment();
//        align.setHorizontal(STHorizontalAlignment.CENTER);
//        align.setVertical(STVerticalAlignment.CENTER);
//        xf1.setAlignment(align);
//        //格式化数据
//        CTXf xf9 = new CTXf();
//        xf9.setNumFmtId(176L);
//        xf9.setFontId(0L);
//        xf9.setFillId(0L);
//        xf9.setBorderId(0L);
//        xf9.setXfId(0L);
//        xf9.setApplyNumberFormat(Boolean.TRUE);
//        xfs.getXf().add(xf9);
//      //格式化数据日期
//        CTXf xf2 = new CTXf();
//        xf2.setNumFmtId(178L);
//        xf2.setFontId(0L);
//        xf2.setFillId(0L);
//        xf2.setBorderId(0L);
//        xf2.setXfId(0L);
//        xf2.setApplyNumberFormat(Boolean.TRUE);
//        xf2.setApplyFont(Boolean.TRUE);
//        xf2.setApplyFill(Boolean.TRUE);
//        xf2.setApplyBorder(Boolean.TRUE);
//        xf2.setApplyAlignment(Boolean.TRUE);
//        xfs.getXf().add(xf2);
//        //格式化%
//        CTXf xf3 = new CTXf();
//        xf3.setNumFmtId(10L);
//        xf3.setFontId(0L);
//        xf3.setFillId(0L);
//        xf3.setBorderId(0L);
//        xf3.setXfId(0L);
//        xf3.setApplyNumberFormat(Boolean.TRUE);
//        xfs.getXf().add(xf3);
//        
//        ss.setCellXfs(xfs);
//        //样式
//        CTCellStyles cstyles = new CTCellStyles();
//        cstyles.setCount(1L);
//        //
//        CTCellStyle cstyle = new CTCellStyle();
//        cstyle.setName("Standard");
//        cstyle.setXfId(0L);
//        cstyles.getCellStyle().add(cstyle);
//        
//        ss.setCellStyles(cstyles);
//        
//        //<editor-fold defaultstate="collapsed" desc="DXFS">
//        CTDxfs dxfs = new CTDxfs();
//        dxfs.setCount(0L);
//        ss.setDxfs(dxfs);
//        //</editor-fold>
//        
//        //<editor-fold defaultstate="collapsed" desc="Table Styles">
//        CTTableStyles tstyles = new CTTableStyles();
//        tstyles.setCount(0L);
//        tstyles.setDefaultTableStyle("TableStyleMedium2");
//        tstyles.setDefaultPivotStyle("PivotStyleLight16");
//        
//        ss.setTableStyles(tstyles);
//        //</editor-fold>
//        
//        //<editor-fold defaultstate="collapsed" desc="Extensions">
//        CTExtensionList extlist = new CTExtensionList();
//        CTExtension ext = new CTExtension();
//        ext.setUri("{EB79DEF2-80B8-43e5-95BD-54CBDDF9020C}");
//        extlist.getExt().add(ext);
//        ss.setExtLst(extlist);
//	    return ss;
//    }
//
//}
