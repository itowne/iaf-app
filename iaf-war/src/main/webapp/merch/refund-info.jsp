<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我要还款</p>
  </div>
  <div id="content" class="report">
    <h3 class="title"><span>还款信息</span></h3>
    <div style="padding:30px 100px;">
   <s:form action="merchMyRefund">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
	    	<tr>
	    		<td class="label">借款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th style="background: #F3F9FD">借款编号</th>
	    					<th style="background: #F3F9FD">借款金额(万元)</th>
	    					<th style="background: #F3F9FD">需还款总期数</th>
	    					<th style="background: #F3F9FD">放贷机构</th>
	    				</tr>
	    				<tr align="center">
	    				<!-- style="border-width: 0.3px;border-style: solid;border-color: #CCCCCC;" -->
	    					<td>
	    					<a href="javaScript:viod(0)" onclick="Returnetail(<s:property  value='loanOrd.loanOrdId'/>)"><s:property  value='loanOrd.loanOrdId'/></a></td>
	    					<td><s:property  value='loanOrd.wangyuanQuota'/></td>
	    					<td><s:property  value='planList.size'/>期</td>
	    					<td><s:property  value='inst.instName'/></td>
	    				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label">本次还款详情</td>
	    		<td class="data" colspan="2" style="padding:0;">
	    			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" >
	    				<tr>
	    					<th style="background: #F3F9FD">还款编号</th>
							<th style="background: #F3F9FD">当前还款期数</th>
							<th style="background: #F3F9FD">计划还款日期</th>
							<th style="background: #F3F9FD">还款金额(元)</th>
							<th style="background: #F3F9FD">还款本金(元)</th>
							<th style="background: #F3F9FD">利息(元)</th>
							<th style="background: #F3F9FD">剩余还款金额(元)</th>
	    				</tr>
	    				<s:iterator value="payplanList" id="id" status="status"> 
							<tr align="center">
								<td><s:property value='#id.iloanOrdPlan'/></td>
								<td>第<s:property value='#id.period'/>期</td>
								<td><fmt:formatDate value="${id.refundDate}" pattern="yyyy-MM-dd"/></td>
								<td><s:property value='#id.repayment'/></td>
								<td><s:property value='#id.capital'/></td>
								<td><s:property value='#id.interest'/></td>
								<td><s:property value='#id.remainAmount'/></td>
							</tr>
							</s:iterator>
							<tr align="center">
								<td>合计</td>
								<td><s:label name="payplanList.size"/>期</td>
								<td>--</td>
								<td><s:label name="curRepayment"/></td>
								<td><s:label name="curCapital"/></td>
								<td><s:label name="curInterest"/></td>
								<td>--</td>
							</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">收款人信息</td>
	    		<td class="label">收款方</td>
	    		<td class="data"><s:property  value='inst.instName'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">开户姓名</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentName'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">银行卡(帐)号</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentNo'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">开户银行</td>
	    		<td class="data"><s:property  value='instBusiInfo.repaymentBank'/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">付款方信息</td>
	    		<td class="label">付款方</td>
	    		<td class="data"><s:property value="%{merch.merchName}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label" rowspan="4">交易信息</td>
	    		<td class="label">支付方式</td>
	    		<td class="data"><input type="radio" name="xx" checked="checked"/>金掌柜 &nbsp;&nbsp;<input type="radio" name="xx" disabled="disabled"/>代扣<font style="color:red">(暂未开通)</font>&nbsp;&nbsp;<input type="radio" name="xx" disabled="disabled"/>银联在线<font style="color:red">(暂未开通)</font><br/>
	    		<p><strong>金掌柜</strong>：是指广东汇卡特约商户，通过金掌柜pos机刷卡进行对外付款支付.</p>
	    		<p><strong>代扣</strong>：是指经商户授权，由系统根据批扣文件，自动从付款方指定的银行账号进行扣款支付.</p>
	    		<p><strong>银联在线</strong>：是指使用中国银联网上的无卡交易方式支付.</p></td>
	    	</tr>
	    	<tr>
	    		<td class="label">还款总金额(元)</td>
	    		<td class="data"><s:property value="%{captial}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">交易手续费(元)</td>
	    		<td class="data"><s:property value="%{charge}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">合计(元)</td>
	    		<td class="data"><s:property value="%{payAmt}"/></td>
	    	</tr>
	    	<tr>
	    		<td class="label">还款备注</td>
	    		<td class="data" style="padding:5px;" colspan="2"><textarea name="memo" style="width:95%;"></textarea></td>
	    	</tr>
	    </table>
	     <div>
	    <p>温馨提示：</p> 

<p>1、选择“金掌柜”的支付方式还款，需要用到刷卡机，请确保您的刷卡机已经连接正常。</p>

<p>2、“金掌柜”的支付方式，目前只支持国内各大商业银行借记卡转账，暂不支持信用卡转账。</p>

<p>3、到账时间：正常清算日期为T+1（T代表交易当天），节假日顺延。到账时间具体以银行处理时间为准。</p>
	    </div>
	    	<div class="uop">
	    	
	    	<s:submit onclick="return refund();" cssClass="dark-btn" value="交  易"/>
	    	<s:submit method="execute" value="取  消" cssClass="dark-btn"/>
	    	
	    	</div>
	    </s:form>
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
			
			function usedPayer(){
				var payerName = '${merchUsedPayer.payerName}';
				var payerCardNo =  '${merchUsedPayer.payerCardNo}';
				var payerBankName ='${merchUsedPayer.payerBankName}';
				var bankCode='${merchUsedPayer.bankCode}';
				if(payerName == ""){
					alert("没有常用付款人信息!");
					return false;
				}
				$("#payerName").attr("value",payerName);
				$("#payerCardNo").attr("value",payerCardNo);
				$("#payerBankName").attr("value",payerBankName);
				$("#bankCode").attr("value",bankCode);
			}
			
			function Redetail(id){
				window.location.href="${ctx}/merch/merchMyRefund!viewDetail?loanId="+id;
			}
			function Returnetail(id){
				window.location.href="${ctx}/merch/merchMyRefund!viewDetail?iloanOrd="+id;
			}
			function refund(){
				var addFlag = $("#addFlag").attr("checked");
				var payerName="";
				var payerCardNo="";
				var payerBankName="";
				var bankCode="";
				if(addFlag == "checked"){
					addFlag = "true";
					payerName=$('#payerName').val();
					payerCardNo=$('#payerCardNo').val();
					payerBankName=$('#payerBankName').val();
					bankCode=$('#bankCode').val();
					parent.jDialog("还款","${ctx}/merch/merchMyRefund!viewTransMsg.action?method:viewTransMsg&method:viewTransMsg&addFlag="+addFlag+"&payerName="+payerName+"&payerCardNo="+payerCardNo+"&payerBankName="+payerBankName+"&bankCode="+bankCode,460,300,false,function(){
						window.location = "${ctx}/merch/merchMyRefund";
					});
					return false;
				}else{
					addFlag = "false";
					payerName=$('#payerName').val();
					payerCardNo=$('#payerCardNo').val();
					payerBankName=$('#payerBankName').val();
					bankCode=$('#bankCode').val();
					parent.jDialog("还款","${ctx}/merch/merchMyRefund!viewTransMsg.action?method:viewTransMsg&method:viewTransMsg&addFlag="+addFlag+"&payerName="+payerName+"&payerCardNo="+payerCardNo+"&payerBankName="+payerBankName+"&bankCode="+bankCode,460,300,false,function(){
						window.location = "${ctx}/merch/merchMyRefund";
					});
					return false;
				}
			}
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
