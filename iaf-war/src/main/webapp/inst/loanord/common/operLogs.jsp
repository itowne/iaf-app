<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />

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
	    				<td class="data" align="center"><s:property value="%{(#request.pageNum - 1) * 10 + #st.index + 1}" /></td>
			    		<td class="data" align="center"><s:property value="#op.operType.desc" /></td>
			    		<td class="data" align="center"><s:property value="#op.operResult" /></td>
			    		<td class="data" align="center"><s:property value="%{getText('format.time', {#op.genTime})}" /></td>
			    		<td class="data" align="center">
			    		<s:if test="#op.loginName==null">
			    			&nbsp;
			    		</s:if>
			    		<s:else>
			    			<s:property value="#op.loginName" />
			    		</s:else>
			    		</td>
			    		<td class="data" align="center">
			    		<s:if test="#op.roleNames==null">
			    			&nbsp;
			    		</s:if>
			    		<s:else>
			    			<s:property value="#op.roleNames" />
			    		</s:else>
			    		</td>
			    		<td class="data" align="center">
			    		<s:if test='#op.memo==null||#op.memo==""'>
			    			&nbsp;
			    		</s:if>
			    		<s:else>
			    			<s:property value="#op.memo" />
			    		</s:else>
			    		</td>
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
	  </div>
	  <div align="right" style="margin: 20px;width: 90%;">
	  	第<s:property value="#request.pageNum" />页/共<s:property value="#request.pageCount"/>页&nbsp;&nbsp;
	  <a target="loanFrame" class="dark-btn" href='/inst/loanord/operLogDetail?pageNum=0&pageCount=<s:property value="#request.pageCount"/>' >首页</a>&nbsp;&nbsp;
	  <a target="loanFrame" class="dark-btn" href='/inst/loanord/operLogDetail?pageNum=<s:property value="#request.pageNum - 1"/>&pageCount=<s:property value="#request.pageCount"/>' >上一页</a>&nbsp;&nbsp;
	  <a target="loanFrame" class="dark-btn" href='/inst/loanord/operLogDetail?pageNum=<s:property value="#request.pageNum + 1"/>&pageCount=<s:property value="#request.pageCount"/>'>下一页</a>&nbsp;&nbsp;&nbsp;
	  <a target="loanFrame" class="dark-btn" href='/inst/loanord/operLogDetail?pageNum=<s:property value="#request.pageCount"/>&pageCount=<s:property value="#request.pageCount"/>' >末页</a>&nbsp;&nbsp;
	  </div>
<script type="text/javascript">
parent.window.curTab = "operLogDetail";
</script>


