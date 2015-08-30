package newland.iaf.base.model.dict;

public enum OperType {
	/**
	 * 登录
	 */
	LOGIN("登录"),
	/**
	 * 退出
	 */
	LOGOUT("退出"),
	/**
	 * 添加角色
	 */
	ADD_ROLE("添加角色"),
	/**
	 * 添加用户
	 */
	ADD_USER("添加用户"),
	/**
	 * 添加商户现场调查
	 */
	ADD_MERCHFIELDSURVY("添加商户现场调查"),
	/**
	 * 修改商户现场调查
	 */
	MODIFY_MERCHFIELDSURVY("修改商户现场调查"),
	/**
	 * 修改角色
	 */
	MODIFY_ROLE("修改角色"),
	/**
	 * 修改用户
	 */
	MODIFY_USER("修改用户"),
	/**
	 * 删除角色
	 */
	DEL_ROLE("删除角色"),
	/**
	 * 册除用户
	 */
	DEL_USER("册除用户"),
	/**
	 * 删除机构
	 */
	DEL_INST("删除机构"),
	/**
	 * 删除商户
	 */
	DEL_MERCH("删除商户"),
	/**
	 * 修改密码
	 */
	MODIFY_PWD("修改密码"),
	/**
	 * 删除产品
	 */
	DEL_PDT("删除产品"),
	/**
	 * 添加产品
	 */
	ADD_PDT("添加产品"),
	/**
	 * 添加机构
	 */
	ADD_INST("添加机构"),
	/**
	 * 添加商户
	 */
	ADD_MERCH("添加商户"),
	/**
	 * 添加商户业务资料
	 */
	ADD_MERCH_BUSIINFO("添加商户业务资料"),
	/**
	 * 添加机构法人
	 */
	ADD_MERCH_LEGALPERS("添加商户法人"),
	/**
	 * 添加机构业务资料
	 */
	ADD_INST_BUSIINFO("添加机构业务资料"),
	/**
	 * 添加机构法人
	 */
	ADD_INST_LEGALPERS("添加机构法人"),
	/**
	 * 添加竞投产品
	 */
	ADD_DEBITBID("添加竞投产品"),
	/**
	 * 撤销竞投产品
	 */
	CAMCEL_DEBITBID("撤销竞投产品"),
	/**
	 * 修改产品
	 */
	MODIFY_PDT("修改产品"),
	/**
	 * 修改商户
	 */
	MODIFY_MERCH("修改商户"),
	/**
	 * 修改机构
	 */
	MODIFY_INST("修改机构"),
	/**
	 * 修改机构业务资料
	 */
	MODIFY_INST_BUSIINFO("修改机构业务资料"),
	/**
	 * 修改机构法人
	 */
	MODIFY_INST_LEGALPERS("修改机构法人"),
	/**
	 * 修改商户业务资料
	 */
	MODIFY_MERCH_BUSIINFO("修改商户业务资料"),
	/**
	 * 修改机构法人
	 */
	MODIFY_MERCH_LEGALPERS("修改商户法人"),
	/**
	 * 禁用机构
	 */
	UNUSED_INST("禁用机构"),
	/**
	 * 产品上架
	 */
	UP_PDT("产品上架"),
	/**
	 * 产品下架
	 */
	DOWN_PDT("产品下架"),
	/**
	 * 申请贷款
	 */
	APPLY("申请贷款"),
	/**
	 * 受理
	 */
	ACCEPT("受理"),
	/**
	 * 审核
	 */
	AUDIT("审核"),
	/**
	 * 平台外放款
	 */
	CREDIT("平台外放款"),
	/**
	 * 放款申请
	 */
	CREDIT_APPLY("放款申请"),
	/**
	 * 拒绝
	 */
	REFUSE_APPLY("拒绝申请"),
	/**
	 * 拒绝
	 */
	REFUSE_ACCEPT("拒绝受理"),
	/**
	 * 平台外还款
	 */
	REFUND("平台外还款"),
	/**
	 * 还款申请
	 */
	REFUND_APPLY("还款申请"),
	/**
	 * 撤销
	 */
	CANCEL("撤销订单"),
	/**
	 * 放款审核
	 */
	AUDIT_CREDIT("放款审核"),
	/**
	 * 放款退回
	 */
	CANCEL_CREDIT("退回放款申请"),
	/**
	 * 还款审核 
	 */
	AUDIT_REFUND("还款审核 "),
	/**
	 * 退回还款申请
	 */
	CANCEL_REFUND("退回还款申请"),
	/**
	 * 冻结申请
	 */
	FREEZE_APPLY("冻结申请"),
	/**
	 * 冻结成功
	 */
	AUDIT_FREEZE("冻结成功"),
	/**
	 * 拒绝冻结
	 */
	REFUSE_FREEZE("拒绝冻结"),
	/**
	 * 撤销冻结请求
	 */
	CANCEL_FREEZE("撤销冻结请求"),
	/**
	 * 解冻
	 */
	THAW_APPLY("解冻申请"),
	/**
	 * 解冻成功
	 */
	AUDIT_THAW("解冻成功"),
	/**
	 * 撤销解冻请求
	 */
	CANCEL_THAW("撤销解冻请求"),
	/**
	 * 拒绝解冻请求
	 */
	REFUSE_THAW("拒绝解冻请求"),
	/**
	 * 新增公告
	 */
	NOTICE_ADD("新增公告"),
	/**
	 * 修改公告
	 */
	NOTICE_UPDATE("修改公告"),
	/**
	 * 删除公告
	 */
	NOTICE_DEL("删除公告"),
	
	/**
	 * 上传还款计划
	 */
	UPLOAD_PLAN("上传还款计划"),
	/**
	 * 修改还款计划
	 */
	MODIFY_PLAN("修改还款计划"),
	/**
	 * 修改订单信息
	 */
	MODIFY_LOANORD("修改订单信息");
	String desc;
	OperType(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
