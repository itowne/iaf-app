package newland.iaf.base.trans.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.format.csv.CsvBeanFormat;
import newland.iaf.base.ftp.FtpService;
import newland.iaf.base.model.FundFlow;
import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.condition.FundFlowCondition;
import newland.iaf.base.model.dict.CreditType;
import newland.iaf.base.model.dict.ForbidenType;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.model.dict.SysParamType;
import newland.iaf.base.service.FundFlowService;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.SysParamService;
import newland.iaf.base.trans.dao.HcTransDao;
import newland.iaf.base.trans.model.HcMerchBaseInfo;
import newland.iaf.base.trans.model.HcTrans;
import newland.iaf.base.trans.model.HcTransType;
import newland.iaf.base.trans.service.AbstractFtpFileService;
import newland.iaf.merch.dao.MerchDao;
import newland.iaf.merch.dao.MerchUserDao;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchInfoAppeal;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.model.MerchUser;
import newland.iaf.merch.model.MerchUserType;
import newland.iaf.merch.service.MerchCreditReportService;
import newland.iaf.merch.service.MerchInfoAppealService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.merch.service.MerchUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.iaf.merchBaseInfoService")
@Transactional
public class MerchBaseInfoServiceImpl extends AbstractFtpFileService {

	@Resource(name = "com.newland.iaf.merchDao")
	private MerchDao merchdao;

	@Resource(name = "com.newland.iaf.merchUserService")
	private MerchUserService merchUserService;
	
	@Resource(name = "merchCreditReportService")
	private MerchCreditReportService merchCreditReportService;

	@Resource(name = "com.newland.iaf.merchUserDao")
	private MerchUserDao merchUserDao;
	
	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;
	
	@Resource(name = "loanOrdService")
	private LoanOrdService loanOrdService;
	
	@Resource(name = "fundFlowService")
	private FundFlowService fundFlowService;
	
	@Resource(name="merchInfoAppealService")
	private MerchInfoAppealService merchInfoAppealService;

	protected static Logger log = LoggerFactory
			.getLogger(MerchBaseInfoServiceImpl.class);
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
	public void fetch(Reader reader, HcTrans trans) {
		// CsvBeanFormat cbf = new CsvBeanFormat();
		CsvBeanFormat<HcMerchBaseInfo> cbf = new CsvBeanFormat<HcMerchBaseInfo>(
				HcMerchBaseInfo.class);
		List<HcMerchBaseInfo> list = null;
		try {
			// list = cbf.parseList(reader, 4, HcMerchBaseInfo.class);
			list = cbf.parseList(reader, 4);
			log.debug("get HcMerchBaseInfo " + list.size() + " records.");
		} catch (Exception e) {
			log.error("", e);
			return;
		}
		log.info("HcMerchBaseInfo " + list.size() + " rec.");
		trans.setGenTime(new Date());
		trans.setRealRecNum(list.size());
		trans.setTransType(HcTransType.merchBaseInfo);
		hcTransDao.updateTrans(trans);

		hcTransDao.saveMerchBaseInfo(list);
		trans.setInsertNum(list.size());
		hcTransDao.updateTrans(trans);

		long n = 0;
		for (HcMerchBaseInfo info : list) {
			updateMerchInfo(info);
			n++;
			if (n % 100 == 0) {
				log.debug("updateMerchInfo " + n);
			}
		}
	}

	private void updateMerchInfo(HcMerchBaseInfo info) {
		String merchNo = info.getHcMerchNo();
		Merch merch = new Merch();
		merch.setMerchNo(merchNo);
		boolean isNew = !merchdao.hasMerch(merch);
		if (isNew) {
			merch = new Merch();
			merch.setMerchNo(info.getHcMerchNo());
			merch.setMerchType(MerchType.GOLD_SHOPKEEPER);
			copyTo(info, merch);
			if(info.getOriginalTime()==null||info.getOriginalTime()==""){
				throw new RuntimeException("数据同步失败!!! --- 原因:提供导入文件缺少录入时间! \n 信息:商户号=["+info.getHcMerchNo()+"]");
			}
			merchdao.saveMerch(merch);
			if(merch!=null){
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.BASIC_INFO, AcceptType.getAcceptType(true));
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.FIELD_SURVY, AcceptType.getAcceptType(true));
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.OTHER_INFO, AcceptType.getAcceptType(true));
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.ROUTING_ISPECTION, AcceptType.getAcceptType(true));
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.TRANSFER, AcceptType.getAcceptType(true));
				this.merchCreditReportService.setInfoPermission(merch,
						PagePosition.INSTALL, AcceptType.getAcceptType(true));
			}
		} else {
			merch = merchdao.getMerchByMerchNo(merchNo);
			copyTo(info, merch);
			if(info.getOriginalTime()==null||info.getOriginalTime()==""){
				throw new RuntimeException("数据同步失败!!! --- 原因:提供导入文件缺少录入时间! \n 信息:商户号=["+info.getHcMerchNo()+"]");
			}
			merchdao.updateMerch(merch);
		}

		Long imerch = merch.getImerch();
		MerchLegalPers legalPer = null;
		if (isNew) {
			legalPer = new MerchLegalPers();
			legalPer.setImerch(merch.getImerch());
			copyTo(info, legalPer);
			merchdao.saveMerchLegalPers(legalPer);
		} else {
			legalPer = merchdao.getMerchLegalPersByImerch(imerch);
			copyTo(info, legalPer);
			merchdao.updateMerchLegalPers(legalPer);
		}

		MerchBusiInfo busiInfo = null;
		if (isNew) {
			busiInfo = new MerchBusiInfo();
			busiInfo.setImerch(merch.getImerch());
			copyTo(info, busiInfo);
			merchdao.saveMerchBusiInfo(busiInfo);
		} else {
			busiInfo = merchdao.getMerchBusiInfoByImerch(imerch);
			copyTo(info, busiInfo);
			merchdao.updateMerchBusiInfo(busiInfo);
		}

		String loginName = info.getMerchLoginName();
		//System.out.println("loginName="+loginName);
		if(loginName==null){
			throw new RuntimeException("数据同步失败!!! --- 原因:提供导入文件缺少登录名! \n 信息:商户号=["+info.getHcMerchNo()+"]");
		}
		 
		List<MerchUser> merchUserList = merchUserService.getMerchUserByLoginName(loginName);
		MerchUser usr = null;
		if (isNew) {
			usr = new MerchUser();
			usr.setMerchNo(merchNo);
			usr.setImerch(merch.getImerch());
			if(merchUserList.size() != 0){
				throw new RuntimeException("数据同步失败!!! --- 原因：导入文件新增商户登录名与其他商户登录名重名. \n 信息:商户号=["
						+ merchNo + "], 登录名=[" + loginName
						+ "]");
			}
			usr.setLoginName(info.getMerchLoginName());
			
			usr.setUserType(MerchUserType.hicard);
			usr.setGenTime(new Date());
			usr.setMerchStat(ForbidenType.USED);
			usr.setUpdTime(new Date());
			merchUserDao.saveMerchUser(usr);
		} else {
			 if(merchUserList.size() >= 1){
				 for(MerchUser merchU : merchUserList){
					 if(!merchU.getMerchNo().equals(merchNo)){
						 throw new RuntimeException("数据同步失败!!! --- 原因：导入文件商户登录名与其他商户登录名重名. \n 信息:商户号=["
									+ merchNo + "], 登录名=[" + loginName
									+ "]");
					 }
				 }
			 }
			 usr = merchUserDao.getMerchUserByMerchNo(info.getHcMerchNo());
			 copyTo(info,usr);
			 merchUserDao.updateMerchUser(usr);
//			usr = merchUserService.getMerchUser(merchNo, loginName,
//					MerchUserType.hicard);
//			if (usr == null) {
//				throw new RuntimeException("数据同步失败!!! --- 原因：导入文件对应登录名被修改或与其他商户登录名重名. \n 信息:商户号=["
//						+ merch.getMerchNo() + "], 登录名=[" + loginName
//						+ "]");
//			}
//			//同merchNo，同merchName直接覆盖 
//			imerch = usr.getImerch();
//			if (imerch.equals(merch.getImerch())&&!usr.getMerchNo().equals(merch.getMerchNo())) {
//				throw new RuntimeException("数据同步失败!!! --- 原因：同步文件登录名重复 . \n 信息:商户号=["
//						+ merch.getMerchNo() + "], 登录名=[" + loginName
//						+ "]");
//			}
		}
		//查找商户同步文件的merchName，如果不同，做同步操作
		if(!isNew){
			//_merch是本地的商户
			Merch _merch = merchdao.getMerchByMerchNo(merchNo);
			if(!_merch.getMerchName().equals(info.getMerchName())){
				//更新产品订单中商户名称
				List<LoanOrd> loanOrdList = loanOrdService.queryByImerch( _merch.getImerch());
				for (LoanOrd loanOrd : loanOrdList) {
					loanOrd.setMerchName(info.getMerchName());
					try {
						loanOrdService.updateLoanOrd(loanOrd);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//更新商户申诉表的商户名称
				List<MerchInfoAppeal> merchInfoAppealList= this.merchInfoAppealService.findAppealByMerchNo(merchNo);
				for (MerchInfoAppeal mia : merchInfoAppealList){
					mia.setMerchName(info.getMerchName());
					merchInfoAppealService.updateMerchInfoAppeal(mia);
				}
			}
			//更新fundFlow中的商户名称
			FundFlowCondition fundFlowCon = new FundFlowCondition();
			fundFlowCon.setImerch(_merch.getImerch());
			List<FundFlow> fundFlowLists = fundFlowService.queryFundFlowBy(fundFlowCon);
			for(FundFlow fundflow : fundFlowLists){
				fundflow.setMerchName(info.getMerchName());
				fundFlowService.update(fundflow,fundflow.getStatus());
			} 
		}
	}

	private void copyTo(HcMerchBaseInfo src, Merch merch) {
		merch.setMerchName(src.getMerchName());
		// merch.setRegNo("");
		merch.setRegAddr(src.getRegAddr());
		merch.setRegTime(src.getRegTime());
		merch.setPosAddr(src.getPosAddr());
		merch.setBusinlic(src.getBusinlic());
		merch.setTaxReg(src.getTaxRegNo());
		merch.setOpenTime(src.getOpenTime());
		merch.setRegCap(src.getRegCap());
		merch.setContract(src.getContract());
		merch.setContractTel(src.getContractTel());
		//System.out.println("oriT="+src.getOriginalTime());
		merch.setOriginalTime(src.getOriginalTime());
		merch.setCredit(CreditType.C);
		merch.setBankCode(src.getBankCode());
		merch.setIndustry(src.getMerchType());
		merch.setMerchStatus(src.getMerchStatus());
		// merch.setEmail("");
		// merch.setMerchType(info.getMerchType());
		// merch.setCredit();
		// merch.setIndustry();
		// merch.setCompanySize(info.ge)
		// merch.setAuditAddr(auditAddr)
		// merch.setAudirBusinlic(audirBusinlic);
	}

	private void copyTo(HcMerchBaseInfo src, MerchLegalPers merchLegalPers) {
		merchLegalPers.setLegalPersName(src.getLegalPerName());
		merchLegalPers.setCerdNo(src.getLegalPerCerdId());
	}

	private void copyTo(HcMerchBaseInfo src, MerchBusiInfo target) {
		target.setAccountNo(src.getSettleAccountNo());
		target.setBank(src.getSettleBank());
		target.setMerchNatrue(src.getSettleBank());
		target.setAccountName(src.getSettleAccountName());
		target.setMerchNatrue(src.getNature());
		target.setBankCode("--");

	}
	
	private void copyTo(HcMerchBaseInfo src, MerchUser target) {
		target.setLoginName(src.getMerchLoginName());
		target.setUserType(MerchUserType.hicard);
		target.setGenTime(new Date());
		target.setMerchStat(ForbidenType.USED);
		target.setUpdTime(new Date());
	}

	public void fetch(Date date) throws Exception {
		this.fetchTransLog(SysParamType.merchBaseInfo, date);
	}

	public void fetch(String subFixFileName) throws Exception {
		this.fetchTransLog(SysParamType.merchBaseInfo, subFixFileName);
	}

	@Override
	protected HcTransType getTransType() {
		return HcTransType.merchBaseInfo;
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
