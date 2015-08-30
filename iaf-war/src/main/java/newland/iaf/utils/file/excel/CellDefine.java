/*
 * Created on 2004-7-24
 *
 * Project: CashManagement
 */
package newland.iaf.utils.file.excel;

import newland.base.formater.Formatter;

/**
 * CellDefine Excel单元格定义
 * Project: CashManagement
 * @author shen
 *
 * 2004-7-24
 */
public class CellDefine {
    
    /**
     * Excel列名称（用于列标题）
     */
    String name;
    
    /**
     * 对象路径
     */
    String path;
    
    /**
     * 如果评估值为null, 或者未指定path时输出的单元格
     */
    String defaultValue;
    
    /**
     * 单元格样式名称
     */
    String styleName;
    
    /**
     * 单元格样式（Excel规范）
     *  0, "General"
		1, "0"
		2, "0.00"
		3, "#,##0"
		4, "#,##0.00"
		5, "($#,##0_);($#,##0)"
		6, "($#,##0_);[Red]($#,##0)"
		7, "($#,##0.00);($#,##0.00)"
		8, "($#,##0.00_);[Red]($#,##0.00)"
		9, "0%"
		0xa, "0.00%"
		0xb, "0.00E+00"
		0xc, "# ?/?"
		0xd, "# ??/??"
		0xe, "m/d/yy"
		0xf, "d-mmm-yy"
		0x10, "d-mmm"
		0x11, "mmm-yy"
		0x12, "h:mm AM/PM"
		0x13, "h:mm:ss AM/PM"
		0x14, "h:mm"
		0x15, "h:mm:ss"
		0x16, "m/d/yy h:mm"
		
		
		// 0x17 - 0x24 reserved for international and undocumented 0x25, "(#,##0_);(#,##0)"
		
		0x26, "(#,##0_);[Red](#,##0)"
		
		0x27, "(#,##0.00_);(#,##0.00)"
		
		0x28, "(#,##0.00_);[Red](#,##0.00)"
		
		0x29, "_(*#,##0_);_(*(#,##0);_(* \"-\"_);_(@_)"
		
		0x2a, "_($*#,##0_);_($*(#,##0);_($* \"-\"_);_(@_)"
		
		0x2b, "_(*#,##0.00_);_(*(#,##0.00);_(*\"-\"??_);_(@_)"
		
		0x2c, "_($*#,##0.00_);_($*(#,##0.00);_($*\"-\"??_);_(@_)"
		
		0x2d, "mm:ss"
		
		0x2e, "[h]:mm:ss"
		
		0x2f, "mm:ss.0"
		
		0x30, "##0.0E+0"
		
		0x31, "@" - This is text format.
		
		0x31 "text" - Alias for "@"
     */
    String cellFormat;
    
    /**
     * 格式化器用于数据的转换
     */
    Formatter formatter;
    
    /**
     * 单元格宽度
     */
    Short width;
    
    String align;
    
    
    /**
     * @return Returns the defaultValue.
     */
    public String getDefaultValue() {
        return defaultValue;
    }
    /**
     * @return Returns the formatter.
     */
    public Formatter getFormatter() {
        return formatter;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the path.
     */
    public String getPath() {
        return path;
    }
    /**
     * @return Returns the styleName.
     */
    public String getStyleName() {
        return styleName;
    }
    /**
     * @param defaultValue The defaultValue to set.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    /**
     * @param formatter The formatter to set.
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param path The path to set.
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @param styleName The styleName to set.
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    
    
    /**
     * @return Returns the width.
     */
    public Short getWidth() {
        return width;
    }
    /**
     * @param width The width to set.
     */
    public void setWidth(Short width) {
        this.width = width;
    }
    
    
    /**
     * @return Returns the cellFormat.
     */
    public String getCellFormat() {
        return cellFormat;
    }
    /**
     * @param cellFormat The cellFormat to set.
     */
    public void setCellFormat(String cellFormat) {
        this.cellFormat = cellFormat;
    }
    
    
    /**
     * @return Returns the align.
     */
    public String getAlign() {
        return align;
    }
    /**
     * @param align The align to set.
     */
    public void setAlign(String align) {
        this.align = align;
    }
}

