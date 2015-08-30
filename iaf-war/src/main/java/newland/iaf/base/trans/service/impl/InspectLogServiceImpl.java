package newland.iaf.base.trans.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.format.csv.CsvBeanFormat;
import newland.iaf.base.ftp.FtpService;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.dao.impl.HcTransDaoImpl;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransType;
import newland.iaf.base.trans.service.AbstractFtpFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Transactional
@Service("com.newland.iaf.inspectLogService")
public class InspectLogServiceImpl extends AbstractFtpFileService {

	private static Logger log = LoggerFactory
			.getLogger(InspectLogServiceImpl.class);

	@Resource(name = "com.newland.iaf.ftpService")
	protected FtpService ftpService;

	@Resource(name = "com.newland.iaf.hcTransDao")
	protected HcTransDao hcTransDao;

	@Resource(name = "com.newland.iaf.sysParamService")
	protected SysParamService sysParamService;

	public FtpService getFtpService() {
		return ftpService;
	}

	public HcTransDao getHcTransDao() {
		return hcTransDao;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	@Override
	public void fetch(Reader reader, HcTrans ht) throws IOException {
		CsvBeanFormat<HcInspectLog> cbf = new CsvBeanFormat<HcInspectLog>(
				HcInspectLog.class);
		List<HcInspectLog> list = null;
		try {
			list = cbf.parseList(reader, 4);
			log.debug("parse HcInspectLog " + list.size() + " records.");
		} catch (Exception e) {
			log.error("", e);
			return;
		}

		ht.setTransType(HcTransType.inspectLog);
		ht.setRealRecNum(list.size());

		hcTransDao.updateTrans(ht);
		int count = 0;
		for (HcInspectLog hcInspectLog : list) {
			hcInspectLog.setIhcTrans(ht.getIhcTrans());
			hcTransDao.saveInspectLog(hcInspectLog);
			count++;
			if (count % 1000 == 0) {
				HcTransDaoImpl impl = (HcTransDaoImpl) getHcTransDao();
				impl.getSession().flush();
				log.debug("insert HcInspectLog " + count + " rec.");
			}
		}
		log.info("total insert HcInspectLog " + count + " rec.");
		ht.setInsertNum(count);
		hcTransDao.updateTrans(ht);
	}

	@Override
	public void fetch(Date date) throws IOException {
		this.fetchTransLog(SysParamType.serviceLog, date);
	}

	@Override
	public void fetch(String subFixFileName) throws IOException {
		this.fetchTransLog(SysParamType.serviceLog, subFixFileName);
	}

	@Override
	protected HcTransType getTransType() {
		return HcTransType.inspectLog;
	}

	@Override
	protected void readFilehead(HcTrans ht, InputStreamReader isr)
			throws IOException, ParseException {
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		line = br.readLine();
		line = line.substring(5).trim();
		Date time = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(line);
		ht.setExportTime(time);
		line = br.readLine();
		line = line.substring(5).trim();
		ht.setRecNum(Integer.parseInt(line));
	}
}
