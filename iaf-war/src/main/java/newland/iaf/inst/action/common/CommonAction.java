package newland.iaf.inst.action.common;

import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.OperLog;
import newland.iaf.base.service.LoanOrdService;
import newland.iaf.base.service.OperLogService;
import newland.iaf.inst.action.InstBaseAction;
import newland.iaf.inst.service.InstLoanService;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.service.MerchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;

public class CommonAction extends InstBaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Resource (name = "loanOrdService")
	protected LoanOrdService loanOrdService;

	@Resource (name = "operLogService")
	protected OperLogService operLogService;
	
	@Resource (name = "com.newland.iaf.merchService")
	protected MerchService merchService;
	
	@Resource (name = "instLoanService")
	protected InstLoanService instLoanService;

	
	//TODO 信用评估报告

}
