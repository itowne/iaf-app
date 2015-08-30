package newland.iaf.base.trans.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import newland.iaf.base.ftp.FtpService;
import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.dao.impl.HcTransDaoImpl;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransStatus;
import newland.iaf.base.trans.model.HcTransType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Transactional
public abstract class AbstractFtpFileService implements FtpFileService {

	private static Logger log = LoggerFactory
			.getLogger(AbstractFtpFileService.class);

	protected static final String charSet = "gb18030";

	protected abstract HcTransType getTransType();

	@Override
	public void fetchTransLog(SysParamType paramType, Date date)
			throws SocketException, IOException {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String sub = df.format(date);
		fetchTransLog(paramType, sub);
	}

	@Override
	public void fetchTransLog(SysParamType paramType, String subFixfileName)
			throws SocketException, IOException {

		Map<SysParamName, SysParam> map = getSysParamService()
				.findSysParamMapByType(paramType);
		log.debug(paramType + "map size=" + map.size());
		String fileName = map.get(SysParamName.FTP_FILE_PREFIX).getValue()
				+ subFixfileName;
		String remotePath = map.get(SysParamName.FTP_REOMTE_PATH).getValue();
		int remotePort = Integer.parseInt(map.get(SysParamName.FTP_REMOTE_PORT)
				.getValue());
		String remoteAddr = map.get(SysParamName.FTP_REMOTE_IPADDR).getValue();
		String localPath = map.get(SysParamName.FTP_LOCAL_PATH).getValue();

		String username = map.get(SysParamName.FTP_USERNAME).getValue();
		String passwd = map.get(SysParamName.FTP_PASSWD).getValue();
		
		String local = getFtpService().ftpGet(remoteAddr, remotePort,
				remotePath, fileName, localPath, username, passwd);
		log.debug("local name :"+local);
		HcTrans ht = new HcTrans();
		ht.setRemoteFile(fileName);
		fetch(local, ht);
	}

	@Override
	public void fetch(InputStream is, HcTrans ht) throws IOException {
		InputStreamReader isr = null;
		try {
			byte[] buf = IOUtils.toByteArray(is);
			byte[] sha = DigestUtils.sha(buf);
			ht.setSize((long) buf.length);
			ht.setSha1(Base64.encodeBase64String(sha));
			ht.setTransType(getTransType());
			
			isr = new InputStreamReader(new ByteArrayInputStream(buf), charSet);
			readFilehead(ht, isr);
			/**
			 * 判断是否已经导入成功
			 */
			ht.setStatus(HcTransStatus.success);
			String local = ht.getLocalFile();
			ht.setLocalFile(null);
			if (getHcTransDao().hasTrans(ht)) {
				log.debug("ignore file=" + ht.getLocalFile() + ", sha1="
						+ ht.getSha1() + ", size=" + ht.getSize());
				ht.setStatus(HcTransStatus.ignore);
				ht.setGenTime(new Date());
				getHcTransDao().saveTrans(ht);
				return;
			} else {
				ht.setGenTime(new Date());
				ht.setLocalFile(local);
				ht.setStatus(HcTransStatus.start);
			}

			getHcTransDao().saveTrans(ht);
			isr = new InputStreamReader(new ByteArrayInputStream(buf), charSet);
			fetch(isr, ht);
			
			HcTransDaoImpl impl = (HcTransDaoImpl) getHcTransDao();
			impl.getSession().flush();

			ht.setStatus(HcTransStatus.success);
			ht.setUpdTime(new Date());
			getHcTransDao().updateTrans(ht);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ht.setStatus(HcTransStatus.error);
			ht.setUpdTime(new Date());
			ht.setMemo(e.getMessage());
			if (ht.getIhcTrans() == null) {
				if(ht.getGenTime()==null){
					ht.setGenTime(new Date());
				}
				getHcTransDao().saveTrans(ht);
			} else {
				getHcTransDao().updateTrans(ht);
			}
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(isr);
		}
	}

	protected abstract void readFilehead(HcTrans ht, InputStreamReader isr)
			throws IOException, ParseException;

	@Override
	public void fetchResource(String fileName, HcTrans ht) throws IOException {
		InputStream is = null;
		try {
			ht.setLocalFile(fileName);
			// is = ClassLoader.getSystemResourceAsStream(fileName);
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			org.springframework.core.io.Resource resource = resourcePatternResolver
					.getResource("classpath:" + fileName);
			if (resource.isReadable() == false)
				throw new RuntimeException("文件不存在");
			is = resource.getInputStream();
			if (is == null) {
				throw new RuntimeException("could not found file[" + fileName
						+ "].");
			}
			fetch(is, ht);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Override
	public void fetch(String fileName, HcTrans ht) throws IOException {
		FileInputStream fis = null;
		try {
			ht.setLocalFile(fileName);
			fis = new FileInputStream(fileName);
			fetch(fis, ht);
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}

	public abstract FtpService getFtpService();

	public abstract HcTransDao getHcTransDao();

	public abstract SysParamService getSysParamService();

}
