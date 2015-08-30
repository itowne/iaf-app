<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>

<html>
<head>
<n:ui includes="form" />
	<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
</head>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：产品申请</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>产品申请</span>
			</h3>
			<div class="results report">
			<form id="storeformReq" name="storeformReq" >
				<table width="65%" border="0" cellspacing="0" cellpadding="0"
					class="data-table">
					<tr>
						<td class="data" colspan="2" align="center">您的借款产品申请详情</td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">申请借款产品名称</td>
						<td class="data"><s:property value="loanPdt.pdtName" /></td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">利率</td>
						<td class="data">
						<s:label name="loanPdt.rateType.desc"/>
						<s:property value="loanPdt.rate" />%</td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">所属机构</td>
						<td class="data"><s:property value="loanPdt.inst.instName" /></td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">申请借款金额(万元)</td>
						<td class="data"><s:property value="loanOrd.wangyuanQuota"/></td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">申请借款周期</td>
						<td class="data"><s:property value="loanOrd.term" />
						<s:if test='termType=="DAY"'>
						天
						</s:if>
						<s:elseif test='termType=="MONTH"'>
						个月
						</s:elseif>
						<s:elseif test='termType=="YEAR"'>
						年
						</s:elseif>
						</td>
					</tr>
					<tr>
						<td class="label" style="width: 150px;">借款用途</td>
						<td class="data"><s:property value="loanOrd.purpose" /></td>
					</tr>
				</table>
				<br/>
				<div style="margin-left:190px;width:65%;font-size:15px">
					<p>
						 如果您确定申请该借款产品，您的资料和经营报告信息将会对产品所属机构公开，以作为机构审核您借款申请参考的依据。  </p><br>
     					<p> 您确认要申请本借款产品吗？</p>
				</div>
				<div class="uop">
					<input type="button" class="dark-btn" value="确定" onclick="toAdd();" />
					<input type="button" class="dark-btn" value="取消" onclick="back();" />
				</div>
				<input type="hidden" name="iloanPdt" id="iloanPdt" value="<s:property value="loanPdt.iloanPdt"/>">
				<input type="hidden" name="termType" id="termType" value="<s:property value="termType"/>"/>
				</form>
				<div class="results report"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function back() {
			var termType=$("#termType").val();
			window.location.href = "${ctx}/merch/merchProdReq!detailBack?termType="+termType;
			//history.go(-1);
		}
		function toAdd(){
			if(confirm("是否确认申请该产品?")){
				var iloanPdt = $("#iloanPdt").val();
				document.storeformReq.action="${ctx}/merch/merchProdReq!saveReqInfo?index="+iloanPdt;
				document.storeformReq.submit();
			}
		}
	</script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>