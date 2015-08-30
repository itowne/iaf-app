<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 我要借款</title>
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
					<span style="font-size:15px;">放 贷 产 品 详 情</span>&nbsp;&nbsp;
					<span class="related-menu"><a href="JavaScript:runQQ('${inst.qqUid}')">在线联系</a></span>&nbsp;&nbsp;
				</h3>
    	<div class="results report" style="padding:10px 0;">
					<h3 class="small-title">产 品 名 称：<font style="color:red"><s:property value="loanPdt.pdtName"/></font>
					&nbsp;&nbsp;&nbsp;<span class="related-menu"><a href="JavaScript:addPdtColl('${loanPdt.iloanPdt}','${loanPdt.iinst}')">加入关注产品</a></span>
					</h3>
					<h3 class="small-title">产 品 简 述 </h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 0;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<label name="loanPdt.introduce"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdt.introduce'/></label>
		    		</td>
		    	</tr>
		    </table>
		    
			<h3 class="small-title">产 品 特 点</h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 0;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!-- <label name="loanPdt.feature"  rows="15" cols="86" readonly="readonly" ><s:property value='loanPdt.feature'/></label> -->
		    		<div>${loanPdt.feature}</div>
		    		</td>
		    	</tr>
		    </table>		
		    <h3 class="small-title">产 品 详 情</h3>
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 50px;">
						<input type="hidden" name="loanPdt.iloanPdt" id="iloanPdt" value="<s:property value='loanPdt.iloanPdt'/>">
						<tr>
						<td class="label">借款最低金额</td>
						<td class="data"><s:property value="loanPdt.minQuota"/>(万元)</td>
						<td class="label">借款最高金额</td>
						<td class="data"><s:property value="loanPdt.maxQuota"/>(万元)</td>
						</tr>
						<tr>
			    		<td class="label">借款最短周期</td>
			    		<td class="data"><s:property value="loanPdt.minTerm"/><s:label name="loanPdt.minTermType.desc"/></td>
			    		<td class="label">借款最长周期</td>
			    		<td class="data"><s:property value="loanPdt.maxTerm"/><s:label name="loanPdt.maxTermType.desc"/></td>
				    	</tr>
				    	<tr>
				    		<td class="label">还款方式</td>
				    		<td class="data"><s:property value="loanPdt.repayment.desc"/></td>
				    		<td class="label">最快放款时间</td>
				    		<td class="data"><s:property value="loanPdt.creditTerm"/>天</td>
				    	</tr>
				    	<tr>
				    		<td class="label">借款受理地区</td>
				    		<td class="data"><s:property value="loanPdt.area"/></td>
				    		<td class="label">最低利率</td>
				    		<td class="data"><s:label name="loanPdt.rateType.desc"/><s:property value="loanPdt.rate"/>%起</td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品状态</td>
				    		<td class="data"><s:property value="loanPdt.pdtStatus.desc"/></td>
				    		<td class="label">产品上架时间</td>
				    		<td class="data"><s:date name="loanPdt.genTime" format="yyyy-MM-dd" />
				    		</td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品所属机构</td>
				    		<td class="data" style="width:200px">
				    			<a href="${ctx}/homePage!instIntro">
				    				<s:property value="loanPdt.inst.instName"/>
				    			</a>
				    			&nbsp;&nbsp;&nbsp;<span class="related-menu"><a href="JavaScript:addInstColl('${loanPdt.iinst}')">加入机构关注</a></span>
				    		</td>
				    		<td class="label">申请统计</td>
				    		<td class="data">
				    			<s:if test="loanPdt.reqTotal==NULL">0(次)</s:if>
				    			<s:else><s:property value="loanPdt.reqTotal"/>(次)</s:else>
				    		</td>
				    	</tr>
					</table>
			<h3 class="small-title">申 请 条 件</h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 0;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!--  <label name="loanPdt.condition"  rows="15" cols="86" readonly="readonly"><s:property value='loanPdt.condition'/></label>-->
		    		<div>${loanPdt.condition}</div>
		    		</td>
		    	</tr>
		    </table>

		    <h3 class="small-title">所 需 材 料</h3>
		    <table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 0;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!-- <label name="loanPdt.cl"  rows="15" cols="86" readonly="readonly"><s:property value='loanPdt.cl'/></label> -->
		    		<div>${loanPdt.cl}</div>
		    		</td>
		    	</tr>
		    </table>
	    
		<div class="uop">
			<a href="${ctx}/merchLogin!init?target=/merch/merchProdReq!query?loanPdtId=<s:property value='%{encode(loanPdt.iloanPdt)}'/>" class="dark-btn">我要申请</a>&nbsp;&nbsp;
			<a href="${ctx}/homePage!askLoan?curPage=0" class="dark-btn">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</a>
		</div>
		<br/><br/>
	    </div>
	 </div>
	 </div>
	<%@include file="/template/portal/co-bank.jspf" %>
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
			function addPdtColl(ipdt, iinst){
				if(confirm("是否要关注此产品?")){
					$("#target").val("/merch/merchCollPdt!add?iloanPdt=" + ipdt + "&iinst=" + iinst);
					$("#form1").attr("action", "${ctx}/merchLogin!init");
					$("#form1").submit();
				}
			}
			
			function addInstColl(iinst){
				if(confirm("是否确认要关注此机构")){
					$("#target").val("/merch/merchCollInst!add?iinst=" + iinst);
					$("#form1").attr("action", "${ctx}/merchLogin!init");
					$("#form1").submit();
				}
			}

		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>