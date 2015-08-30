package newland.iaf.base.dao;

import java.math.BigInteger;
import java.util.List;

import newland.iaf.base.model.LoanPdt;
import newland.iaf.base.model.condition.LoanPdtCondition;

import org.ohuyo.commons.query.criterion.Page;

/**
 * 
 * @author rabbit
 * 
 */
public interface LoanPdtDao {

	void saveLoanPdt(LoanPdt loanPdt);

	void updateLoanPdt(LoanPdt loanPdt);

	LoanPdt getLoanPdtById(Long id);
	/**
	 * 多条件查询产品列表
	 * 产品名称，放贷机构，年利率 (%)，产品金额，产品周期，受理地区
	 * @return
	 */
	List<LoanPdt> queryLoanPdtByCon(LoanPdtCondition loanPdtCondition,Page page) throws Exception;

	/**
	 * 根据机构号查询产品数量
	 * @param iinst
	 * @return
	 */
	BigInteger countLoanPdtByIinst(Long iinst);
	
	/**
	 * 删除借款产品信息
	 * @param loanPdt 借款产品信息
	 * @throws Exception
	 */
	void deleteLoanPdt (LoanPdt loanPdt) throws Exception ;

	/**
	 * 查询接口
	 * @param criteriaExecutor
	 * @return
	 */
	LoanPdt queryBy(CriteriaExecutor<LoanPdt> criteriaExecutor);

	/**
	 * 统计所有产品
	 * @return 
	 */
	BigInteger countLoanPdt();
	/**
	 * 统计所有上架产品
	 * @return 
	 */
	BigInteger countGroundingLoanPdt();
	/**
	 * 查询所有产品
	 * @return
	 */
	List<LoanPdt> queryAll();
	
	LoanPdt queryByLoanPdtId(String loanPdtId);
}
