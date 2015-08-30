<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<base target="_self"/>
	<body>
	<s:form id="form" target="" action="refundCheck">
	 <div id="content" class="report">
	<h3 style="line-height:32px;text-align:center;font-size:15px;">还款交易对账详情</h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    	<tr>
    		<td class="label" width="150">借贷编号</td>
    		<td class="data" width="230"><s:label name="loanOrd.loanOrdId"></s:label></td>
    		<td class="label" width="120">借款商户(收款方)</td>
    		<td class="data" width="230"><s:label name="merch.merchName"/></td>
    	</tr>
    	<tr>
    		<td class="label" width="150">借款方汇卡商户号</td>
    		<td class="data" width="250"><s:label name="merch.merchNo"/></td>
    		<td class="label">借款申请金额(万元)</td>
    		<td class="data"><s:label name="loanOrd.wangyuanPreQuota"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">借款审批金额(万元)</td>
    		<td class="data"><s:label name="loanOrd.wangyuanQuota"></s:label></td>
    		<td class="label">所属贷款产品</td>
    		<td class="data"><s:label name="loanOrd.pdtName"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">利率</td>
    		<td class="data"><s:label name="loanOrd.pdtRate"></s:label></td>
    		<td class="label" width="100">借款周期</td>
    		<td class="data"><s:label name="loanOrd.term"></s:label><s:label name="loanOrd.termType.desc"/></td>
    	</tr>
    	<tr>
    		<td class="label" width="100">贷款机构(付款方)</td>
    		<td class="data" width="250"><s:label name="inst.instName"/></td>
    		<td class="label" width="100">贷款方汇卡商户号</td>
    		<td class="data" width=250""><s:label name="inst.instId"/></td>
    	</tr>
    	 <tr>
    		<td class="label">申请日期</td>
    		<td class="data"><s:date name="loanOrd.ordDate" format="yyyy-MM-dd HH:mm"/></td>
    		<td class="label">放款交易日期</td>
    		<td class="data"><s:date name="fundFlow.genTime" format="yyyy-MM-dd HH:mm"/></td>
    	</tr>
    	 <tr>
    		<td class="label" width="100">交易流水号</td>
    		<td class="data"><s:label name="loanOrd.loanOrdId"/></td>
    		<td class="label" width="100">交易订单号</td>
    		<td class="data"><s:label name="transMsg.orderNo"/></td>
    	</tr>
    	<tr>
    		<td class="label" width="100">汇卡订单号</td>
    		<td class="data"><s:label name="transMsg.otherSysNo"/></td>
    		<td class="label" width="100">借款用途</td>
    		<td class="data" width="250"><s:label name="loanOrd.purpose"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label" width="100">还款金额(元)</td>
    		<td class="data"><s:label name="fundFlow.amount"/></td>
    		<td class="label" width="100">交易手续费(元)</td>
    		<td class="data">
    		<s:else>
    		
    		</s:else>
    		<s:label name="charge"/></td>
    	</tr>
    	 <tr>
    		<td class="label" width="100">交易金额(元)</td>
    		<td class="data"><s:label name="transMsg.orderAmount"/></td>
    		<td class="label">还款期数</td>
    	 	<td class="data">第<s:label name="fundFlow.period"/>期</td>
    	</tr>
    	 <tr>
    	 	<td class="label">交易日期</td>
    	 	<td class="data"><s:date name="fundFlow.genTime" format="yyyy-MM-dd HH:mm"/></td>
    		<td class="label" width="100">收款卡(帐)号</td>
    		<td class="data"><s:label name="merchBusiInfo.accountNo"/></td>
    	 </tr>
    	<tr>
    		<td class="label" width="100">收款卡(帐)号开户姓名</td>
    		<td class="data"><s:label name="merchBusiInfo.accountName"/></td>
    		<td class="label" width="100">收款卡(账)号开户行</td>
    		<td class="data"><s:label name="merchBusiInfo.bank"/></td>
    	</tr>
    	 <tr>
    		<td class="label" width="100">收款卡(帐)号开户银行行号</td>
    		<td class="data"><s:label name="merchBusiInfo.bankCode"/></td>
    		<td class="label" width="100">支付状态</td>
    		<td class="data">
    		<s:if test='fundFlow.status.toString()=="AUDIT"'>
    		未对账
    		</s:if>
    		<s:else>
    		<s:label name="fundFlow.status.desc"/>
    		</s:else>
    		</td>
    	</tr>
    	<tr>
    		<td class="label">操作备注</td>
    		<td class="data" colspan="3">
    		<s:if test='fundFlow.status.toString()=="SUCCESS"||fundFlow.type.toString()=="OTH_REFUND"||fundFlow.status.toString()=="EXPIRY"'>
    		<s:property value="fundFlow.checkMemo"/>
    		</s:if>
    		<s:else>
    		<s:textarea cols="70" rows="3" name="memo">
    		</s:textarea>
    		</s:else>
    		</td>
    	</tr>
    </table>
    </div>
    <div style="margin:15px auto;text-align:center;">
    <s:if test='fundFlow.status.toString()=="SUCCESS"||fundFlow.type.toString()=="OTH_REFUND"||fundFlow.status.toString()=="EXPIRY"'>
    <s:submit onclick="return close1();" value = "返  回" cssClass="dark-btn"/>
    </s:if>
    <s:else>
    <n:HcAuthButton value="已对账" cssClass="dark-btn" onclick="return beforeSubmit1('是否提交？','audit');" authCode="HKDZ_YDD"/>
   		<n:HcAuthButton value="退  款" cssClass="dark-btn" onclick="return beforeSubmit1('是否提交？','cancel');" authCode="HKDZ_TH"/>
   		<n:HcAuthButton onclick="down()" value="下载对账文件" cssClass="dark-btn" authCode="HKDZ_XZ"/>
   		<s:submit onclick="return close1();" value = "取  消" cssClass="dark-btn"/>
    </s:else>
    </div>
	</s:form>
	<script type="text/javascript">

		function beforeSubmit1(str,method){
			if (confirm(str)){
				window.returnValue= 0;
				document.form.action="${ctx}/backstage/refundCheck!"+method;	
		        document.form.submit();
				return true;
			}
			return false;
		}
		function close1(){
			parent.jDialogClose($("body").attr("id"));
			//window.location.href="${ctx}/backstage/refundcheck";
			return ;
		}
		function down(){
			document.form.action="${ctx}/backstage/refundCheck!downloadOrd";	
	        document.form.submit();
	}
	</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
