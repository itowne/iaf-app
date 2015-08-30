package newland.iaf.backstage.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import newland.iaf.base.model.SysParam;
import newland.iaf.base.model.dict.SysParamName;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.SysParamService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.Begin;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

/**
 * 后台管理参数管理
 * @author lindaqun
 *
 */
@ParentPackage("struts")
@Namespace("/backstage/sysparam")
@Action("sysParam")
@Results({
	@Result(name = "smsEdit", type = "dispatcher",location = "/backstage/sysparam/smsEdit.jsp"),
	@Result(name = "riskControl", type = "dispatcher",location = "/backstage/sysparam/riskControl.jsp"),
	@Result(name = "merchBaseInfo", type = "dispatcher",location = "/backstage/sysparam/merchBaseInfo.jsp"),
	@Result(name = "transLog", type = "dispatcher",location = "/backstage/sysparam/transLog.jsp"),
	@Result(name = "installLog", type = "dispatcher",location = "/backstage/sysparam/installLog.jsp"),
	@Result(name = "serviceLog", type = "dispatcher",location = "/backstage/sysparam/serviceLog.jsp"),
	@Result(name = "pwdCheck", type = "dispatcher",location = "/backstage/sysparam/checkPasswd.jsp"),
	@Result(name = "jumpTicket", type = "dispatcher",location = "/backstage/sysparam/jumpTicket.jsp"),
	@Result(name = "payUrl", type = "dispatcher",location = "/backstage/sysparam/payUrl.jsp"),
	@Result(name = "imageUrl", type = "dispatcher",location = "/backstage/sysparam/imageUrl.jsp"),
	@Result(name = "expireDate", type = "dispatcher",location = "/backstage/sysparam/expireDate.jsp"),
	@Result(name = "list", type = "JqgridJsonResult"),
	@Result(name = "viewOperLogs", location="/backstage/backstagelog/viewOperLogs.jsp")
    })
public class SysParamAction extends BSBaseAction {
	
	private static final long serialVersionUID = 3763195978953021624L;

	@Resource(name = "com.newland.iaf.sysParamService")
	private SysParamService sysParamService;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private SysParam sysParam;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private SysParam tradeRate_sysParam;
	
	private String ftp_remote_ipaddr;
	
	private String ftp_reomte_path;
	
	private String ftp_remote_port;
	
	private String ftp_file_prefix;
	
	private String ftp_local_path;
	
	private String ftp_username;
	
	private String ftp_passwd;
	
	private String jump_ticket_key;
	
	private String jump_ticket_iv;
	
	private String jump_ticket_expire_second;
	
	private String merch_upload_path;
	
	private String inst_upload_path;
	
	private String organNo;
	
	private String tradePwd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public Map<SysParamName,SysParam> map_organNo;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public Map<SysParamName,SysParam> map_tradePwd;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public SysParam minRate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public SysParam maxRate;
	
	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	public SysParam tradeRate;
	
	
	public String getOrganNo() {
		return organNo;
	}

	public void setOrganNo(String organNo) {
		this.organNo = organNo;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	@In(scope = ScopeType.CONVERSATION)
	@Out(scope = ScopeType.CONVERSATION)
	private Map<SysParamName, SysParam> map;
	
	@Begin
	public String smsEdit() throws Exception{
		Map<SysParamName, SysParam> map = sysParamService.findSysParamMapByType(SysParamType.smsUrl);
		sysParam = map.get(SysParamName.SMS_URL_1);
		return "smsEdit";
	}
	
	public String updateSmsURL() throws Exception{
		try {
			if(sysParam.getIsysParm() == null){
				super.addActionMessage("请先初始化系统参数！");
				return "smsEdit";
			}
			sysParam.setUpdTime(new Date());
			this.sysParamService.updateSysParam(sysParam);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "smsEdit";
	}
	
	@Begin
	public String riskControl() throws Exception{
		Map<SysParamName, SysParam> map = sysParamService.findSysParamMapByType(SysParamType.riskControl);
		//sysParam = map.get(SysParamName.RISK_CONTROL_RATE);
		sysParam = map.get(SysParamName.RISK_CONTROL_POS_TRANS_DAYS);
		tradeRate_sysParam = map.get(SysParamName.RISK_TRADE_RATE);
		
		Map<SysParamName, SysParam> map_rate = sysParamService.findSysParamMapByType(SysParamType.rate);
		minRate =map_rate.get(SysParamName.MIN_RATE);
		maxRate=map_rate.get(SysParamName.MAX_RATE);
		tradeRate=map_rate.get(SysParamName.TRADE_RATE);
		return "riskControl";
	}
	
	public String updateRiskControl() throws Exception{
		try {
			if(sysParam.getIsysParm() == null){
				super.addActionMessage("请先初始化系统参数！");
				return "riskControl";
			}
			sysParam.setUpdTime(new Date());
			this.sysParamService.updateSysParam(sysParam);
			this.sysParamService.updateSysParam(tradeRate_sysParam);
			
			this.sysParamService.updateSysParam(minRate);
			this.sysParamService.updateSysParam(maxRate);
			this.sysParamService.updateSysParam(tradeRate);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "riskControl";
	}
	
	@Begin
	public String merchBaseInfo() throws Exception{
		map = sysParamService.findSysParamMapByType(SysParamType.merchBaseInfo);
		ftp_remote_ipaddr = map.get(SysParamName.FTP_REMOTE_IPADDR).getValue();
		ftp_reomte_path = map.get(SysParamName.FTP_REOMTE_PATH).getValue();
		ftp_remote_port = map.get(SysParamName.FTP_REMOTE_PORT).getValue();
		ftp_file_prefix = map.get(SysParamName.FTP_FILE_PREFIX).getValue();
		ftp_local_path = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
		ftp_username = map.get(SysParamName.FTP_USERNAME).getValue();
		ftp_passwd = map.get(SysParamName.FTP_PASSWD).getValue();
		return "merchBaseInfo";
	}
	
	public String updateMerchBaseInfo() throws Exception{
		try {
			SysParam p1 = map.get(SysParamName.FTP_REMOTE_IPADDR);
			p1.setValue(ftp_remote_ipaddr);p1.setUpdTime(new Date());
			SysParam p2 = map.get(SysParamName.FTP_REOMTE_PATH);
			p2.setValue(ftp_reomte_path);p2.setUpdTime(new Date());
			SysParam p3 = map.get(SysParamName.FTP_REMOTE_PORT);
			p3.setValue(ftp_remote_port);p3.setUpdTime(new Date());
			SysParam p4 = map.get(SysParamName.FTP_FILE_PREFIX);
			p4.setValue(ftp_file_prefix);p4.setUpdTime(new Date());
			SysParam p5 = map.get(SysParamName.FTP_LOCAL_PATH);
			p5.setValue(ftp_local_path);p5.setUpdTime(new Date());
			SysParam p6 = map.get(SysParamName.FTP_USERNAME);
			p6.setValue(ftp_username);p6.setUpdTime(new Date());
			SysParam p7 = map.get(SysParamName.FTP_PASSWD);
			p7.setValue(ftp_passwd);p7.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "merchBaseInfo";
	}
	
	public String transLog(){
		map = sysParamService.findSysParamMapByType(SysParamType.transLog);
		ftp_remote_ipaddr = map.get(SysParamName.FTP_REMOTE_IPADDR).getValue();
		ftp_reomte_path = map.get(SysParamName.FTP_REOMTE_PATH).getValue();
		ftp_remote_port = map.get(SysParamName.FTP_REMOTE_PORT).getValue();
		ftp_file_prefix = map.get(SysParamName.FTP_FILE_PREFIX).getValue();
		ftp_local_path = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
		ftp_username = map.get(SysParamName.FTP_USERNAME).getValue();
		ftp_passwd = map.get(SysParamName.FTP_PASSWD).getValue();
		return "transLog";
	}
	
	public String updateTransLog() throws Exception{
		try {
			SysParam p1 = map.get(SysParamName.FTP_REMOTE_IPADDR);
			p1.setValue(ftp_remote_ipaddr);p1.setUpdTime(new Date());
			SysParam p2 = map.get(SysParamName.FTP_REOMTE_PATH);
			p2.setValue(ftp_reomte_path);p2.setUpdTime(new Date());
			SysParam p3 = map.get(SysParamName.FTP_REMOTE_PORT);
			p3.setValue(ftp_remote_port);p3.setUpdTime(new Date());
			SysParam p4 = map.get(SysParamName.FTP_FILE_PREFIX);
			p4.setValue(ftp_file_prefix);p4.setUpdTime(new Date());
			SysParam p5 = map.get(SysParamName.FTP_LOCAL_PATH);
			p5.setValue(ftp_local_path);p5.setUpdTime(new Date());
			SysParam p6 = map.get(SysParamName.FTP_USERNAME);
			p6.setValue(ftp_username);p6.setUpdTime(new Date());
			SysParam p7 = map.get(SysParamName.FTP_PASSWD);
			p7.setValue(ftp_passwd);p7.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "transLog";
	}
	
	public SysParam getTradeRate_sysParam() {
		return tradeRate_sysParam;
	}

	public void setTradeRate_sysParam(SysParam tradeRate_sysParam) {
		this.tradeRate_sysParam = tradeRate_sysParam;
	}

	public String installLog(){
		map = sysParamService.findSysParamMapByType(SysParamType.installLog);
		ftp_remote_ipaddr = map.get(SysParamName.FTP_REMOTE_IPADDR).getValue();
		ftp_reomte_path = map.get(SysParamName.FTP_REOMTE_PATH).getValue();
		ftp_remote_port = map.get(SysParamName.FTP_REMOTE_PORT).getValue();
		ftp_file_prefix = map.get(SysParamName.FTP_FILE_PREFIX).getValue();
		ftp_local_path = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
		ftp_username = map.get(SysParamName.FTP_USERNAME).getValue();
		ftp_passwd = map.get(SysParamName.FTP_PASSWD).getValue();
		return "installLog";
	}
	
	public String updateInstallLog() throws Exception{
		try {
			SysParam p1 = map.get(SysParamName.FTP_REMOTE_IPADDR);
			p1.setValue(ftp_remote_ipaddr);p1.setUpdTime(new Date());
			SysParam p2 = map.get(SysParamName.FTP_REOMTE_PATH);
			p2.setValue(ftp_reomte_path);p2.setUpdTime(new Date());
			SysParam p3 = map.get(SysParamName.FTP_REMOTE_PORT);
			p3.setValue(ftp_remote_port);p3.setUpdTime(new Date());
			SysParam p4 = map.get(SysParamName.FTP_FILE_PREFIX);
			p4.setValue(ftp_file_prefix);p4.setUpdTime(new Date());
			SysParam p5 = map.get(SysParamName.FTP_LOCAL_PATH);
			p5.setValue(ftp_local_path);p5.setUpdTime(new Date());
			SysParam p6 = map.get(SysParamName.FTP_USERNAME);
			p6.setValue(ftp_username);p6.setUpdTime(new Date());
			SysParam p7 = map.get(SysParamName.FTP_PASSWD);
			p7.setValue(ftp_passwd);p7.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "installLog";
	}
	
	public String serviceLog(){
		map = sysParamService.findSysParamMapByType(SysParamType.serviceLog);
		ftp_remote_ipaddr = map.get(SysParamName.FTP_REMOTE_IPADDR).getValue();
		ftp_reomte_path = map.get(SysParamName.FTP_REOMTE_PATH).getValue();
		ftp_remote_port = map.get(SysParamName.FTP_REMOTE_PORT).getValue();
		ftp_file_prefix = map.get(SysParamName.FTP_FILE_PREFIX).getValue();
		ftp_local_path = map.get(SysParamName.FTP_LOCAL_PATH).getValue();
		ftp_username = map.get(SysParamName.FTP_USERNAME).getValue();
		ftp_passwd = map.get(SysParamName.FTP_PASSWD).getValue();
		return "serviceLog";
	}
	
	public String updateServiceLog() throws Exception{
		try {
			SysParam p1 = map.get(SysParamName.FTP_REMOTE_IPADDR);
			p1.setValue(ftp_remote_ipaddr);p1.setUpdTime(new Date());
			SysParam p2 = map.get(SysParamName.FTP_REOMTE_PATH);
			p2.setValue(ftp_reomte_path);p2.setUpdTime(new Date());
			SysParam p3 = map.get(SysParamName.FTP_REMOTE_PORT);
			p3.setValue(ftp_remote_port);p3.setUpdTime(new Date());
			SysParam p4 = map.get(SysParamName.FTP_FILE_PREFIX);
			p4.setValue(ftp_file_prefix);p4.setUpdTime(new Date());
			SysParam p5 = map.get(SysParamName.FTP_LOCAL_PATH);
			p5.setValue(ftp_local_path);p5.setUpdTime(new Date());
			SysParam p6 = map.get(SysParamName.FTP_USERNAME);
			p6.setValue(ftp_username);p6.setUpdTime(new Date());
			SysParam p7 = map.get(SysParamName.FTP_PASSWD);
			p7.setValue(ftp_passwd);p7.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "serviceLog";
	}
	
	
	@Begin
	public String pwdCheck() throws Exception{
		Map<SysParamName, SysParam> mapPwdCheck = sysParamService.findSysParamMapByType(SysParamType.pwdCheck);
		sysParam = mapPwdCheck.get(SysParamName.PWD_CHECK_URL);
		
		map = sysParamService.findSysParamMapByType(SysParamType.jumpTicketCrypt);
		jump_ticket_expire_second = map.get(SysParamName.JUMP_TICKET_EXPIRE_SECOND).getValue();
		
		return "pwdCheck";
	}
	
	public String updatePwdCheck() throws Exception{
		try {
			if(sysParam.getIsysParm() == null){
				super.addActionMessage("请先初始化系统参数！");
				return "pwdCheck";
			}
			sysParam.setUpdTime(new Date());
			SysParam p3 = map.get(SysParamName.JUMP_TICKET_EXPIRE_SECOND);
			p3.setValue(jump_ticket_expire_second);p3.setUpdTime(new Date());
			this.sysParamService.updateSysParam(sysParam);
			this.sysParamService.updateSysParam(p3);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "pwdCheck";
	}
	
	@Begin
	public String jumpTicket(){
		map = sysParamService.findSysParamMapByType(SysParamType.jumpTicketCrypt);
		jump_ticket_key = map.get(SysParamName.JUMP_TICKET_KEY).getValue();
		jump_ticket_iv = map.get(SysParamName.JUMP_TICKET_IV).getValue();
//		jump_ticket_expire_second = map.get(SysParamName.JUMP_TICKET_EXPIRE_SECOND).getValue();
		return "jumpTicket";
	}
	
	public String updateJumpTicket() throws Exception{
		try {
			SysParam p1 = map.get(SysParamName.JUMP_TICKET_KEY);
			p1.setValue(jump_ticket_key);p1.setUpdTime(new Date());
			SysParam p2 = map.get(SysParamName.JUMP_TICKET_IV);
			p2.setValue(jump_ticket_iv);p2.setUpdTime(new Date());
//			SysParam p3 = map.get(SysParamName.JUMP_TICKET_EXPIRE_SECOND);
//			p3.setValue(jump_ticket_expire_second);p3.setUpdTime(new Date());
//			SysParam p4 = map_organNo.get(SysParamName.ORGANNO);
//			p4.setValue(organNo);p4.setUpdTime(new Date());
//			SysParam p5 = map_tradePwd.get(SysParamName.TRADE_PWD);
//			p5.setValue(tradePwd);p5.setUpdTime(new Date());
//			map.put(SysParamName.ORGANNO, p4);
//			map.put(SysParamName.TRADE_PWD, p5);
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "jumpTicket";
	}
	
	@Begin
	public String imageUrl(){
		map = sysParamService.findSysParamMapByType(SysParamType.merchLogo);
		merch_upload_path = map.get(SysParamName.MERCH_UPLOAD_PATH).getValue();
		map = sysParamService.findSysParamMapByType(SysParamType.instLogo);
		inst_upload_path = map.get(SysParamName.INST_UPLOAD_PATH).getValue();
		return "imageUrl";
	}
	
	public String updateImageUrl() throws Exception{
		try {
			map = sysParamService.findSysParamMapByType(SysParamType.merchLogo);
			SysParam p1 = map.get(SysParamName.MERCH_UPLOAD_PATH);
			p1.setValue(merch_upload_path);p1.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			map = sysParamService.findSysParamMapByType(SysParamType.instLogo);
			SysParam p2 = map.get(SysParamName.INST_UPLOAD_PATH);
			p2.setValue(inst_upload_path);p2.setUpdTime(new Date());
			this.sysParamService.batchUpdateSysParam(map);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "imageUrl";
	}
	
	
	@Begin
	public String payUrl() throws Exception{
		Map<SysParamName, SysParam> map = sysParamService.findSysParamMapByType(SysParamType.payUrl);
		sysParam = map.get(SysParamName.PAY_URL);
		map_organNo =sysParamService.findSysParamMapByType(SysParamType.organNo);
		organNo=map_organNo.get(SysParamName.ORGANNO).getValue();
		map_tradePwd =sysParamService.findSysParamMapByType(SysParamType.tradePwd);
		tradePwd=map_tradePwd.get(SysParamName.TRADE_PWD).getValue();
		return "payUrl";
	}
	
	public String updatePayUrl() throws Exception{
		try {
			sysParam.setUpdTime(new Date());
			if(sysParam.getIsysParm() == null){
				super.addActionMessage("请先初始化系统参数！");
				return "payUrl";
			}
			SysParam p4 = map_organNo.get(SysParamName.ORGANNO);
			p4.setValue(organNo);p4.setUpdTime(new Date());
			SysParam p5 = map_tradePwd.get(SysParamName.TRADE_PWD);
			p5.setValue(tradePwd);p5.setUpdTime(new Date());
			this.sysParamService.updateSysParam(sysParam);
			this.sysParamService.updateSysParam(p4);
			this.sysParamService.updateSysParam(p5);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "payUrl";
	}
	
	@Begin
	public String expireDate() throws Exception{
		Map<SysParamName, SysParam> map = sysParamService.findSysParamMapByType(SysParamType.expireDate);
		sysParam = map.get(SysParamName.EXPIRE_DATE);
		return "expireDate";
	}
	
	public String updateExpireDate() throws Exception{
		try {
			sysParam.setUpdTime(new Date());
			if(sysParam.getIsysParm() == null){
				super.addActionMessage("请先初始化系统参数！");
				return "expireDate";
			}
			this.sysParamService.updateSysParam(sysParam);
			super.addActionMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionError(e.getMessage());
		}
		return "expireDate";
	}
	

	public SysParam getSysParam() {
		return sysParam;
	}

	public void setSysParam(SysParam sysParam) {
		this.sysParam = sysParam;
	}

	public Map<SysParamName, SysParam> getMap_organNo() {
		return map_organNo;
	}

	public void setMap_organNo(Map<SysParamName, SysParam> map_organNo) {
		this.map_organNo = map_organNo;
	}

	public Map<SysParamName, SysParam> getMap_tradePwd() {
		return map_tradePwd;
	}

	public void setMap_tradePwd(Map<SysParamName, SysParam> map_tradePwd) {
		this.map_tradePwd = map_tradePwd;
	}

	public String getFtp_remote_ipaddr() {
		return ftp_remote_ipaddr;
	}

	public void setFtp_remote_ipaddr(String ftp_remote_ipaddr) {
		this.ftp_remote_ipaddr = ftp_remote_ipaddr;
	}

	public SysParam getMinRate() {
		return minRate;
	}

	public void setMinRate(SysParam minRate) {
		this.minRate = minRate;
	}

	public SysParam getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(SysParam maxRate) {
		this.maxRate = maxRate;
	}

	public SysParam getTradeRate() {
		return tradeRate;
	}

	public void setTradeRate(SysParam tradeRate) {
		this.tradeRate = tradeRate;
	}

	public String getFtp_reomte_path() {
		return ftp_reomte_path;
	}

	public void setFtp_reomte_path(String ftp_reomte_path) {
		this.ftp_reomte_path = ftp_reomte_path;
	}

	public String getFtp_remote_port() {
		return ftp_remote_port;
	}

	public void setFtp_remote_port(String ftp_remote_port) {
		this.ftp_remote_port = ftp_remote_port;
	}

	public String getFtp_file_prefix() {
		return ftp_file_prefix;
	}

	public void setFtp_file_prefix(String ftp_file_prefix) {
		this.ftp_file_prefix = ftp_file_prefix;
	}

	public String getFtp_local_path() {
		return ftp_local_path;
	}

	public void setFtp_local_path(String ftp_local_path) {
		this.ftp_local_path = ftp_local_path;
	}

	public String getFtp_username() {
		return ftp_username;
	}

	public void setFtp_username(String ftp_username) {
		this.ftp_username = ftp_username;
	}

	public String getFtp_passwd() {
		return ftp_passwd;
	}

	public void setFtp_passwd(String ftp_passwd) {
		this.ftp_passwd = ftp_passwd;
	}

	public String getJump_ticket_key() {
		return jump_ticket_key;
	}

	public void setJump_ticket_key(String jump_ticket_key) {
		this.jump_ticket_key = jump_ticket_key;
	}

	public String getJump_ticket_iv() {
		return jump_ticket_iv;
	}

	public void setJump_ticket_iv(String jump_ticket_iv) {
		this.jump_ticket_iv = jump_ticket_iv;
	}

	public String getJump_ticket_expire_second() {
		return jump_ticket_expire_second;
	}

	public void setJump_ticket_expire_second(String jump_ticket_expire_second) {
		this.jump_ticket_expire_second = jump_ticket_expire_second;
	}

	public Map<SysParamName, SysParam> getMap() {
		return map;
	}

	public void setMap(Map<SysParamName, SysParam> map) {
		this.map = map;
	}

	public String getMerch_upload_path() {
		return merch_upload_path;
	}

	public void setMerch_upload_path(String merch_upload_path) {
		this.merch_upload_path = merch_upload_path;
	}

	public String getInst_upload_path() {
		return inst_upload_path;
	}

	public void setInst_upload_path(String inst_upload_path) {
		this.inst_upload_path = inst_upload_path;
	}
	
	
	
}
