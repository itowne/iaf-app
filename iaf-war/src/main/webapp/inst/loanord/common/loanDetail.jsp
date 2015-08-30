<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="n" uri="/nl-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 	<div class="tab-content">
	 <h3 style="line-height:32px;text-align:center;font-size:15px;">贷款申请情况</h3>
		<table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table report">
			<tr>
				<td class="label" width="150">贷款编号</td>
				<td class="data" width="250"><s:label name="loanOrd.loanOrdId"></s:label></td>
				<td class="label" width="150">借款申请金额</td>
				<td class="data"><s:property value="%{wanyuanFormat(loanOrd.preQuota)}"/>元</td>
			</tr>
			<tr>
				<td class="label">贷款期限</td>
				<td class="data"><s:label name="loanOrd.term"></s:label><s:label name="loanOrd.termType.desc"/></td>
				<td class="label">最低利率</td>
				<td class="data">
				<s:label name="loanOrd.rateType.desc"/>
				<s:property value="%{getText('format.percent', {loanOrd.rate})}"/>起</td>
			</tr>
			<tr>
				<td class="label">还款方式</td>
				<td class="data">
				<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="debitBid.refundType.desc"/>
				</s:if>
				<s:else>
				<s:label name="lp.repayment.desc"/>
				</s:else>
				</td>
				<td class="label">贷款地区</td>
				<td class="data">
				<s:if test='loanOrd.pdtType.toString()=="MERCH_PROD"'>
				<s:label name="area"/>
				</s:if>
				<s:else>
				<s:label name="lp.area"/>
				</s:else>
				</td>
			</tr>
			<tr>
				<td class="label">申请日期</td>
				<td class="data"><fmt:formatDate value="${loanOrd.ordDate}" pattern="yyyy-MM-dd"/></td>
				<td class="label">申请失效日期</td>
				<td class="data"><fmt:formatDate value="${loanOrd.expiryDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="label">贷款产品</td>
				<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
				<td class="label">产品所属机构</td>
				<td class="data"><s:label name="loanOrd.instName"></s:label></td>
			</tr>
			<tr>
				<td class="label" width="150">贷款用途</td>
				<td class="data" width="250" colspan="3"><s:label name="loanOrd.purpose"></s:label></td>
			</tr>
		</table>
		<h3 style="line-height:32px;text-align:center;font-size:15px;">商户基本情况</h3>
		<table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table report">
			<tr>
				<td class="label" width="150">商户名称</td>
				<td class="data" width="250"><s:label name="loanOrd.merchName"></s:label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="related-menu">
  			<n:instAuthA href="javaScript:addMerch(${loanOrd.imerch})" value="加入我的关注" authCode="WDGZ_GZKH_TJ"/>
  		</span></td>
				<td class="label">开业时间</td>
				<td class="data">
				<s:if test='merch.openTime==""||merch.openTime==null'>
				&nbsp;
				</s:if>
				<s:else>
				<fmt:formatDate value="${merch.openTime}" pattern="yyyy-MM-dd"/>
				</s:else>
				</td>
			</tr>
			<tr>
				<td class="label">所属行业</td>
				<td class="data"><s:label name="merch.industry"/></td>
				<td class="label" width="150">商户类型</td>
				<td class="data" width="250"><s:label name="loanOrd.merchType.desc"></s:label></td>
			</tr>
			<tr>
				<td class="label">员工人数</td>
				<td class="data"><s:label name="merch.regNo"/></td>
				<td class="label">分支机构数量</td>
				<td class="data">&nbsp;</td>
			</tr>

			<tr>
				<td class="label">联系人</td>
				<td class="data"><s:label name="merch.contract"/></td>
				<td class="label">联系电话</td>
				<td class="data"><s:label name="merch.contractTel"/></td>
			</tr>
		</table>
	</div>
<script type="text/javascript">
parent.window.curTab = "loanOrdApply";
function addMerch(id){
	if(confirm("是否确认关注此商户?")){
		window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
	}
}
</script>

