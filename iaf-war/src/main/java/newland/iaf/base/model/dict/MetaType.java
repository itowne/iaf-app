package newland.iaf.base.model.dict;

public enum MetaType {
	/**
	 * 文档
	 */
	DOC("application/msword"),
	/**
	 * PDF文件
	 */
	PDF("application/pdf"),
	/**
	 * PPT文档
	 */
	PPT("appication/powerpoint"),
	/**
	 * 2003文档
	 */
	XLS("application/vnd.ms-excel"),
	/**
	 * ZIP
	 */
	ZIP("application/zip"),
	/**
	 * JPG
	 */
	JPG("image/jpeg"),
	/**
	 * TXT
	 */
	TXT("text/plain"),
	/**
	 * JSON
	 */
	JSON("text/json"),
	/**
	 * HTML
	 */
	HTML("text/html"),
	/**
	 * PBM
	 */
	PBM("image/x-portable-bitmap"),
	/**
	 * PNG
	 */
	PNG("image/png"),
	/**
	 * LOGO图片
	 */
	GIF("image/gif");
	String desc;
	MetaType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
}
