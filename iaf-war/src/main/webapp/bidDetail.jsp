<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 我要放贷</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<n:head styles="mp-workframe,portal-base,portal-hp"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<!--[if IE]><script src="resources/js/html5.js" type="text/javascript"></script><![endif]-->
		<style type="text/css">
			.small-title{
				padding:5px 55px;font-size:15px;
			}
			#content .results p{
				line-height:28px;padding-left:54px;
			}
			#xxoo td{
				text-align:left;padding:5px 60px;letter-spacing:3px;
			}
		</style>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<form id="form1" method="post">
			<input type="hidden" id="target" name="target">
		</form>
		<div id="main" class="iaf-grid">
			<div id="content">
				<h3 class="title">
					<span style="font-size:15px;">借 款 申 请 详 情</span>&nbsp;&nbsp;
					<span class="related-menu"><a href="JavaScript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;
				</h3>
    		<div class="results report" style="padding:10px 0 10px 50px;">
				<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table report">
			    	<tr>
			    		<td class="label" width="150">借款金额</td>
			    		<td class="data" width="250"><s:property  value='%{wanyuanFormat(debitBid.quota)}'/>元</td>
			    		<td class="label">借款期限</td>
			    		<td class="data"><s:property  value='debitBid.term'/><s:label name="debitBid.termType.desc"/></td>
			    		
			    	</tr>
			    	<tr>
			    		<td class="label">利率</td>
			    		<td class="data">
			    		<s:label name="debitBid.rateType.desc"/>
			    		<s:property  value='debitBid.yearRate'/>%</td>
			    		<!--  
			    		<td class="label">月利率</td>
			    		<td class="data"><span id="monthRate"></span>%</td>
			    		-->
			    		<td class="label">申请受理情况</td>
			    		<td class="data">
			    		<s:if test="debitBid.acceptTotal==null">0次</s:if>
							<s:else><s:property value="debitBid.acceptTotal"/>次</s:else>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td class="label">还款方式</td>
			    		<td class="data"><s:property  value='debitBid.refundType.desc'/></td>
			    		<td class="label">借款地区</td>
			    		<td class="data"><s:property  value='area'/></td>
			    	</tr>
			    	<tr>
			    		<td class="label">申请日期</td>
			    		<td class="data"><fmt:formatDate value="${debitBid.genTime}" pattern="yyyy-MM-dd"/></td>
			    		<td class="label">申请失效时间</td>
			    		<td class="data"><fmt:formatDate value="${debitBid.expireDate}" pattern="yyyy-MM-dd"/></td>
			    	</tr>
			    	<tr>
			    		<td class="label">借款用途简述</td>
			    		<td class="data">&nbsp;</td>
			    		<td class="label">&nbsp;</td>
			    		<td class="data">&nbsp;</td>
			    	</tr>
			    	<tr>
			    		<td class="label">借款用途</td>
			    		<td class="data" colspan="3" style="width:250px;word-wrap:break-word;word-break:break-all"><s:property  value='debitBid.purpose'/></td>
			    	</tr>
			    </table>
			 </div>
				    <h3 class="title">
						<span style="font-size:15px;">商 户 基 本 情 况</span>&nbsp;&nbsp;
					</h3>
					<div class="results report" style="padding:10px 0 10px 50px;">
    <table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table report">
    	<tr>
    		<td class="label" width="150">商户名称</td>
    		<td class="data" width="250"><s:property  value='merch.merchName'/></td>
    		<td class="label">开业时间</td>
    		<td class="data"><fmt:formatDate value="${merch.openTime}" pattern="yyyy-MM-dd"/></td>
    	</tr>
    	<tr>
    		<td class="label">所属行业</td>
    		<td class="data"><s:property  value='merch.industry'/></td>
    		<td class="label" width="150">商户类型</td>
    		<td class="data" width="250"><s:property  value='merch.MerchType.desc'/></td>
    	</tr>
			<tr>
				<td class="label">员工人数</td>
				<td class="data"><s:label name="merch.companySize"/>人</td>
				<td class="label">分支机构数量</td>
				<td class="data">&nbsp;</td>
			</tr>
    </table>
    <div class="uop">
			<a href="${ctx}/instUserLogin!init?target=/inst/loanord/prodAccept!instApplyDebit?idebitBid=<s:property value='%{encode(debitBid.idebitBid)}'/>" class="dark-btn" style="width:70px;height:40px;line-height:37px">我要放贷</a>&nbsp;&nbsp;
		</div>
		<br/><br/>
		</div>
	 </div>
	 </div>
	<%@include file="/template/portal/footer.jspf" %>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/portal.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#nav li").hover(function(){
					if($(this).hasClass("cur")){
						return;
					}
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				doPlaceholder();
				$(".data-row").hover(function(){
					$(this).addClass("data-row-hover");
				},function(){
					$(this).removeClass("data-row-hover");
				});
				$(".search-btn").hover(function(){
					$(this).addClass("search-btn-hover");
				},function(){
					$(this).removeClass("search-btn-hover");
				});
				
				
			});
			
			//function monthRate(){
			//var rate = '<s:property  value='debitBid.yearRate'/>';
			//if(!isNaN(rate)){
				//if(rate!=0&&rate!=""&&rate){
					//var monthRate = rate/12;
					//$("#monthRate").html(monthRate.toFixed(2));
				//}
			//}
			//}
			//setTimeout(monthRate,300); 
		</script>
		<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>