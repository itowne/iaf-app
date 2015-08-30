<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/nl-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
</head>

<body>
<div id="workframe-wrapper" class="clearfix">
<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构申请不受理详情</p>
		</div>
  <div id="content" class="report">
  <h3 class="title"><span>详情</span></h3>
  <s:form id="instMainForm" action="instApplyRequest!cancel" namespace="/backstage/instmanager" onsubmit="return check()">
 	<div  style="margin-top: 50px"><div style="color:red;font-size: 18px;font-weight: bold;margin-left: 280px""> 如果不受理该机构的申请，请选择不受理原因：</div></div>
 	<div style="margin-top: 50px;font-size: 15px;margin-left: 350px"><input type="radio" name="radio" value="联系方式不实"/>1.联系方式不实.</div>
 	<div style="margin-top: 50px;font-size: 15px;margin-left: 350px"><input type="radio" name="radio" value="不具备审批条件"/>2.不具备审批条件.</div>
 	<div style="margin-top: 50px;font-size: 15px;margin-left: 350px"><input type="radio" name="radio" value="机构主动取消合作"/>3.机构主动取消合作.</div>
 	<div style="margin-top: 50px;font-size: 15px;margin-left: 350px"><input type="radio" name="radio" value="4"/>4.其他情况(请输入详细原因说明)<br><br>
 	<div id="memo">
 	<textarea name="reason" id="reason" cols="55" rows="5"></textarea></div>
 	</div>
 	<div align="center" style="margin-top: 40px">
 	<input type="submit" class="dark-btn" value="确定" />&nbsp;&nbsp;&nbsp;<input class="dark-btn" type="button" name="" value="取消" onclick="after()"/></div>
	</s:form>
	</div>
	</div>
<script type="text/javascript">
	$("#memo").hide();
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
		window.setTimeout(reinitLoanIframe,300);
		window.curTab = "loanOrdApply";
		$("#loanFrame").bind("load",function(){
			$(".tab-title li").removeClass("cur");
			$("#"+window.curTab).addClass("cur");
		});
	});
	
	$("input:radio[type='radio']").click(function(){
		if(this.checked==true&&this.value==4){
			$("#memo").show();
		}else{
			$("#memo").hide();
		}
	})
	
	function check(){
		if($('input:radio[name="radio"]:checked').length<=0){
			alert("请选择原因");
			return false;
		}else{
			if(confirm("您确定不受理该机构的注册申请?")){
			 document.instMainForm.submit();
			}else{
				return false;
			}
		}
		//document.instMainForm.action="${ctx}/backstage/instmanager/";	
	}
	function after(){
		window.location.href="${ctx}/backstage/instmanager/instApplyRequest";
	}
</script> 
</body>
