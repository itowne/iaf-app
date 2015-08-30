<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<head>
<title>汇融易 - 机构管理后台</title>
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
<base target="_self"/>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
  <div class="report-tab-area">
  	<div id="content" class="report" >
	    <h3 style="line-height:42px;text-align:center;font-size:15px;">放款详情</h3>	    
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<td class="label">贷款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th class="label" style="text-align: center">贷款编号</th>
	    					<th class="label" style="text-align: center">贷款金额(元)</th>
	    					<th class="label" style="text-align: center">放贷机构</th>
	    				</tr>
	    				<tr align="center">
	    					<td><s:if test="batchCredit">
								<s:iterator value="#request.ords" id="ord" status="st">
									<s:property value="#ord.loanOrdId" />,
									</s:iterator>
									</s:if>
									<s:else>
								<s:property value="#request.loanOrd.loanOrdId"/>
							</s:else></td>
	    					<td><s:property value="%{getText('format.money', {#request.trans.orderAmount})}" />（元）</td>
	    					<td><s:property  value='%{#request.ist.instName}'/></td>
	    				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">收款人信息</td>
	    		<td class="label">收款方</td>
	    		<td class="data">
	    		<s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.accountName" />,
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.accountName"/>
				</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人姓名</td>
	    		<td class="data">
				<s:property value="#request.merch.merchName" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人卡(帐)号</td>
	    		<td class="data">
	    			<s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.accountNo" />
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.accountNo"/>
				</s:else>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">收款人开户银行</td>
	    		<td class="data"><s:if test="batchCredit">
				<s:iterator value="#request.merchBusiInfoList" id="merchBusiInfos" status="st">
					<s:property value="#merchBusiInfos.bank" />
				</s:iterator>
				</s:if>
				<s:else>
					<s:property value="#request.merchBusiInfo.bank"/>
				</s:else></td>
	    	</tr>
	    	<tr>
	    		<td class="label">付款方信息</td>
	    		<td class="label">付款方</td>
	    		<td class="data"><s:property value="%{#request.instBusiInfo.RepaymentBank}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">交易信息</td>
	    		<td class="label">支付方式</td>
	    		<td class="data">
	    		<input type="radio" name="xxx" checked="checked"/>平台内放款<br/>
	    		<p><strong>平台内放款</strong>：是经汇融易平台系统进行转账放款.</p>
	    		<p><strong>平台外放款</strong>：是不经过汇融易平台系统放款，需自行联系商户进行放款.</p>
	    		<hr></hr>
	    		<input type="radio" name="xx" checked="checked"/>金掌柜 &nbsp;&nbsp;<input type="radio" name="xx" disabled="disabled"/>代扣<font style="color:red">(暂未开通)</font>&nbsp;&nbsp;<input type="radio" name="xx" disabled="disabled"/>银联在线<font style="color:red">(暂未开通)</font><br/>
	    		<p><strong>金掌柜</strong>：是指广东汇卡特约商户，通过金掌柜pos机刷卡进行对外付款支付.</p>
	    		<p><strong>代扣</strong>：是指经商户授权，由系统根据批扣文件，自动从付款方指定的银行账号进行扣款支付.</p>
	    		<p><strong>银联在线</strong>：是指使用中国银联网上的无卡交易方式支付.</p>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">放款总金额(元)</td>
	    		<td class="data"><s:property value="%{getText('format.money', {#request.loanOrd.quota})}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">手续费(元)</td>
	    		<td class="data"><s:property value="%{#request.charge}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">合计(元)</td>
	    		<td class="data"><s:property value="%{getText('format.money', {#request.trans.orderAmount})}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">备注</td>
	    		<td class="data" style="padding:5px;" colspan="2"><textarea name="memo" style="width:95%;" ></textarea></td>
	    	</tr>
	    </table>
	    <div>
	    <p>温馨提示：</p> 

<p>1、选择“金掌柜”的支付方式还款，需要用到刷卡机，请确保您的刷卡机已经连接正常。</p>

<p>2、“金掌柜”的支付方式，目前只支持国内各大商业银行借记卡转账，暂不支持信用卡转账。</p>

<p>3、到账时间：正常清算日期为T+1（T代表交易当天），节假日顺延。到账时间具体以银行处理时间为准。</p>
	    
	    </div>
	    <div style="margin:15px auto;text-align:center;">
	    	<s:form target="" action="prodCredit">
	    	<div id="trans">
	    	<s:if test="batchCredit">
	    		<s:submit method="batchCredit" onclick="return trans('/inst/loanord/prodCredit!batchCredit')" cssClass="dark-btn" value="交易" />
	    	</s:if>
	    	<s:else>
	    		<s:submit method="credit" onclick="return trans('/inst/loanord/prodCredit!credit')" cssClass="dark-btn" value="交易" />
	    	</s:else>
	    	<input type="button" onclick="close1();" class="dark-btn" value="取消" />
	    	</div>
	    	<div id="btn" style="display:none;">
	    	<input type="button" onclick="close1();" class="dark-btn" value="关闭窗口" />
	    	</div>
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
			function close1(){
				parent.jDialogClose($("body").attr("id"));
			}
			
			function trans(url){
				window.open("${ctx}" + url);
				$("#trans").hide();
				$("#btn").show();
				return false;
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
