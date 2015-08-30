package newland.iaf.base.risk.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.model.HcTransLogMouth;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.riskControlService")
@Transactional
public class RiskControlServiceImpl implements RiskControlService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	@Resource(name = "com.newland.riskControlDao")
	private RiskControlDao riskControlDao;

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;

	@Override
	public void genTransLogMouth(Date date) {
		if (logger.isDebugEnabled()) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			logger.debug(df.format(date));
		}
		if (riskControlDao.hasTransLogMouth(date)) {
			riskControlDao.deleteTransLogMouth(date);
		}
		riskControlDao.insertTransLogMouth(date);
	}

	@Override
	public void genTransLogMouth(Date begin, Date end) {
		if (begin.after(end)) {
			Date t = begin;
			begin = end;
			end = t;
		}
		Date d = DateUtils.truncate(begin, Calendar.DAY_OF_MONTH);
		while (!d.after(end)) {
			genTransLogMouth(d);
			d = DateUtils.addMonths(d, 1);
			d = DateUtils.truncate(d, Calendar.DAY_OF_MONTH);
		}
	}

	@Override
	public List<HcTransLogMouth> getTransLogMouth(String merchNo, Date mouth,
			int length) {
		return riskControlDao.getList(merchNo, mouth, length);
	}

	@Override
	public int getUninstallPosNum(String merchNo, Date date) {
		return riskControlDao.countUninstallNum(merchNo, date);
	}

	@Override
	public BigDecimal getHcTransLogAmt(String merchNo, Date mouth) {
		Date begin = DateUtils.truncate(mouth, Calendar.MONTH);
		Date end = DateUtils.addMonths(begin, 1);
		return riskControlDao.getHcTransLogAmt(merchNo, begin, end);
	}

	public BigDecimal transLogAmtReduce(String merchNo) {
		Date now = new Date();
		Date mouth = DateUtils.addMonths(now, -1);
		BigDecimal a = getHcTransLogAmt(merchNo, mouth);
		BigDecimal b = getHcTransLogAmt(merchNo, DateUtils.addMonths(mouth, -1));
		if(b.intValue()==0){
			return new BigDecimal(0);
		}else{
			BigDecimal tmp = a.divide(b,2);
			BigDecimal r = BigDecimal.ONE.subtract(tmp);
			return r;
		}
	}

	@Override
	public boolean hasTransLog(String merchNo) {
		Date now = new Date();
		SysParam param = sysParamService.getSysParam(SysParamType.riskControl,
				SysParamName.RISK_CONTROL_POS_TRANS_DAYS);
		int days=0;
		if(param!=null){
			days = Integer.parseInt(param.getValue());
		}
		Date d = DateUtils.addDays(now, -days);
		return riskControlDao.hasTransLog(merchNo, d);
	}
}
