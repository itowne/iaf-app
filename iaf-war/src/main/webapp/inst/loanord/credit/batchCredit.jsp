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

<body style="position:relative;">
<s:form action="prodCredit">
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：放款处理&nbsp;&gt;&gt;&nbsp;产品明细</p>
  </div>
  <div id="content" class="report">
  <div class="tab-content">
  <h3 class="title"><span>批量转账信息</span></h3>
  	
    <table width="850" border="0" cellspacing="0" cellpadding="0" class="data-table report">
    	<thead>
    		<tr><td colspan="6" class="label-center">批量转账信息</td></tr>
    	</thead>
    	<tr >
    		<td class="label-center">借贷信息</td>
    		<td class="data" style="padding:0;border:0;" colspan="2">
    		<table width="100%"  cellspacing="0" cellpadding="0" class="data-table report" style="border:0;">
    			<tr>
    				<th>序号</th>
		    		<th>借贷编号</th>
		    		<th>借款商户</th>
		    		<th>借款金额</th>
		    		<th>放贷机构</th>
    			</tr>
    			<s:iterator value="#request.ords" id="ord" status="st">
		   		<tr>
		   			<td><s:property value="%{#st.index + 1}" /></td>
		   			<td><s:property value="#ord.loanOrdId"/></td>
		   			<td><s:property value="#ord.merchName"/></td>
		   			<td><s:property value="#ord.quota"/></td>
		   			<td><s:property value="#ord.instName"/></td>
		   		</tr>
		   		</s:iterator>
    		</table>
    		</td>
    	</tr>
    	<tr>
    		<td class="label-center" rowspan="4">收款人信息</td>
    		<td class="label">收款人：</td>
    		<td class="data">
    		<s:iterator value="#request.ords" status="st" id="ord">
		    				<s:property value="#ord.merchName" />,
		    			</s:iterator>
		    </td>
		 </tr>
		 <tr>
    		<td class="label">收款人卡号：</td>
    		<td class="data"></td>
		 </tr>
		 <tr>
		 	<td class="label">收款人卡号：</td>
		 	<td class="data"></td>
		 </tr>
		 <tr>
		 	<td class="label">收款人开户行：</td>
		 	<td class="data"></td>
		 </tr>
    	<tr>
    		<td class="label-center" rowspan="3">付款人信息</td>
    		<td class="label">付款人：</td>
    		<td class="data"><s:textfield name="payAccountName"/></td>
    	</tr>
    	<tr>
    		<td class="label">付款人账号：</td>
    		<td class="data"><s:textfield name="payAccountNo"/></td>
    	</tr>
    	<tr>
    		<td class="label">付款人开户行卡号：</td>
    		<td class="data"><s:textfield name="payBankName"/></td>
    	</tr>
    	<tr>
    		<td class="label-center" rowspan="2">汇款信息</td>
    		<td class="label">批量转账金额：</td>
    		<td class="data">￥<s:property value="batchAmount"/></td>
    	</tr>
    	<tr>
    		<td class="label">转账方式：</td>
    		<td class="data">
    			<input name="transType"  type="radio" checked="checked"  value="HICARD">金掌柜</input>
    		</td>
    	</tr>
    	<tr>
    		<td class="label-center">备注：</td>
    		<td class="data" colspan="2"><s:textarea cols="60" rows="3" name="memo"></s:textarea></td>
    	</tr>		
    </table>
    <div style="margin:15px auto;text-align:center;">
    		<s:submit onclick="return beforeSubmit2();" cssClass="dark-btn" value="确定" />
    		<s:submit method="returnAction" cssClass="dark-btn" value="取消" />
    </div>
  </div>
</div>
</div>
</s:form>
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
			function beforeSubmit2(){
				parent.jDialog("放款","${ctx}/inst/loanord/prodCredit!viewTransMsg.action",860,480,true,function(){
					window.location = "/inst/loanord/prodCredit";
				});
				return false;
			}
			
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>

