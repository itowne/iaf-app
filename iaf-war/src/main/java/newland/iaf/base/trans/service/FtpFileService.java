package newland.iaf.base.trans.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.SocketException;
import java.util.Date;

import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.trans.model.HcTrans;

public interface FtpFileService {
	/**
	 * 
	 * @param reader
	 */
	void fetch(Reader reader, HcTrans ht) throws IOException;

	void fetch(InputStream is, HcTrans ht) throws IOException;

	/**
	 * 
	 * @param paramType
	 * @param date
	 * @throws SocketException
	 * @throws IOException
	 */
	void fetchTransLog(SysParamType paramType, Date date)
			throws SocketException, IOException;

	void fetchTransLog(SysParamType paramType, String subFixFileName)
			throws SocketException, IOException;

	void fetch(String fileName, HcTrans ht) throws Exception;

	void fetchResource(String fileName, HcTrans ht) throws Exception;

	void fetch(Date date) throws Exception;

	void fetch(String subFixFileName) throws Exception;

}
