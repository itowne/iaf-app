<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />

		    <h3 style="line-height:32px;text-align:center;font-size:15px;">产 品 借 款 申 请 情 况</h3>
		    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table report">
		    	<tr>
		    		<td class="label">借款申请金额</td>
		    		<td class="data"><s:label name="loanOrd.preQuota"></s:label>元</td>
		    		<td class="label" width="150">借款编号</td>
		    		<td class="data" width="250"><s:label name="loanOrd.loanOrdId"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借款审批金额</td>
		    		<td class="data"><s:label name="loanOrd.quota"></s:label>元</td>
		    		<td class="label">最高可接受利率</td>
		    		<td class="data"><s:label name="loanOrd.rate"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借贷产品</td>
		    		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
		    		<td class="label">产品所属机构</td>
		    		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
		    	</tr>
		    	<tr>
		    		<td class="label">借款期限</td>
		    		<td class="data"><s:label name="loanOrd.term"></s:label>个月</td>
		    		<td class="label">申请日期</td>
		    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm"/></td>
		    	</tr>
		    	<tr>
		    		<td class="label" width="150">借款用途</td>
		    		<td class="data" width="250" colspan="3"><s:label name="loanOrd.purpose"></s:label></td>
		    	</tr>
		    </table>
		    <h3 style="line-height:32px;text-align:center;font-size:15px;">商 户 基 本 情 况</h3>
		    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table report">
		    	<td class="label" width="150">商户名称</td>
    		<td class="data" width="250"><s:property  value='merch.merchName'/></td>
    		<td class="label" width="150">商户类型</td>
    		<td class="data" width="250"><s:property  value='merch.MerchType.desc'/></td>
    	</tr>
    	<tr>
    		<td class="label">商户所属省市</td>
    		<td class="data"><s:property  value='merch.province'/></td>
    		<td class="label">商户所属行业</td>
    		<td class="data"><s:property  value='merch.industry'/></td>
    	</tr>
    	<tr>
    		<td class="label">开业时间</td>
    		<td class="data"><fmt:formatDate value="${merch.openTime}" pattern="yyyy-MM-dd"/></td>
    		<td class="label">经营状况</td>
    		<td class="data"><s:property  value='merch.companySize'/></td>
    	</tr>
    	<tr>
				<td class="label">注册编号</td>
				<td class="data"><s:label name="merch.regNo"/></td>
				<td class="label">注册地址</td>
				<td class="data"><s:label name="merch.regAddr"/></td>
			</tr>
			<tr>
				<td class="label">注册时间</td>
				<td class="data"><s:label name="merch.regTime"/></td>
				<td class="label">机具地址</td>
				<td class="data"><s:label name="merch.posAddr"/></td>
			</tr>
			<tr>
				<td class="label">营业执照号</td>
				<td class="data"><s:label name="merch.businlic"/></td>
				<td class="label">税务登记号</td>
				<td class="data"><s:label name="merch.taxReg"/></td>
			</tr>
			<tr>
				<td class="label">注册资本</td>
				<td class="data"><s:label name="merch.regCap"/></td>
				<td class="label">邮件地址</td>
				<td class="data"><s:label name="merch.email"/></td>
			</tr>
			<tr>
				<td class="label">联系人</td>
				<td class="data"><s:label name="merch.contract"/></td>
				<td class="label">联系电话</td>
				<td class="data"><s:label name="merch.contractTel"/></td>
			</tr>
			<tr>
				<td class="label">信用情况</td>
				<td class="data"><s:label name="merch.credit.getDesc()"/></td>
				<td class="label">公司规模</td>
				<td class="data"><s:label name="merch.companySize"/></td>
			</tr>
		    </table>



