package newland.iaf.utils.file.exception;

import java.io.FileNotFoundException;



public class FileParserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -691730240165400252L;
	
	

	public FileParserException(String string) {
		super(string);
	}

	public FileParserException(String string, Throwable e) {
		super(string, e);
	}

}
