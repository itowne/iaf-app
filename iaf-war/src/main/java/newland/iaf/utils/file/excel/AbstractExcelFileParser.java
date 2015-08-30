package newland.iaf.utils.file.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import newland.iaf.utils.file.AbstractFileParser;
import newland.iaf.utils.file.exception.FileParserException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class AbstractExcelFileParser<T> extends AbstractFileParser<T> {
	
	@Override
	public T convertFromStream(InputStream input) {
		Workbook wb;
		try {
			wb = WorkbookFactory.create(input);
		} catch (InvalidFormatException e) {
			throw new FileParserException("unexpected-format exception!",e);
		} catch (IOException e) {
			throw new FileParserException("read workbook failed!",e);
		}
		if(wb == null)
			throw new FileParserException("read workbook failed!the result workbook is null?");
		return convertFromWorkbook(wb);
	}

	@Override
	protected void convertToStream(T object, OutputStream fos) {
		if(fos == null)
			throw new FileParserException("unable to write to null outputstream!");
		
		Workbook wb = new HSSFWorkbook();
		
		convertToWorkbook(object,wb);
		
		try {
			wb.write(fos);
		} catch (IOException e) {
			throw new FileParserException("write workbook failed!",e);
		}
	}
	
	protected abstract T convertFromWorkbook(Workbook wb);
	
	protected abstract void convertToWorkbook(T o,Workbook wb);

}
