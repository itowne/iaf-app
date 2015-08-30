package newland.iaf.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import newland.iaf.utils.file.exception.FileParserException;

public abstract class AbstractFileParser<T> implements FileParser <T> {
	
	private static String _FILETEMP_PREFIX = "wyfs";
	private static String _FILETEMP_SUFFIX = "tmp";

	@Override
	public T convertFromFile(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new FileParserException("file is not found!"
					+(file == null? "null" :file.getAbsolutePath()));
		}
		if(fis == null)
			throw new FileParserException("unable to init a new inputstream!"
					+(file == null? "null" :file.getAbsolutePath()));
		try{
			return convertFromStream(fis);
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
	}

	@Override
	public T convertFromFile(String filePath) {
		File file = new File(filePath);
		return convertFromFile(file);
	}

	@Override
	public File convertToFile(T object) {
		File file = createTempFile();
		convertToFile(object,file);
		return file;
	}

	@Override
	public File convertToFile(T object, String filePath) {
		File file = null;
		if(filePath == null){
			file = createTempFile();
		}else{
			file = new File(filePath);
		}
		convertToFile(object,file);
		return file;
	}
	
	/**
	 * <p>在未传入具体的文件名的条件下，构造临时文件的方式</p>
	 * @return
	 */
	protected File createTempFile(){
		File file = null;
		try {
			file = File.createTempFile(_FILETEMP_PREFIX,_FILETEMP_SUFFIX);
		} catch (IOException e) {
			throw new FileParserException("create tempfile failed!"
					+(file == null? "null" : file.getPath()));
		}
		if(file == null){
			throw new FileParserException("the create tempfile is null?");
		}
		return file;
	}

	protected void convertToFile(T object, File file) {
		if(file == null){
			throw new FileParserException("unable to covert anything into a null file!");
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new FileParserException("file is not found!"
					+(file == null? "null" :file.getPath()),e);
		}
		if(fos == null)
			throw new FileParserException("unable to init a new outputstream!"
					+(file == null? "null" :file.getAbsolutePath()));
		try{
			convertToStream(object,fos);
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	
	protected abstract void convertToStream(T object,OutputStream fos) ;

	protected abstract T convertFromStream(InputStream input) ;

}
