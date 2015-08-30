<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/template/head.jsp" />
<html>
<head>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<style type="text/css">
	.pager{
		padding:15px 45px;text-align:right;
	}
	.pager a{
		display:inline-block;padding:2px 5px;border:1px solid #d1d1d1;color:#4a4a4a;
	}
	.pager a.cur,.pager a:hover{
		background:#0157ad;color:#fff;
	}
	.dark-btn{
		vertical-align:middle;
	}
</style>
</head>
<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;经营数据报告管理</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>我的经营数据报告</span></h3>
				<div class="search-bar">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">经营数据报告生成时间范围</td>
							<td>
							<s:form action="backMerchCredit" namespace="/backstage/merchcreditreport">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<s:radio onclick="javascript:dateRangeChange()" name="dateRange"  list="#{'PRE_MONTH':'前一个月', 'PRE_THREE_MONTH':'前三个月', 'PRE_HALF_YEAR':'前六个月'}"></s:radio>
										</td>
										<td align="right">
											<s:submit cssClass="dark-btn"  method="queryNewest" value="查询最新报告"></s:submit>
											<s:if test="first==false">
												<s:submit cssClass="dark-btn" method="saveReport" value="保存报告"></s:submit>
											</s:if>
											<s:submit cssClass="dark-btn" method="queryHis" value="查询历史报告"></s:submit>
										</td>
									</tr>
								</table>
							</s:form>
							</td>
						</tr>
						<tr>
							<td class="label">报告统计日期范围</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<span id="dateRange"></span>
										</td>
										<td align="right">
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<s:if test="first==false">
				<div class="report-tab-area">
					<ul class="tab-title clearfix">
						<li class="cur" id="BASIC_INFO"  onclick="window.curTab='BASIC_INFO';disableAllBtn();show('modify')"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=BASIC_INFO&instType=HICARD">基本资料</a></li>
						<li id="FIELD_SURVY" onclick="window.curTab='FIELD_SURVY';disableAllBtn();"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=FIELD_SURVY&instType=HICARD">现场调查情况</a></li>
						<li id="TRANSFER" onclick="window.curTab='TRANSFER';disableAllBtn();"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=TRANSFER&instType=HICARD">交易记录</a></li>
						<li id="ROUTING_ISPECTION" onclick="window.curTab='ROUTING_ISPECTION';disableAllBtn();"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=ROUTING_ISPECTION&instType=HICARD">服务巡检记录</a></li>
						<li id="INSTALL" onclick="window.curTab='INSTALL';disableAllBtn();"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=INSTALL&instType=HICARD">装机撤机记录</a></li>
						<li id="OTHER_INFO" onclick="window.curTab='OTHER_INFO';disableAllBtn();show('upload')"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=OTHER_INFO&instType=HICARD">其他资料</a></li>
						<li id="MERCH_BUSI_DATA_VERIFICATION" onclick="window.curTab='MERCH_BUSI_DATA_VERIFICATION';disableAllBtn();"><a target="merchFrame" href="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=MERCH_BUSI_DATA_VERIFICATION&instType=HICARD">经营数据核查情况</a></li>
					</ul>
					<div class="tab-content">
					<div id="modify" style="margin: 0px;text-align: right;width: 90%">
<%-- 						<s:submit cssClass="dark-btn" onclick="showModify();" value="修改资料"></s:submit> --%>
					</div>
					<div id="upload" style="display:none;margin: 20px;text-align: right;width: 90%">
						<s:submit cssClass="dark-btn" onclick="showUpload();" value="上传其他资料"></s:submit>
						<s:submit type="button" cssClass="dark-btn" onclick="picDetail();" value="详情"></s:submit>
					</div>
					<iframe id="merchFrame" name="merchFrame" marginheight="0" marginwidth="0" src="/backstage/merchcreditreport/backMerchCredit!viewPagePositon?position=BASIC_INFO&instType=HICARD" width="100%" align="middle" scrolling="no" frameborder="0"></iframe>
					</div>
					<script type="text/javascript">
						function showInfoPermSet(){
							parent.jDialog("经营数据报告隐私设置","/merch/infoPermSet",500,430,false);
						}
						
						function showAppeal(){
							parent.jDialog("经营数据报告隐私设置","/merch/merchInfoPermSet",500,430,false);
						}
						function showModify(){
							parent.jDialog("修改商户信息","/merch/modifyMerchInfo",800,500,false);
						}
						
						function showUpload(){
							parent.jDialog("上传其他资料","${ctx}/backstage/merchcreditreport/backMerchCredit!toUpload",480,360,false,function(){
								merchFrame.location = "/backstage/merchcreditreport/backMerchCredit!viewPagePositon.action?method:viewPagePosition&position=OTHER_INFO";
							});
						}
						
						function picDetail(){
							parent.jDialog("资料详情","${ctx}/backstage/merchcreditreport/backMerchCredit!picDetail",750,550,true,function(){
								merchFrame.location = "/backstage/merchcreditreport/backMerchCredit!viewPagePositon.action?method:viewPagePosition&position=OTHER_INFO";
							});
						}
						
						function show(id){
							$("#" + id).attr("style", "margin: 20px;text-align: right;width: 90%");
						}
						function disableAllBtn(){
							$("#modify").attr("style", "display:none;");
							$("#upload").attr("style", "display:none;");
						}
					</script>
				</div>
				</s:if>
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
				window.setTimeout(reinitLoanIframe,300);
				window.curTab = "BASIC_INFO";
				$("#merchFrame").bind("load",function(){
					$(".tab-title li").removeClass("cur");
					$("#"+window.curTab).addClass("cur");
				});
				dateRangeChange();
			});
			function reinitLoanIframe(){
				var iframe = document.getElementById("merchFrame");
				try{
					var height = iframe.contentWindow.document.body.scrollHeight;
					if(/webkit/.test(navigator.userAgent.toLowerCase())){
						height = $(iframe.contentWindow.document.body).height() + 40;
					}
					iframe.height = height;
				}catch(e){}
				iframe=null;
				window.setTimeout(reinitLoanIframe,300);
			}
			function dateRangeChange(){
				var New=document.getElementsByName("dateRange");
				var strNew;
				for(var i=0;i<New.length;i++)
				{
					if(New.item(i).checked){
						strNew = New.item(i).getAttribute("value"); 
						var beginDate = new Date();
						var endDate = new Date();
						if(strNew=='PRE_MONTH'){
							//前一个月
							beginDate.setMonth(beginDate.getMonth()-1, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}else if(strNew=='PRE_THREE_MONTH'){
							//前三个月
							beginDate.setMonth(beginDate.getMonth()-3, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}else if(strNew=='PRE_HALF_YEAR'){
							//前六个月
							beginDate.setMonth(beginDate.getMonth()-6, 1);
							endDate.setMonth(endDate.getMonth(),0);
						}
						strNew = beginDate.toLocaleDateString()+'---'+endDate.toLocaleDateString();
						$("#dateRange").empty();                  
			        	$("#dateRange").html(strNew); 
					}
				}
			}
		</script> 
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>