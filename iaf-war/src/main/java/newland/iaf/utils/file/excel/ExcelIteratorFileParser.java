package newland.iaf.utils.file.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import newland.base.formater.Formatter;
import newland.iaf.utils.file.exception.FileParserException;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>excel叠代文件解析器</p>
 * @author shen,lance
 *
 */
public class ExcelIteratorFileParser extends
		AbstractExcelFileParser<Collection<?>> {
	
	private final static Logger logger = LoggerFactory.getLogger(ExcelIteratorFileParser.class);
	
    public final static String STYLE_TEXT = "text";
    public final static String STYLE_DATE = "date";
    public final static String STYLE_DATETIME = "datetime";
    public final static String STYLE_NUMBER = "number";

    private String title;
    private String sheetName;
    private List<CellDefine> cellList;
    private String modelClass;
    
    private Integer rowOffset = 0;
    
    private Integer cellOffset = 0;

    /**
     * @param dataFieldList The dataFieldList to set.
     */
    public void setCellList(List<CellDefine> dataFieldList) {
        this.cellList = dataFieldList;
    }
    /**
     * @param sheetName The sheetName to set.
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    public void setModelClass(String clazz){
    	this.modelClass = clazz;
    }


    
    /**
     * <p>解析流程描述如下：</p>
     * <ul>
     * 		<li>
     * 			获取excel的第一行，并比对第一行所有的类型为<tt>CELL_TYPE_STRING</tt>的数据，和配置模板中的定义名<tt>CellDefine.name</tt>
     * 			相比较，如果存在，则将其名称和对应行的序号存入<tt>indexMap:Map&lt;String,Integer&gt;</tt>
     * 		</li>
     * 		<li>
     * 			确认对应<tt>indexMap</tt>是否存在数据，若存在，则表示首行可能是标题，若不存在，则表示首行是数据。若首行是数据，则使用默认的
     * 			定义对象(List&lt;CellDefine&gt;)，按照默认顺序重新生成<tt>indexMap</tt>。
     * 		</li>
     * 		<li>
     * 			将indexMap展开，并通过indexMap里定义的的名字/序号关系分别获取：
     * 			<ol>
     * 				<li>单元格定义对象{@link com.intensoft.file.excel.CellDefine CellDeine}</li> 
     * 				<li>excel单元格对象{@link org.apache.poi.ss.usermodel.Cell Cell}</li>
     *			</ol>
     * 			传入函数{@link #cellCoverToContext(Object, Cell, CellDefine)}进行解析。
     * 		</li>
     * </ul>
     */
	protected Collection<?> convertFromWorkbook(Workbook wb) {
		if(modelClass == null)
			throw new FileParserException("the modelClass must not be null!could not init the instance!");
		
		
		/**解析excel**/
		Sheet sheet = null;
		if(sheetName != null)
			sheet = wb.getSheet(sheetName);
		if(sheet == null)
			sheet = wb.getSheetAt(0);
		if(sheet == null)
			throw new FileParserException("convert excel failed!could not get usersheet!");
		Map <String,Integer> indexMap = new HashMap<String, Integer>();
		Row head = sheet.getRow(rowOffset.intValue());
		if(head == null)
			throw new FileParserException("convert excel failed!may the sheet even without single row?");		
		
		
		
		boolean isHeadExists = false;
		/**生成映射关系 定义名称-》定义的类型**/
		Map <String,CellDefine> definedMap = new HashMap<String,CellDefine>(); 
		for(CellDefine cellDefine: cellList){
			definedMap.put(cellDefine.getName(),cellDefine);
		}
		
		/**生成映射关系 定义名称-》excel的列序号**/
		int i = cellOffset - 1;
		for(Cell cell:head){ //获取对应列名的序号 ，尝试解析第一行，获取类名关系。			                   
			i++;
			if(cell.getCellType() == Cell.CELL_TYPE_STRING){
				String key = cell.getRichStringCellValue().getString();
				if(definedMap.keySet().contains(key))//若名字空间包含对应key
					indexMap.put(key, i);
			}
		}
		if(indexMap.size()<=0) {//无法获取对应类名关系，使用默认关系。
			isHeadExists = false;
			i = -1;
			for(CellDefine cell: cellList){
				i++;
				indexMap.put(cell.getName(), i);
			}
		}else{
			isHeadExists = true;
		}
		
		/**开始生成结果**/
		List<Object> l = new ArrayList<Object>();
		
		i = - 1;
		for(Row row:sheet){
			i++;
			if (i < rowOffset.intValue()) continue;
			if(i == rowOffset.intValue() && isHeadExists) //若第一行为头，则忽略
				continue;
			
			Object context = null;
			try {
				context = Class.forName(modelClass).newInstance();
			} catch (Exception e) {
				logger.error("convert from excel failed!could not makeup the context!",e);
				continue;
			} 
			for(String name:indexMap.keySet()){
				
				
				/**获取对应的cell**/
				Integer index = indexMap.get(name);
				Cell cell = row.getCell(index);
				if(cell == null){
					continue;
				}
				/**获取对应的名称**/
				CellDefine cellDefine = definedMap.get(name);
				if(cellDefine == null){
					continue;
				}
				/**
				 * 产生结果
				 */
				cellCoverToContext(context,cell,cellDefine);
			}
			l.add(context);
		}
		return l;
	}
	

	private void cellCoverToContext(Object o, Cell cell,CellDefine cellDefine) {
		Object value = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(cell)){
					value = cell.getDateCellValue();
				}else{
					value = cell.getNumericCellValue();
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
			default:
		}
		if(value != null){
			Formatter formatter = cellDefine.getFormatter();
			if(formatter != null && value instanceof String){
				value = formatter.unformat((String)value);
			}
			String path = cellDefine.getPath();
			JXPathContext context = JXPathContext.newContext(o);
			context.setValue(path, value);
		}
		
	}
private void createCell(HSSFWorkbook book, HSSFRow row, Integer col, Object value, CellDefine cd, Map<Integer,HSSFCellStyle> bookStyleCache) {
        if (value == null) return ;
		
        HSSFCell cell = row.createCell(col);
        // 确定样式
        HSSFCellStyle style = bookStyleCache.get(col);
        
        if (style == null) {
        	style = getCellStyle(book, value, cd);
        	if (style != null)
        		bookStyleCache.put(col, style);
        }

        if (style != null)
        	cell.setCellStyle(style);
		// 确定值
		if (value instanceof String ) {
		    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    cell.setCellValue((String)value);
		    
		} else if (value instanceof java.util.Date) {
		    cell.setCellValue((Date)value);
		} else if (value instanceof Boolean) {
		    cell.setCellValue(((Boolean)value).booleanValue());
		} else if (value instanceof Number) {
		    cell.setCellValue(((Number)value).doubleValue());
		} else {
		    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    cell.setCellValue(value.toString());
		    
		}
    }
    
   private HSSFCellStyle getCellStyle(HSSFWorkbook book, Object value, CellDefine cd) {
        // 确定样式
        HSSFCellStyle style = book.createCellStyle();
		HSSFDataFormat format = book.createDataFormat();
		String cellFormat = null;
		if (cd.getStyleName() != null && cd.getCellFormat() == null) {
		    if (STYLE_TEXT.equalsIgnoreCase(cd.getStyleName())) {
			    cellFormat = "text";
			} else if (STYLE_NUMBER.equalsIgnoreCase(cd.getStyleName())) {
			    cellFormat = "#,##0.00";
			} else if (STYLE_DATE.equalsIgnoreCase(cd.getStyleName())) {
			    cellFormat = "yyyy-MM-dd";
			} else if (STYLE_DATETIME.equalsIgnoreCase(cd.getStyleName())) {
			    cellFormat = "yyyy-MM-dd HH:mm:ss";
			} 
		} else if (cd.getCellFormat() != null)  {
		    cellFormat = cd.getCellFormat();
		}
		
		// 未指定数据格式根据类型设置数据格式
		if (cellFormat == null) {
		    if (value instanceof Date) 
		        cellFormat = "yyyy-MM-dd";
		    else if (value instanceof String) {
		        cellFormat = "text";
		    }
		}
		
		if (cellFormat != null) {
		    try {
		        style.setDataFormat(format.getFormat(cellFormat));
		    } catch (Exception e) {
		        logger.warn("Excel单元格样式格式设置错误", e);
		    }
		}  
		
		// 设置对齐方式
		if (cd.getAlign() != null) {
			if ("left".equalsIgnoreCase(cd.getAlign())) {
			    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			} else if ("center".equalsIgnoreCase(cd.getAlign())) {
			    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			} else if ("right".equalsIgnoreCase(cd.getAlign())) {
			    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			}
		}
		return style;
    }
    
   private void setCellValue(HSSFCell cell, Object value) {
		// 确定值
		if (value instanceof String ) {
		    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    cell.setCellValue((String)value);
		    
		} else if (value instanceof java.util.Date) {
		    cell.setCellValue((Date)value);
		} else if (value instanceof Boolean) {
		    cell.setCellValue(((Boolean)value).booleanValue());
		} else if (value instanceof Number) {
		    cell.setCellValue(((Number)value).doubleValue());
		} else {
		    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    cell.setCellValue(value.toString());
		    
		}
    }

	protected void convertToWorkbook(Collection<?> o, Workbook workbook) {
		try {
			HSSFWorkbook wb =(HSSFWorkbook)workbook;
			HSSFSheet sheet = wb.createSheet();
			// excel 工作表名称
			if (sheetName != null)
			    wb.setSheetName( 0, sheetName);		
			// 当前行次
			short rowIndex = 0;
			HSSFRow row = null;
			HSSFCell cell = null;

			// 创建标题行
			if (title != null) {
			    
			}
			
			// 列头
			row = sheet.createRow(rowIndex++);
			for (int i=0; i<cellList.size(); i++) {
			    CellDefine cellDefine = (CellDefine)cellList.get(i);
			    cell = row.createCell(i);
			    
			    HSSFCellStyle style = wb.createCellStyle();
			    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			    
			    HSSFFont font = wb.createFont();
			    font.setColor(HSSFColor.BLUE_GREY.index);
			    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			    style.setFont(font); 
			    //style.setFillForegroundColor(HSSFColor.ORANGE.index);
			    //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			    cell.setCellStyle(style);
			    setCellValue(cell, cellDefine.getName());
			    
			}
			// 输出数据行
			Map bookStyleCache = new Hashtable();
			Iterator it = o.iterator();
			while (it.hasNext()) {
			    row = sheet.createRow(rowIndex++);
			    JXPathContext context = JXPathContext.newContext(it.next());
			    for (int i=0; i<cellList.size(); i++) {
				    CellDefine cf = (CellDefine)cellList.get(i);
				    // 单元格值处理
				    Formatter formatter = cf.getFormatter();
				    
				    Object value = null;
				    if (cf.getPath() != null) {
//				    	String[] str={"reciveAcctNo","reciveAcctName","reiciveBankName","reciveBankCode","status/desc"};
//				    	if(cf.getPath().equals(str[0])||cf.getPath().equals(str[1])||cf.getPath().equals(str[2])||cf.getPath().equals(str[3])||cf.getPath().equals(str[4])){
//				    		continue;
//				    	}
				        value = context.getValue(cf.getPath());
				        if (formatter != null) {
				            value = formatter.format(value);
				        }
				    } 
				    if (cf.getDefaultValue() != null && value == null) 
				        value = cf.getDefaultValue();
				    
				    createCell(wb, row, i, value, cf, bookStyleCache);
			    	
				}
			}
		} catch (Exception e) {
			logger.error("生成Excel文件异常",e);
			throw new FileParserException("生成Excel文件异常", e);
		}
	}
	public Integer getRowOffset() {
		return rowOffset;
	}
	public void setRowOffset(Integer rowOffset) {
		this.rowOffset = rowOffset;
	}
	public Integer getCellOffset() {
		return cellOffset;
	}
	public void setCellOffset(Integer cellOffset) {
		this.cellOffset = cellOffset;
	}

}
