<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机色管理后台</title>
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
    <p>当前位置：贷后管理&nbsp;&gt;&gt;&nbsp;还款计划</p>
  </div>
  <div class="how-to-ask">
   	<strong>借贷处理流程：</strong>&nbsp;<img src="/resources/images/mp/bg-step4.gif" width="737" height="24" alt=""/>
  </div>
  <div class="report-tab-area">
  	<ul class="tab-title clearfix">
  		<li>借款申请</li>
  		<li>申请受理</li>
  		<li class="cur">还款计划</li>
  		<li>借贷信用</li>
  		<li>经营数据报告</li>
  	</ul>
  	<div id="content" class="report">
	    <h3 class="title"><span>借款状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;<span class="related-menu"><a href="javaScript:addMerch(${loanOrd.imerch})">加入我的关注</a></span></h3>
	    <div class="tab-content">
	    <h3 style="line-height:32px;text-align:center;font-size:15px;">产 品 借 款 申 请 情 况</h3>
	    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<td class="label" width="150">借款用途</td>
	    		<td class="data" width="250"><s:label name="loanOrd.purpose"></s:label></td>
	    		<td class="label" width="150">借款编号</td>
	    		<td class="data" width="250"><s:label name="loanOrd.loanOrdId"></s:label></td>
	    	</tr>
	    	<tr>
	    		<td class="label">借款金额</td>
	    		<td class="data"><s:label name="loanOrd.quota"></s:label>元</td>
	    		<td class="label">最高可接受利率</td>
	    		<td class="data"><s:label name="loanOrd.rate"></s:label>%</td>
	    	</tr>
	    	<tr>
	    		<td class="label">借贷产品</td>
	    		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
	    		<td class="label">产品所属机构</td>
	    		<td class="data"><s:label name="loanOrd.instName"></s:label></td>
	    	</tr>
	    	<tr>
	    		<td class="label">借款期限</td>
	    		<td class="data"><s:label name="loanOrd.term"></s:label><s:label name="loanOrd.termType.desc"/></td>
	    		<td class="label">申请日期</td>
	    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm"/></td>
	    	</tr>
	    </table>
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">还款计划</h3>
	    <div  style="margin:10px auto;width:100%">
								<table width="800" id="list"></table>
								<div id="pager"></div>
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
					getList();
				});
				
				$(function(){
					$(window).resize(function(){
						$("#list").setGridWidth($(window).width()*0.95);
					});
				});
				
	    		function getList(){
					jQuery("#list").jqGrid({
						url:'${ctx}/inst/loanord/planUpload!planList',
						colNames:['还款期数','还款日期','还款金额(元)','还款本金(元)','还款利息(元)','剩余还款金额(元)','保存状态'],
						colModel:[
							{name:'period',index:'period',align:"center", width:30},
							{name:'refundDate',index:'refundDate',align:"center", width:40},
							{name:'refundAmount',index:'refundAmount',align:"center", width:50},
							{name:'capital',index:'capital',align:"center", width:50},
							{name:'interest',index:'interest',align:"center", width:50},
							{name:'remainAmount',index:'remainAmount',align:"center", width:50},
							{name:'saveFlag',index:'saveFlag',formatter:statFormatter, align:"center", width:50},
						],
						height:240,
						rowNum:10,
						pager: '#pager',
						repeatitems: false, 
						shrinkToFit:true,
						autowidth:true,
						caption:"计划列表"
					});
					jQuery("#list").jqGrid('navGrid','#pager');
				}
	    	</script>
	    	<div style="margin:15px auto;text-align:center;">
	    			<s:form action="planUpload" method="POST" enctype="multipart/form-data">
	    			<s:file name="plan" label="还款计划文件"></s:file>
	    			<s:submit method="upload" cssClass="dark-btn" value="上传"></s:submit>
	    			</s:form>
	    	</div>
	
	    <div style="margin:15px auto;text-align:center;">
	    	<s:form action="planUpload">
	    		<s:submit method="applyPlan" cssClass="dark-btn" value="保存还款计划" onclick="return beforeSubmit('是否保存还款计划')" />
	    		<s:submit method="cancel" cssClass="dark-btn" value="作废订单"  onclick="return beforeSubmit('是否作废订单')" />
	    		<s:submit method="returnAction" cssClass="dark-btn" value="返回" />
	    	</s:form>
	    	
	    </div>
	  </div>
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
				//$("#tab").height($("#workframe-wrapper").height());
				var h = $("#workframe-wrapper").height();
				if(h < 648){
					$("#workframe-wrapper").height(700)
				}
			});
			function toPass(){
				parent.openDialog("审核通过确认页面","vetting-detail-pass.html",500,250);
			}
			function beforeSubmit(desc){
				return confirm(desc);
			}
			function statFormatter(cellvalue, options, rowObject){
				if (cellvalue == "未保存" || cellvalue == "逾期" || cellvalue == "欠款冻结中" || cellvalue == "欠款已冻结" || cellvalue == "受理过期" || cellvalue == "申请过期"){
					return "<div style='color:red'>" + cellvalue + "</div>";
				}
				return cellvalue;
			}
			function addMerch(id){
				if(confirm("是否确认关注此商户?")){
					window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
				}
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>

