<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html>

<html>
<head>
	<n:ui includes="form" />
</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我的产品&nbsp;&gt;&gt;&nbsp;发布新产品</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增产品详细信息</span></h3>
				<div class="results report">
				<s:form id="storeform" action="productPublish" namespace="/inst" method = "post" enctype="multipart/form-data">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label"><font color=red>*</font>产品名称</td>
							<td class="data"><input  name="loanPdt.pdtName" class="validate[required,minSize[2]]" value="<s:property  value='loanPdt.pdtName'/>"  maxLength="30"/></td>
							<td class="label"><font color=red>*</font>最快放款时间</td>
							<td class="data"><input  name="loanPdt.creditTerm" class="validate[required,[custom[integer]],min[1]" value="<s:property  value='loanPdt.creditTerm'/>"  maxLength="15"/></td>
						</tr>
						<tr>
							<td class="label"><font color=red>*</font>贷款最低金额(万元)</td>
							<td class="data"><input id="minQuota" name="loanPdt.minQuota" class="validate[required,[custom[integer]],min[1]" /></td>
							<td class="label"><font color=red>*</font>贷款最高金额(万元)</td>
							<td class="data"><input id="maxQuota" name="loanPdt.maxQuota" class="validate[required,[custom[integer]],min[1]" /></td>
						</tr>
						<tr>
							<td class="label"><font color=red>*</font>贷款最短周期</td>
							<td class="data">
							<input id="minTerm" style="width:60px" name="loanPdt.minTerm" class="validate[required,[custom[integer]],min[1]" value="<s:property  value='loanPdt.minTerm'/>" />
							<select id="minTermType" name="minTermType" style="width:120px;" class="validate[required]">
								<option value="">请选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
							</td>
							<td class="label"><font color=red>*</font>贷款最长周期</td>
							<td class="data">
							<input id="maxTerm" style="width:60px" name="loanPdt.maxTerm" class="validate[required,[custom[integer]],min[1]" value="<s:property  value='loanPdt.maxTerm'/>" />
							<select id="maxTermType" name="maxTermType" style="width:120px;" class="validate[required]">
								<option value="">请选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
							</td>
						</tr>
						<tr>
							<td class="label"><font color=red>*</font>最低利率(%)</td>
							<td class="data">
							<select id="rateType" name="rateType" style="width:120px;" class="validate[required]">
								<option value="">请选择利率类型</option>
								<option value="DAY">日利率</option>
								<option value="MONTH">月利率</option>
								<option value="YEAR">年利率</option>
							</select>
							<input style="width:60px" name="loanPdt.rate" class="validate[required,[custom[number2]],min[0.01]" value="<s:property  value='loanPdt.rate'/>" />
							</td>
							<td class="label"><font color=red>*</font>贷款受理地区</td>
							<td class="data" class="validate[required]">
								<s:select list="provMap" name="provinceCode" id="provMap" cssStyle="width:135px;"></s:select>
								<select name="cityCode" id="cityCode" style="width:85px;"></select>
							</td>
						</tr>
						<tr>
							<td class="label"><font color=red>*</font>还款方式</td>
							<td class="data" colspan="3" style="height:60px"><select class="validate[required]" style="float:left;margin-top:20px" name="loanPdt.repayment"  id="repayment"><option value="0">请选择还款方式</option>
								<option value="TERM_LOAN">等额分期还款</option>
								<option value="ONE_TIME">一次性还本</option></select>
								&nbsp;&nbsp;&nbsp;
								<div id="tip" style="color:#ff660a;visibility:hidden;float:left;margin-left:60px"><p>等额分期还款：是指借款的本金和利息分成若干次支付,每次支付相同金额.</p>
								<p>一次性还本:一次归还借款的本金.</p></div>
							</td>
						</tr>
						<tr>
							<td class="label">产品特点简述</td>
							<td class="data" colspan="3"><textarea name="loanPdt.introduce" class="validate[required,maxSize[20]]" rows="1" cols="71"></textarea></td>
						</tr>
						<tr>
							<td class="label">产品特点</td>
							<td class="data" colspan="3"><textarea class="validate[maxSize[1000]]" name="loanPdt.feature"  rows="15" cols="71"></textarea></td>
						</tr>
						<tr>
							<td class="label">申请条件</td>
							<td class="data" colspan="3"><textarea class="validate[maxSize[1000]]" name="loanPdt.condition"  rows="15" cols="71"></textarea></td>
						</tr>
						<tr>
							<td class="label">申请所需材料</td>
							<td class="data" colspan="3"><textarea class="validate[maxSize[1000]]" name="loanPdt.cl"  rows="15" cols="71"></textarea></td>
						</tr>
						<tr>
							<s:hidden name="filetype" value="loanPdtLogo"/>
							<td class="label" >&nbsp;&nbsp;产品LOGO大图片 </td>
							<td class="data" colspan="3"><s:file id="fileforload" name="upload" label="File"/>&nbsp;&nbsp;&nbsp;(建议上传150X70的图片,此图片将用于平台"我要借款"频道上显示)</td>
						</tr>
						<tr>
							<s:hidden name="filetype" value="PdtLogoPage"/>
							<td class="label" >&nbsp;&nbsp;产品LOGO小图片 </td>
							<td class="data" colspan="3"><s:file id="fileforpage" name="upload" label="File"/>&nbsp;&nbsp;&nbsp;(建议上传45X45的图片,此图片将用于平台"首页"频道上显示)</td>
						</tr>
						<tr>
							<td class="data" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="isUpload" name="isUpload" value="1"></input>立即上架</td>
						</tr>
					</table>
					<div class="uop">
						<input type="button" class="dark-btn"  value="确&nbsp;&nbsp;定" onclick="doSubmit();"/> 
						<input type="button" class="dark-btn" value="取&nbsp;&nbsp;消" onclick="cancel()"/>
					</div>
					</s:form>
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$("#repayment").focus(function(){
			$("#tip").css("visibility","visible");
		});
		$("#repayment").blur(function(){
			$("#tip").css("visibility","hidden");
		});
		
		function cancel(){
			window.location.href="${ctx}/inst/productPublish";
		}
		
			$(function() {
				$("#storeform").validationEngine();
				
				$("#provMap").change(function(){              
					$.getJSON("/../province",{provinceCode:$(this).val()},
					function(myJSON){
						var myOptions='<option value="">所有地区</option>';                  
						for(var i=0;i<myJSON.provinceList.length;i++){    
							myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
			             }   
			             $("#cityCode").empty();                  
			             $("#cityCode").html(myOptions);              
			       	});          
			   	});
				$("#provMap").change();
			});
			function doSubmit(){
				if (!$("#storeform").validationEngine("validate")) {
					return false;
				}
				if(document.getElementById("fileforload").value=="") {
			    	alert("请先选择产品LOGO图片！");
			    	return false;
			    }
				if(document.getElementById("fileforpage").value=="") {
			    	alert("请先选择首页LOGO图片！");
			    	return false;
			    }
				if(parseInt($('#maxQuota').val())<parseInt($('#minQuota').val())){
					alert("贷款最高金额不能小于贷款最低金额!");
					return false;
				}
				if(parseInt($('#maxTerm').val())<parseInt($('#minTerm').val())){
					alert("贷款最长周期不能小于贷款最短周期!");
					return false;
				}
				if($('#repayment').val()==0){
					alert("请选择一个还款方式!");
					return false;
				}
				var isUpload = $("#isUpload").attr("checked");
				if(isUpload == "checked"){
					isUpload = "true";
				}else{
					isUpload = "false";
				}
				document.storeform.action="${ctx}/inst/productPublish!saveProduct?isUpShelves="+isUpload;	
	            document.storeform.submit();
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>