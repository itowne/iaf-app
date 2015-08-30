<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
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
    <p>当前位置：我的申请</p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>商户借款订单详情</span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${inst.qqUid}');">在线联系</a></span></h3>
    <div class="how-to-ask">
    	<strong>借贷流程：</strong>&nbsp;<img src="${ctx}/resources/images/mp/how-to-ask.gif" width="586" height="24" alt=""/>
    </div>
    <div class="report-tab-area">
    	<ul class="tab-title clearfix">
				<li onclick="location.href='javascript:process();'">受理进度跟踪</li>
				<li onclick="location.href='javascript:planInfo();'">还款计划</li>
				<s:if test="debitFlag == false">
				<li onclick="location.href='javascript:productDetail();'">产品详情</li>
				</s:if>
				<s:else>
				<li class="disabled">产品详情</li>
				</s:else>
				<li onclick="location.href='javascript:instInfo();'">机构信息</li>
			</ul>
			<div class="tab-content">
				<div class="inst-info">
					<h3 class="title">机构信息</h3>
					<table width="560" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="label">公司名称：</td>
							<td class="data"><s:property  value='inst.instName'/></td>
							<td class="label">外文名称：</td>
							<td class="data"><s:property  value='inst.englishName'/></td>
						</tr>
						<tr>
							<td class="label">成立时间：</td>
							<td class="data"><fmt:formatDate value="${inst.openTime}" pattern="yyyy-MM-dd"/></td>
							<td class="label">经营范围：</td>
							<td class="data"><s:property  value='inst.busiRegion'/></td>
						</tr>
						<tr>
							<td class="label">公司性质：</td>
							<td class="data"><s:property  value='inst.instNature'/></td>
							<td class="label">公司口号：</td>
							<td class="data"><s:property  value='inst.catchword'/></td>
						</tr>
						<tr>
							<td class="label">员工数：</td>
							<td class="data"><s:property  value='inst.peopleCount'/></td>
							<td class="label">注册资金(万元)：</td>
							<td class="data"><s:property  value='inst.regCapital'/></td>
						</tr>
						<tr>
							<td class="label">官网：</td>
							<td class="data"><s:property  value='inst.officialWebsite'/></td>
							<td class="label">电话：</td>
							<td class="data"><s:property  value='inst.shortPhone'/></td>
						</tr>
					</table>
					<h3 class="title">机构简介</h3>
					<p>
						<s:property  value='inst.introduce'/>
					</p>
				</div>
				<div></div>
			</div>
			<div class="uop">
			<s:if test="loanOrd.ordStat.toString()=='APPLY'">
				<a href="javascript:cancel();" class="dark-btn">申请撤销</a>
			</s:if>
			&nbsp;&nbsp;&nbsp;<a href="${ctx}/merch/merchMyReq.action" class="dark-btn">返&nbsp;回</a></div>
		</div>
  </div>
</div>
<script type="text/javascript">
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
			
			function productDetail(){
				window.location.href="${ctx}/merch/merchMyReq!prodDetail";
			}
			function process(){
				window.location.href="${ctx}/merch/merchMyReq!processInfo";
			}
			
			function cancel(){
				if(confirm("确认提交请求?")){
					window.location.href="${ctx}/merch/merchMyReq!cancelMyReq";
				}else{
				}
			}
			
			function planInfo(){
				window.location.href="${ctx}/merch/merchMyReq!planInfo";
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>
