/*
 * 功能：文件下载Result
 * 类名：FileDownResult.java
 *
 *  Ver     变更日期   	 修改人   	修改说明
 * ─────────────────────────
 * 	V1.0  	2012-8-7 	黄博飞         初版
 *
 * Copyright (c) 2006, 2008 NewlandComputer All Rights Reserved.
 * LICENSE INFORMATION
 */
package newland.base.views.result;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 文件下载Result
 * 
 * @author 黄博飞
 * @since 2012-8-7
 */
public class FileDownResult implements Result {
	private static final long serialVersionUID = 4244541560216181304L;
	private static final Log LOG = LogFactory.getLog(FileDownResult.class);
	private static final String DEFAULT_ENCODING = "UTF-8";// 默认文件编码
	private static final String DEFAULT_PARAM_NAME = "downFile";// 默认文件变量名
	public static final String FILE_TYPE_HSSFBOOK = "hssfbook";
	/**
	 * 文件编码
	 */
	private String encoding;
	/**
	 * 文件变量名
	 */
	private String sourceFileName;
	/**
	 * 
	 */
	private String targetFileName;
	/**
	 * 文件类型
	 */
	private String fileType;

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		ValueStack stack = invocation.getStack();
		// 初始化参数
		if (StringUtils.isBlank(encoding)) {
			encoding = DEFAULT_ENCODING;
		}
		if (StringUtils.isBlank(targetFileName)) {
			targetFileName = DEFAULT_PARAM_NAME;
		}
		// 校验文件是否存在
		// 文件类型分为：poi的HSSFBOOK;oi的File
		if (FILE_TYPE_HSSFBOOK.equals(fileType)) {
			downHssfBook(response, stack);
		} else {
			downFile(response, stack);
		}
	}

	/**
	 * 下载HSSFBook类型
	 * 
	 * @param response
	 * @param stack
	 * @throws Exception
	 */
	private void downHssfBook(HttpServletResponse response, ValueStack stack) throws Exception {
		HSSFWorkbook wb = (HSSFWorkbook) stack.findValue(sourceFileName);
		if (wb == null) {
			throw new Exception("Sorry!File Not Exist");
		}
		// 从文件写到页面
		OutputStream outputStream = null;
		try {
			response.setContentType("application/vnd.ms-excel;charset=" + encoding);
			response.addHeader("Content-Disposition", "attachment;filename=\"info" + new Date().getTime() + ".xls");
			outputStream = response.getOutputStream();
			wb.write(outputStream);
			// Flush
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * 下载文件类型
	 * 
	 * @param response
	 * @param stack
	 * @throws Exception
	 */
	private void downFile(HttpServletResponse response, ValueStack stack) throws Exception {
		File downFile = (File) stack.findValue(sourceFileName);
		String tfileName = (String) stack.findValue(targetFileName);
		if (StringUtils.isBlank(tfileName)) tfileName = downFile.getName();
		if (downFile == null || !downFile.exists()) {
			throw new Exception("Sorry!File Not Exist");
		}
		// 从文件写到页面
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.setContentType(parseContentType(tfileName) + ";charset=" + encoding);
			response.addHeader("Content-Disposition", "attachment;filename=\"" + tfileName);
			outputStream = response.getOutputStream();
			// Copy input to output
			LOG.debug("Streaming to output buffer +++ START +++");
			inputStream = FileUtils.openInputStream(downFile);
			byte[] oBuff = new byte[4096];
			int iSize;
			while (-1 != (iSize = inputStream.read(oBuff))) {
				outputStream.write(oBuff, 0, iSize);
			}
			LOG.debug("Streaming to output buffer +++ END +++");
			// Flush
			outputStream.flush();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * 获取文件的ContentType
	 * 
	 * @param fileName
	 * @return
	 */
	private String parseContentType(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "text/plain";
		} else if (fileName.endsWith(".xls")) {
			return "application/vnd.ms-excel";
		}
		return "text/plain";
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

}
