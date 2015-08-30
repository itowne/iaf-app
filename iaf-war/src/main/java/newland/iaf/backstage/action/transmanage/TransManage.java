package newland.iaf.backstage.action.transmanage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.backstage.action.BSBaseAction;
import newland.iaf.backstage.schedule.impl.LoanCurrentStatisticsSchedule;
import newland.iaf.backstage.schedule.impl.LoanOrdTraceSchedule;
import newland.iaf.backstage.schedule.impl.LoanStatisticsSchedule;
import newland.iaf.base.risk.dao.RiskControlService;
import newland.iaf.base.trans.service.FtpFileService;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author rabbit
 * 
 */
@ParentPackage("struts")
@Namespace("/backstage/transmanage")
@Action(value = "transmanage")
@Results({
		@Result(name = "success", type = "dispatcher", location = "/backstage/transmanage/transmanage.jsp"),
		@Result(name = "runok", type = "dispatcher", location = "/backstage/transmanage/runok.jsp"),
		@Result(name = "error", type = "dispatcher", location = "/backstage/transmanage/error.jsp"),
		@Result(name = "schedule", type = "dispatcher", location = "/backstage/transmanage/schedule.jsp") })
public class TransManage extends BSBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -440462743663417245L;

	private static Logger log = LoggerFactory.getLogger(TransManage.class);

	private DateFormat df = new SimpleDateFormat("yyyyMMdd");

	private DateFormat mouthFormat = new SimpleDateFormat("yyyyMM");

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.merchBaseInfoService")
	private FtpFileService merchBaseInfoService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.inspectLogService")
	private FtpFileService inspectLogService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.transLogService")
	private FtpFileService transLogService;

	/**
	 * 
	 */
	@Resource(name = "com.newland.iaf.installLogService")
	private FtpFileService installLogServie;

	/**
	 * 
	 */
	@Resource(name = "schedule.loanOrdTrackSchedule")
	private LoanOrdTraceSchedule loanOrdTraceSchedule;

	/**
	 * 
	 */
	@Resource(name = "schedule.loanStatisticsSchedule")
	private LoanStatisticsSchedule loanStatisticsSchedule;

	/**
	 * 
	 */
	@Resource(name = "schedule.loanCurrentStatisticsSchedule")
	private LoanCurrentStatisticsSchedule currentSchedule;

	/**
	 * 
	 */
	@Resource(name = "com.newland.riskControlService")
	private RiskControlService riskControlService;

	private String msg;

	private String merchFileName;

	private String inspectFileName;

	private String transBeginDate;

	private String transEndDate;

	private String installFileName;

	private String transMouthBeginDate;

	private String transMouthEndDate;

	public String execute() {
		String s = df.format(IafApplication.getYestoday(new Date()));
		merchFileName = s;
		inspectFileName = s;
		transBeginDate = s;
		transEndDate = s;
		installFileName = s;
		String m = mouthFormat.format(IafApplication.getYestoday(new Date()));
		transMouthBeginDate = m;
		transMouthEndDate = m;
		return SUCCESS;
	}

	public String ftpMerchBase() throws Exception {
		try {
			merchBaseInfoService.fetch(merchFileName);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		return "runok";
	}

	//巡检报告信息
	public String ftpInspectLog() throws Exception {
		try {
			inspectLogService.fetch(inspectFileName);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		return "runok";
	}
	//交易流水信息
	public String ftpTransLog() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date begin = df.parse(transBeginDate);
		begin = DateUtils.truncate(begin, Calendar.DAY_OF_MONTH);
		Date end = df.parse(transEndDate);
		end = DateUtils.truncate(end, Calendar.DAY_OF_MONTH);
		while (!begin.after(end)) {
			try {
				transLogService.fetch(begin);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
				super.addActionMessage(e.getMessage());
				return "error";
			}
			begin = DateUtils.addDays(begin, 1);
		}
		return "runok";
	}
	//装机记录信息
	public String ftpInstallLog() throws Exception {
		try {
			installLogServie.fetch(installFileName);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		return "runok";
	}
	//交易流水月份汇总
	public String genTransLogMouth() throws Exception {
		Date begin = mouthFormat.parse(transMouthBeginDate);
		Date end = mouthFormat.parse(transMouthEndDate);
		try {
			riskControlService.genTransLogMouth(begin, end);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		
		return "runok";
	}
	//执行订单跟踪定时器
	public String loanTrace() throws Throwable {
		try {
			this.loanOrdTraceSchedule.run();
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		
		return "runok";
	}
	
	public String loanStatistics() throws Throwable {
		try {
			this.loanStatisticsSchedule.run();
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		
		return "runok";
	}
	
	public String statisticsCurrent() throws Throwable {
		try {
			this.currentSchedule.run();
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage(e.getMessage());
			return "error";
		}
		return "runok";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMerchFileName() {
		return merchFileName;
	}

	public void setMerchFileName(String merchFileName) {
		this.merchFileName = merchFileName;
	}

	public String getInspectFileName() {
		return inspectFileName;
	}

	public void setInspectFileName(String inspectFileName) {
		this.inspectFileName = inspectFileName;
	}

	public String getTransBeginDate() {
		return transBeginDate;
	}

	public void setTransBeginDate(String transBeginDate) {
		this.transBeginDate = transBeginDate;
	}

	public String getTransEndDate() {
		return transEndDate;
	}

	public void setTransEndDate(String transEndDate) {
		this.transEndDate = transEndDate;
	}

	public String getInstallFileName() {
		return installFileName;
	}

	public void setInstallFileName(String installFileName) {
		this.installFileName = installFileName;
	}

	public String getTransMouthBeginDate() {
		return transMouthBeginDate;
	}

	public void setTransMouthBeginDate(String transMouthBeginDate) {
		this.transMouthBeginDate = transMouthBeginDate;
	}

	public String getTransMouthEndDate() {
		return transMouthEndDate;
	}

	public void setTransMouthEndDate(String transMouthEndDate) {
		this.transMouthEndDate = transMouthEndDate;
	}

}
