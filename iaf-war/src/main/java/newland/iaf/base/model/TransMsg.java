package newland.iaf.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import newland.base.formater.BigDecimalFormatter;
import newland.iaf.base.model.dict.FundFlowType;
import newland.iaf.base.model.dict.TransStat;
/**
 * 转账信息定义
 * @author new
 *
 */
@Entity
@Table(name = "t_trans_log", uniqueConstraints = { @UniqueConstraint(columnNames = "order_no") })
@org.hibernate.annotations.Table(appliesTo = "t_trans_log", indexes = {
		@Index(name = "idx_trans_log1", columnNames = { "flow_type", "trans_date" }),
		@Index(name = "idx_trans_log2", columnNames = { "trans_date", "trans_stat" })
		})
public class TransMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 系统跟踪号
	 */
	@Id
	@Column (name = "order_no", length = 16, nullable = false)
	@TransColumn
	private String orderNo;
	/**
	 * 机构商户号
	 */
	@Column (name = "mer_id", length = 15, nullable = false)
	@TransColumn
	private String merId;
	
	@Column (name = "i_merch")
	private Long imerch;
	
	@Column (name = "i_inst")
	private Long iinst;

	@Transient
	private String tradeType = "7";
	
	@TransColumn
	@Column (name = "gate_type")
	private String gateType = "N";
	/**
	 * 机构号
	 */
	@Column (name = "organ_no", length = 10, nullable = false)
	@TransColumn
	private String organNo;
	/**
	 * 清算号
	 */
	@Column (name = "settle_no", length = 15, nullable = false)
	@TransColumn
	private String settleNo;
	/**
	 * 密码
	 */
	@TransColumn(name = "pwd")
	@Transient
	private String password;
	/**
	 * 币种
	 * CNY
	 */
	@TransColumn
	@Column (name = "curr_code", length = 3, nullable = false)
	private String currCode = "CNY";
	
	@Column (name = "trans_count", nullable = false)
	private Integer transCount;
	/**
	 * 资金类型
	 */
	@Column (name = "flow_type", nullable = false)
	private FundFlowType type;
	/**
	 * 订单金额
	 */
	//@TransColumn(formatter = BigDecimalFormatter.class , pattern = "0.00")
	@TransColumn
	@Column (name = "order_amount", nullable = false)
	private BigDecimal orderAmount;
	/**
	 * 手续费
	 * 1-收款方(清算商户)付手续费; 0-付款方(交易商户)付手续
	 */
	@TransColumn
	@Column (name = "charge", length = 1, nullable = false)
	private String charge = "0";

	/**
	 * 支付网关ID
	 * N网络收银机支付（暂时不用指定）B银行网关支付（01农行银行 02兴业银行）
	 */
	@TransColumn
	@Column (name = "gate_id", length = 1, nullable = false)
	private String gateId;
	/**
	 * 版本号
	 * v1.0.0
	 */
	@TransColumn
	@Column (name = "version", length = 10, nullable = false)
	private String version = "v1.0.0";
	/**
	 * 商户私有域
	 */
	@TransColumn
	@Column (name = "priv1", length = 1, nullable = false)
	private String priv1 = "0";
	/**
	 * 无线pos登陆ID
	 */
	@TransColumn
	@Column (name = "user_id", length = 2, nullable = false)
	private String userId = "01";
	/**
	 * 返回模式
	 */
	@TransColumn
	@Column (name = "result_mode", length = 1, nullable = false)
	private String resultMode = "0";
	/**
	 * 
	 */
	
	@TransColumn
	@Column (name = "bg_ret_url")
	private String bgRetUrl;
	/**
	 * 交易结果返回页
	 */
	
	@TransColumn
	@Column (name = "call_back_url")
	private String callBackUrl;
	/**
	 *  支付方式
	 *  0/消费;6/资金归集;3/查询;2/重打印；7/付款
	 */
	@Column (name = "trans_type", length = 2, nullable = false)
	@TransColumn
	private String transType;
	/**
	 * 其他系统跟踪号
	 */
	@Column (name = "other_sys_no", length = 50)
	private String otherSysNo;
	
	@Column(name = "trans_date", nullable = false)
	private Date transDate;
	
	/**
	 * 支付状态
	 */
	@Column (name = "trans_stat", nullable = false)
	private TransStat transStat;
	
	@Column (name = "resp_code", length = 5)
	private String respCode;
	
	@Column (name = "error_msg", length = 200)
	private String errorMsg;
	
	@Transient
	private String payUrl;
	
	/**
	 * 附加域
	 */
	@TransColumn(name = "reserved02")
	@Column (name = "attachment", length = 255)
	private String attachment;
	
	@Transient
	@TransColumn()
	private String reserved01;


	public String getSettleNo() {
		return settleNo;
	}
	public void setSettleNo(String settleNo) {
		this.settleNo = settleNo;
	}
	public String getOtherSysNo() {
		return otherSysNo;
	}
	public void setOtherSysNo(String otherSysNo) {
		this.otherSysNo = otherSysNo;
	}

	public TransStat getTransStat() {
		return transStat;
	}
	public void setTransStat(TransStat transStat) {
		this.transStat = transStat;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getCallBackUrl() {
		return callBackUrl;
	}
	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrganNo() {
		return organNo;
	}
	public void setOrganNo(String organNo) {
		this.organNo = organNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPriv1() {
		return priv1;
	}
	public void setPriv1(String priv1) {
		this.priv1 = priv1;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResultMode() {
		return resultMode;
	}
	public void setResultMode(String resultMode) {
		this.resultMode = resultMode;
	}
	public String getBgRetUrl() {
		return bgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		this.bgRetUrl = bgRetUrl;
	}

	public Long getImerch() {
		return imerch;
	}
	public void setImerch(Long imerch) {
		this.imerch = imerch;
	}
	public Long getIinst() {
		return iinst;
	}
	public void setIinst(Long iinst) {
		this.iinst = iinst;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public FundFlowType getType() {
		return type;
	}
	public void setType(FundFlowType type) {
		this.type = type;
	}
	public Integer getTransCount() {
		return transCount;
	}
	public void setTransCount(Integer transCount) {
		this.transCount = transCount;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getGateType() {
		return gateType;
	}
	public void setGateType(String gateType) {
		this.gateType = gateType;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getReserved01() {
		return reserved01;
	}
	public void setReserved01(String reserved01) {
		this.reserved01 = reserved01;
	}

}
