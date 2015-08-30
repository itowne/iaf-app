package newland.iaf.base.service;

import java.math.BigInteger;
import java.util.List;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.LoanPdtHis;
import newland.iaf.base.model.condition.LoanPdtCondition;
import newland.iaf.base.model.dict.OperType;
import newland.iaf.base.service.impl.IafConsoleSession;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 
 * @author rabbit
 * 
 */
public interface LoanPdtService {
	/**
	 * 增加贷款产品
	 * 
	 * @param loanPdt
	 */
	void addLoanPdtService(LoanPdt loanPdt) throws Exception ;

	/**
	 * 修改贷款产品
	 * 
	 * @param loanPdt
	 */
	void updateLoanPdt(LoanPdt loanPdt) throws Exception ;

	/**
	 * 根据id查询贷款产品
	 * @param id
	 * @return
	 */
	LoanPdt getLoanPdtById(Long iloanPdt) throws Exception;
	
	/**
	 * 根据id查询贷款产品
	 * @param id
	 * @return
	 */
	LoanPdtHis getLoanPdtHisById(Long iloanPdtHis) throws Exception;
	/**
	 * 多条件查询产品列表
	 * 产品名称，放贷机构，年利率 (%)，产品金额，产品周期，受理地区
	 * @return
	 */
	List<LoanPdt> queryLoanPdtByCon(LoanPdtCondition loanPdtCondition,Page page) throws Exception;

	/**
	 * 删除借款产品信息
	 * @param loanPdt 借款产品信息
	 * @throws Exception
	 */
	void deleteLoanPdt (LoanPdt loanPdt) throws Exception ;
	
	/**
	 * 同步方法，更新产品的请求次数
	 * @param loanPdt
	 * @throws Exception
	 */
	void updateLoanPdtReqTotal(LoanPdt loanPdt) throws Exception;
	/**
	 * 机构产品数量
	 * @return
	 */
	BigInteger countPdt(Long iinst);
	/**
	 * 平台产品数量
	 * @return
	 */
	BigInteger countPdt();
	/**
	 * 平台上架产品数量
	 * @return
	 */
	BigInteger countGroundingPdt();
	/**
	 * 增加贷款产品(后台)
	 * 
	 * @param loanPdt
	 */
	void addLoanPdtServiceBackstage(LoanPdt loanPdt,Long iinst,IafConsoleSession session, String ipaddr) throws Exception ;

	/**
	 * 修改贷款产品（后台）
	 * 
	 * @param loanPdt
	 */
	void updateLoanPdtBackstage(LoanPdt loanPdt,Long iinst,IafConsoleSession session,String ipaddr,OperType operType) throws Exception ;
	/**
	 * 删除借款产品信息（后台）
	 * @param loanPdt 借款产品信息
	 * @throws Exception
	 */
	void deleteLoanPdtBackstage (LoanPdt loanPdt,Long iinst,IafConsoleSession session, String ipaddr) throws Exception ;
	/**
	 * 查询所有产品
	 * @return
	 */
	List<LoanPdt> queryAll();
	
	LoanPdt queryByLoanPdtId(String loanPdtId);
}
