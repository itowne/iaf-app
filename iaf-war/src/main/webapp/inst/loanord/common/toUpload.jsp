<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />

	    <h3 style="line-height:42px;text-align:center;font-size:15px;">还款计划</h3>
	    <div  style="margin:10px auto;width:100%">
								
	    	<div style="margin:15px auto;text-align:center;">
	    			<s:form action="planMgr" method="POST" enctype="multipart/form-data">
	    			<s:file name="plan" id="upload" label="还款计划文件"></s:file>
	    			<s:submit method="upload" onclick="return checkUpload();" cssClass="dark-btn" value="上传"></s:submit>
	    			<a href="${ctx}/resources/还款计划模版.xls" target="_blank" class="dark_btn" >下载还款计划模版</a>
	    			</s:form>
	    	</div>
	  </div>
	  <script type="text/javascript">
	  function checkUpload(){
			if($("#upload").val()==""){
				alert("请选择一个还款计划文件!");
				return false;
			}
			return true;
		}
	   </script>


