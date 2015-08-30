<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<base target="_self" />

	<body>
	<div id="content" class="report">
	<s:form id="form" target="" action="freezeCheck">
	<h3 style="line-height:32px;text-align:center;font-size:15px;">冻结申请详情</h3>
    <table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
    	<tr>
    		<td class="label">借贷编号</td>
    		<td class="data"><s:label name="loanOrd.loanOrdId"></s:label></td>
    		<td class="label">申请冻结金额(元)</td>
    		<td class="data"><s:label name="applyRequest.amount"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">冻结申请机构名称</td>
    		<td class="data"><s:label name="applyRequest.instName"></s:label></td>
    		<td class="label">机构汇卡商户号</td>
    		<td class="data"><s:label name="applyRequest.instNo"/></td>
    	</tr>
    	<tr>
    		<td class="label">被冻结商户名称</td>
    		<td class="data"><s:label name="applyRequest.merchName"></s:label></td>
    		<td class="label">商户汇卡商户号</td>
    		<td class="data"><s:label name="applyRequest.merchNo"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">冻结申请日期</td>
    		<td class="data">
    		<s:if test='applyRequest.genTime==""||applyRequest.genTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.genTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else></td>
    		<td class="label">冻结申请备注</td>
    		<td class="data"><s:label name="applyRequest.reason"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">冻结受理日期</td>
    		<td class="data">
    		<s:if test='applyRequest.freezeAcceptTime==""||applyRequest.freezeAcceptTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.freezeAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else>
    		</td>
    		<td class="label">冻结受理备注</td>
    		<td class="data"><s:label name="applyRequest.freezeAcceptMemo"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">冻结完成日期</td>
    		<td class="data">
    		<s:if test='applyRequest.freezeSuccessTime==""||applyRequest.freezeSuccessTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.freezeSuccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else>
    		</td>
    		<td class="label">冻结备注</td>
    		<td class="data">
    		<s:if test='freezefile==null||freezefile==""'>
    			未上传冻结凭证
    		</s:if>
    		<s:else>
    		<a href="/DrawImage.do?id=${freezefile.ifile}&type=exp">冻结凭证下载</a>
    		</s:else>
    		</td>
    	</tr>
    	<tr>
    		<td class="label">解冻申请日期</td>
    		<td class="data">
    		<s:if test='applyRequest.unFreezeApplyTime==""||applyRequest.unFreezeApplyTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.unFreezeApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else>
    		</td>
    		<td class="label">解冻申请备注</td>
    		<td class="data"><s:label name="applyRequest.unFreezeApplyMemo"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">解冻受理日期</td>
    		<td class="data">
    		<s:if test='applyRequest.unFreezeAcceptTime==""||applyRequest.unFreezeAcceptTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.unFreezeAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else>
    		</td>
    		<td class="label">解冻受理备注</td>
    		<td class="data"><s:label name="applyRequest.unFreezeAcceptMemo"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">解冻完成日期</td>
    		<td class="data">
    		<s:if test='applyRequest.unFreezeSuccessTime==""||applyRequest.unFreezeSuccessTime==null'>
    		&nbsp;
    		</s:if>
    		<s:else>
    		<fmt:formatDate value="${applyRequest.unFreezeSuccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</s:else>
    		</td>
    		<td class="label">解冻备注</td>
    		<td class="data"><s:label name="applyRequest.unFreezeSuccessMemo"></s:label></td>
    	</tr>
    	<tr>
    		<td class="label">状态</td>
    		<td class="data"><s:label name="applyRequest.stat.desc"/></td>
    		<td class="label">申请凭证图片</td>
    		<td class="data"><s:if test='unfreezefile==null||freezefile==""'>
    			未上传解冻凭证
    		</s:if>
    		<s:else>
    		<a href="/DrawImage.do?id=${unfreezefile.ifile}&type=exp">解冻凭证下载</a>
    		</s:else></td>
    	</tr>
    	<tr>
    		<td class="label">操作备注</td>
    		<td class="data" colspan="3"><textarea name="memo" id="memo" rows="3" cols="60"></textarea></td>
    	</tr>
    </table>
    </div>
   
    <div style="margin:15px auto;text-align:center">
    	<s:if test='applyRequest.stat.toString()=="FREEZE_APPLY"'>
    		<input type="button" class="dark-btn" value="冻结受理" onclick="proc('冻结受理','freezeAccept')"/>
    		<input type="button" class="dark-btn" value="冻结不受理" onclick="proc('不受理冻结','refuseFreezeAccept')"/>
    	</s:if>
    	<s:elseif test='applyRequest.stat.toString()=="UNFREEZE_APPLY"'>
    		<input type="button" class="dark-btn" value="解冻受理" onclick="proc('解冻受理','unFreezeAccept')"/>
    		<input type="button" class="dark-btn" value="解冻不受理" onclick="proc('不受理解冻','unRefuseFreezeAccept')"/>
    	</s:elseif>
    	<s:elseif test='applyRequest.stat.toString()=="FREEZE_ACCEPT"'>
    		<input type="button" class="dark-btn" value="冻结成功" onclick="proc('冻结','freezeSuccess')"/>
    		<input type="button" class="dark-btn" value="冻结失败" onclick="proc('不冻结','freezeFaile')"/>
    	</s:elseif>
    	<s:elseif test='applyRequest.stat.toString()=="UNFREEZE_ACCRPT"'>
    		<input type="button" class="dark-btn" value="解冻成功" onclick="proc('解冻','unFreezeSuccess')"/>
    		<input type="button" class="dark-btn" value="解冻失败" onclick="proc('不解冻','unFreezeFaile')"/>
    	</s:elseif>
    	<input type="button" class="dark-btn" value="取消" onclick="return close1();"/>
    </div>
	</s:form>
	<script type="text/javascript">
		function beforeSubmit1(str,method){
			if (confirm(str)){
				window.returnValue= 0;
				document.form.action="${ctx}/backstage/freezeCheck!"+method;	
		        document.form.submit();
				return true;
			}
			return false;
		}
		
		function beforeSubmit2(str,method){
			if (confirm(str)){
				window.returnValue= -1;
				document.form.action="${ctx}/backstage/freezeCheck!"+method;	
		        document.form.submit();
				return true;
			}
			return false;
		}
		function close1(){
			parent.jDialogClose($("body").attr("id"));
			return false;
		}
		
		function cancel(){
			window.history.go(-1);
		}
		
		function proc(tip,url){
			var memo = $("#memo").text();
			if(confirm("确认要"+tip+"该订单吗?")){
				window.location.href="${ctx}/backstage/freezeCheck!"+url+"?memo="+memo;
			}
		}
	</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
