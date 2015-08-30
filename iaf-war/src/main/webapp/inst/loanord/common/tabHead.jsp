<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<ul class="tab-title clearfix">
	<s:if test="#request.tabName == loanOrdApply" >
		<li class="cur" for="loanOrdApply"><a href="/inst/loanord/loanDetail" target="loanFrame" id="loanOrdApply">借款申请</a></li>
	</s:if>
	<s:else>
		<li>借款申请</li>
	</s:else>
	<s:if test="#request.tabName == loanOrdAccept" >
		<li class="cur" for="loanOrdAccept">
			<a id="loanOrdAccept" href="/inst/loanord/operLogDetail" target="loanFrame" >申请受理</a>
		</li>
	</s:if>
	<s:else>
		<li>
		申请受理
		</li>
	</s:else>
	<s:if test="#request.tabName == loanOrdPlan">
		<li  class="cur" for="loanOrdPlan">
			<a id="loanOrdPlan" href="/inst/loanord/planMgr" target="loanFrame">还款计划</a>
		</li>
	</s:if>
	<s:else>
		<li>还款计划</li>
	</s:else>
	<s:if test="#request.tabName == loanOrdList">
		<li class="cur" for="loanOrdPlan">
			<a id="loanOrdList" href="/inst/loanord/loanOrdList" target="loanFrame">借贷信用</a>
		</li>
	</s:if>
	<s:else>
		<li class="cur" for="loanOrdPlan">
			<a id="loanOrdList" href="/inst/loanord/loanOrdList" target="loanFrame">借贷信用</a>
		</li>
	</s:else>
	<s:if test="#request.tabName == creditReport">
		<li class="cur" for="creditReport"><a id="creditReport" href="JavaScript:void(0);">经营数据报告</a></li>
	</s:if>
	<s:else>
		<li>经营数据报告</li>
	</s:else>
</ul>