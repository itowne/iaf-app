package newland.iaf.base.service;

import java.util.List;
import java.util.Map;

import newland.iaf.base.model.LoanOrd;
import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.TransMsg;
import newland.iaf.base.model.dict.GateWayType;
import newland.iaf.base.service.impl.HicardPaymentServiceImpl.TransResult;

public interface HicardPaymentService {
	
	/**
	 * 生成放款转账指令
	 * @param loanOrd
	 * @param gateWay
	 * @param baseUrl
	 * @return
	 * @throws Exception 
	 */
	TransMsg genCreditTransMsg(LoanOrd loanOrd, GateWayType gateWay, String baseUrl) throws Exception;

	
	/**
	 * 更新
	 * @param trans
	 * @return
	 */
	TransMsg update(TransMsg trans);
	
	/**
	 * 处理返回信息
	 * @param encryStr
	 * @param signStr TODO
	 * @param trans TODO
	 * @param resp TODO
	 * @return
	 */
	TransResult procRet(String encryStr, String signStr, TransMsg trans, String resp);
	
	/**
	 * 加密签名数据报文件
	 * @param trans
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> encryptAndSign(TransMsg trans) throws Exception;
	
	/**
	 * 调用汇卡支付交易
	 * @param trans
	 */
	void transfer(TransMsg trans) throws Exception;

	/**
	 * 生成批量放款交易指令
	 * @param ords
	 * @param hicard
	 * @param baseUrl
	 * @return
	 * @throws Exception 
	 */
	TransMsg genCreditTransMsg(List<LoanOrd> ords, GateWayType hicard,
			String baseUrl) throws Exception;

	/**
	 * 根据流水号查询
	 * @param otherSysTraceNo
	 * @return
	 */
	TransMsg queryById(String orderNo);

	/**
	 * 生成还款请求文件
	 * @param ord
	 * @param plans
	 * @param hicard
	 * @param baseUrl
	 * @return
	 * @throws Exception
	 */
	TransMsg genRefundTransMsg(LoanOrd ord, List<LoanOrdPlan> plans, GateWayType hicard,
			String baseUrl) throws Exception;


	String getErrMsg(String resp);

	/**
	 * 同步未确认交易
	 * @param trans
	 * @return
	 * @throws Throwable
	 */
	TransMsg synchronize(TransMsg trans) throws Throwable;
}
