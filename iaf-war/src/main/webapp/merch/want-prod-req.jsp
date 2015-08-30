<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
</head>
	<body>
	
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;产品申请</p>
			</div>
			<div id="content">
				<h3 class="title"><span>填写产品申请</span></h3>
				<div class="results report" style="padding:10px 0 10px 50px;">
					<h3 class="small-title">产品名称:&nbsp;&nbsp;<font style="color:red"><s:property value="loanPdt.pdtName"/></font></h3>
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;">
						<tr>
							<td class="label">借款最低金额</td>
							<td class="data"><s:property  value='loanPdt.minQuota'/>（万元）</td>
							<td class="label">借款最高金额</td>
							<td class="data"><s:property  value='loanPdt.maxQuota'/>（万元）</td>
						</tr>
						<tr>
							<td class="label">借款最短周期</td>
							<td class="data"><s:property  value='loanPdt.minTerm'/><s:label name="loanPdt.minTermType.desc"/></td>
							<td class="label">借款最长周期</td>
							<td class="data"><s:property  value='loanPdt.maxTerm'/><s:label name="loanPdt.maxTermType.desc"/></td>
						</tr>
						<tr>
				    		<td class="label">还款方式</td>
				    		<td class="data"><s:property value="loanPdt.repayment.desc"/></td>
				    		<td class="label">最快放贷时间</td>
				    		<td class="data"><s:property value="loanPdt.creditTerm"/>（天）</td>
				    	</tr>
				    	<tr>
				    		<td class="label">借款受理地区</td>
				    		<td class="data"><s:property value="loanPdt.area"/></td>
				    		<td class="label">最低利率</td>
				    		<td class="data">
				    		<s:label name="loanPdt.rateType.desc"/>
				    		<s:property  value='loanPdt.rate'/>（%）起</td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品状态</td>
				    		<td class="data">上架</td>
				    		<td class="label">产品上架时间</td>
				    		<td class="data"><fmt:formatDate value="${loanPdt.genTime}" pattern="yyyy-MM-dd"/></td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品所属机构</td>
				    		<td class="data" style="width:270px">
				    		<s:property value="loanPdt.inst.instName"/>
				    			<!--  <a href="${ctx}/merch/merchProdReq!instIntro">
				    				<s:property value="loanPdt.inst.instName"/>
				    			</a>
				    			-->
				    		</td>
				    		<td class="label">申请统计</td>
				    		<td class="data">
				    			<s:if test="loanPdt.reqTotal==NULL">0(次)</s:if>
				    			<s:else><s:property value="loanPdt.reqTotal"/>(次)</s:else>
				    		</td>
				    	</tr>
					</table>
					<h3 class="small-title">借款申请内容</h3>
					<form id="storeformReq" name="storeformReq" action="">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;">
						<input type="hidden" name="loanPdt.iloanPdt" id="iloanPdt" value="<s:property value='loanPdt.iloanPdt'/>">
						<tr>
							<td class="label">申请借款金额</td>
							<td class="data"><s:textfield id="quota" name="loanOrd.wangyuanQuota" maxlength="25"/>&nbsp;(万元)</td>
						</tr>
						<tr>
							<td class="label">申请借贷期限</td>
							<td class="data"><s:textfield id="term" name="loanOrd.term" maxlength="25"/>
							<select id="termType" name="termType" style="width:120px;">
								<option value="">请选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
							</td>
						</tr>
						<tr>
							<td class="label">借款用途</td>
							<td class="data"><s:textfield style="width:550px"  id="purpose" name="loanOrd.purpose" maxlength="100"/></td>
						</tr>
					</table>
					<div class="uop">
						<input type="button" class="dark-btn"  value="确&nbsp;&nbsp;定" onclick="doSubmit();"/> 
						<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/> 
					</div>
					</form>
					<div class="uop">
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var termType = '${termType}';
		if(termType!=""){
			$("#termType option[value='"+termType+"']").attr("selected","selecetd");
		}
			$(function(){
				$(".data-list tr:even").addClass("even");
				$(".data-list tr").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
			});
			function doSubmit(){
// 				if(confirm("确认提交请求?")){
				var iloanPdt = $("#iloanPdt").val();

				if(isNaN($("#quota").val()) || $("#quota").val() == ''){
					alert("借款金额请填写数字!并且不能为空.");
					return false;
				}
				if(isNaN($("#term").val()) || $("#term").val() == ''){
					alert("还款期限请填写数字!并且不能为空.");
					return false;
				}
				if($("#purpose").val() == ''){
					alert("借款用途不能为空.");
					return false;
				}
				
				if($("#termType").val()==''){
					alert("请选择期限单位!");
					return false;
				}
				var quota=$("#quota").val();
				var term=$("#term").val();
				var purpose=$("#purpose").val();
				var termType=$("#termType").val();
				//if(confirm("是否确认申请该产品?\n申请的金额:"+quota+"万元"+"\n申请借贷期限:"+term+"个月"+"\n借款用途:"+purpose)){
					//document.storeformReq.action="${ctx}/merch/merchProdReq!saveReqInfo?index="+iloanPdt;	
					document.storeformReq.action="${ctx}/merch/merchProdReq!reqInfo?index="+iloanPdt;	
			        document.storeformReq.submit();
				//}
// 				}else{
// 					return false;
// 				}
			}
			
			function back(){
				var iloanPdt = $("#iloanPdt").val();
				window.location.href="${ctx}/merch/merchProdReq!back";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>