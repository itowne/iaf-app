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
    <p>当前位置：放款处理&nbsp;&gt;&gt;&nbsp;受理进度情况</p>
  </div>
  <div class="how-to-ask">
   	<strong>借贷处理流程：</strong>&nbsp;<img src="/resources/images/mp/bg-step3.gif" width="737" height="24" alt=""/>
  </div>
  <div class="report-tab-area">
  	<ul class="tab-title clearfix">
  		<li>借款申请</li>
  		<li class="cur">申请受理</li>
  		<li>还款计划</li>
  		<li>借贷信用</li>
  		<li>经营数据报告</li>
  	</ul>
  	<div id="content" class="report" >
	    <h3 class="title"><span>借款状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc"/></em></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:addMerch(${loanOrd.iMerch});">加入我的关注</a></span></h3>
	    <div class="tab-content">
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">受 理 进 度 情 况</h3>
	    <table width="800" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<th>序号</th>
	    		<th>处理</th>
	    		<th>处理结果</th>
	    		<th>处理日期</th>
	    		<th>操作人员</th>
	    		<th>操作员角色</th>
	    		<th>操作备注</th>
	    	</tr>
	    	<s:if test="%{#request.operLogs.size > 0}">
	    		<s:iterator  value="#request.operLogs" status="st" id="op">
	    			<tr>
	    				<td class="data"><s:property value="%{#st.index + 1}" /></td>
			    		<td class="data"><s:property value="#op.operType.desc" /></td>
			    		<td class="data"><s:property value="#op.operResult" /></td>
			    		<td class="data"><s:property value="#op.genTime" /></td>
			    		<td class="data"><s:property value="#op.userName" /></td>
			    		<td class="data"><s:property value="#op.roleNames" /></td>
			    		<td class="data"><s:property value="#op.memo" /></td>
	    			</tr>
	    		</s:iterator>
	    	</s:if>
	    	<s:else>
		    	<tr>
		    		<td class="data">1</td>
		    		<td class="data">&nbsp;</td>
		    		<td class="data">&nbsp;</td>
		    		<td class="data">&nbsp;</td>
		    		<td class="data">&nbsp;</td>
		    		<td class="data">&nbsp;</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	
		    </s:else>
	    </table>
	    <div style="margin:15px auto;text-align:center;">
	    	<s:form action="prodCredit">
	    		<s:submit method="credit" cssClass="dark-btn" value="汇融易转账" />
	    		<s:submit method="othCredit" cssClass="dark-btn" value="平台外转账" />
	    		<s:submit method="cancel" cssClass="dark-btn" onclick="return beforeSubmit('是示作废该订单？')" value="作废订单" />
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
			function addMerch(id){
				if(confirm("是否确认关注此商户?")){
					window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
				}
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
