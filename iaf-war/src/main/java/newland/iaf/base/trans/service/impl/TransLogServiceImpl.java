package newland.iaf.base.trans.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import newland.iaf.base.dao.FundFlowDao;
import newland.iaf.base.dao.TransMsgDao;
import newland.iaf.base.format.csv.CsvBeanFormat;
import newland.iaf.base.ftp.FtpService;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.FundFlowStat;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SettleService;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.dao.impl.HcTransDaoImpl;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransLog;
import newland.iaf.base.trans.model.HcTransType;
import newland.iaf.base.trans.service.AbstractFtpFileService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jhlabs.image.FourColorFilter;
import com.mysql.jdbc.log.Log;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.transLogService")
@Transactional
public class TransLogServiceImpl extends AbstractFtpFileService {

	/**
	 * 
	 */
	protected static Logger log = LoggerFactory
			.getLogger(TransLogServiceImpl.class);

	@Resource(name = "com.newland.iaf.ftpService")
	protected FtpService ftpService;

	@Resource(name = "com.newland.iaf.hcTransDao")
	protected HcTransDao hcTransDao;

	@Resource(name = "com.newland.iaf.sysParamService")
	protected SysParamService sysParamService;
	
	@Resource(name="settleService")
	private SettleService settleService;

	@Resource(name = "transMsgDao")
	public TransMsgDao transMsgDao;

	@Resource(name = "fundFlowDao")
	public FundFlowDao fundFlowDao;
	
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
	public synchronized void fetch(Reader reader, HcTrans ht)
			throws IOException {
		CsvBeanFormat<HcTransLog> cbf = new CsvBeanFormat<HcTransLog>(
				HcTransLog.class);
		List<HcTransLog> list = null;
		try {
			list = cbf.parseList(reader, 3);
			log.debug("get HcTransLog " + list.size() + " records.");
		} catch (Exception e) {
			log.error("parse HcTransLog file meet error.", e);
			throw new RuntimeException("parse HcTransLog file meet error.", e);
		}

		log.info("HcTransLog " + list.size() + " rec.");
		ht.setGenTime(new Date());
		ht.setRealRecNum(list.size());
		hcTransDao.updateTrans(ht);
		int insertNum = 0;
		for (HcTransLog hcTransLog : list) {
			hcTransLog.setIhcTrans(ht.getIhcTrans());
		}
		insertNum = list.size();
		int n = 0;
		HcTransDaoImpl impl = (HcTransDaoImpl) hcTransDao;
		for (HcTransLog hcTransLog : list) {
			BigDecimal hcAmt = hcTransLog.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UNNECESSARY);
			
			String time="";
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			if(hcTransLog.getTransDate()!=null){
				time = s.format(hcTransLog.getTransDate())+" "+hcTransLog.getTransTime();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(time)){
				try {
					Date d=sdf.parse(time);
					hcTransLog.setTransDate(d);
				} catch (ParseException e) {
					log.debug("交易时间转换出错 ! info:[merchNo:"+hcTransLog.getMerchNo()+",i_hc_trans:"+hcTransLog.getIhcTrans()+",order_no:"+hcTransLog.getOrderNo()+"]");
				}
			}
			
			hcTransLog.setAmt(hcAmt);
			hcTransDao.saveTransLog(hcTransLog);
			n++;
			if (n % 100 == 0) {
				impl.getSession().flush();
			}
			if (n % 1000 == 0) {
				log.debug("insert hctranslog " + n);
			}
		}
		impl.getSession().flush();

		log.debug("insert hctranslog " + n + " complete.");
		ht.setInsertNum(insertNum);
		hcTransDao.updateTrans(ht);

		long count = 0;
		long currentTime = System.currentTimeMillis();
		List<HcTransLog> htlList = new ArrayList<HcTransLog>();
		List<TransMsg> tmList = transMsgDao.querySettleRecord();
		if(!CollectionUtils.isEmpty(tmList)){
			for(HcTransLog hcTransLog : list){
				for (TransMsg transMsg : tmList) {
					if(hcTransLog.getOrderNo().substring(4, 20).equals(transMsg.getOtherSysNo())){
						htlList.add(hcTransLog);
					}
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(htlList)){
			for (HcTransLog hcTransLog : htlList) {
				transLogSettle(hcTransLog);
				count++;
				if (count % 100 == 0) {
					log.debug("settle finished rec : " + count);
				}
			}
			log.debug("settle complete! rec:"+htlList.size());
		}
		else{
			log.debug("settle complete! rec:"+htlList.size());
		}
		long finishedTime = System.currentTimeMillis();
		log.debug("settle execute time:"+(finishedTime-currentTime)/1000f+" sec.");
	}

	// 根据汇卡公司提供的交易清算文件，变更放款、还款订单状态
	@SuppressWarnings("unused")
	public void transLogSettle(HcTransLog hcTransLog) {
		try {
			settleService.settle(hcTransLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("执行交易清算异常 !",e);
		}
	}

	protected void readFilehead(HcTrans ht, InputStreamReader isr)
			throws IOException, ParseException {
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		line = br.readLine();
		line = line.substring(5).trim();
		Date time = null;
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(line);
		ht.setExportTime(time);
	}

	@Override
	public void fetch(Date date) throws SocketException, IOException {
		this.fetchTransLog(SysParamType.transLog, date);
	}

	@Override
	public void fetch(String subFixFileName) throws IOException {
		this.fetchTransLog(SysParamType.transLog, subFixFileName);
	}

	@Override
	protected HcTransType getTransType() {
		return HcTransType.transLog;
	}
}
