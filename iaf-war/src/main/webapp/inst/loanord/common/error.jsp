<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div id="content" class="report">
 <h3 class="title"><span>借贷状态：<em style="color:red;"><s:property value="#request.loanOrd.ordStat.desc" /></em></span>&nbsp;&nbsp;<span class="related-menu"><a href="javascript:runQQ('${merch.qqUid}')">在线联系</a></span>&nbsp;&nbsp;<span class="related-menu"><a href="javaScript:addMerch(${loanOrd.imerch})">加入我的关注</a></span></h3>
	<div class="tab-content">
	 <s:actionerror />
	 <s:actionmessage />
	</div>
</div>
<script type="text/javascript">
function addMerch(id){
	if(confirm("是否确认关注此商户?")){
		window.location.href="${ctx}/inst/instCollMerch!add?imerch="+id	;
	}
}
</script>

