<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<body>
			<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
			<n:ui includes="form"/>
			<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
			<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;修改现场调查报告</p>
			</div>
			<div id="content">
				<h3 class="title"><span>修改现场调查报告</span></h3>
				<div class="results report">
				<form action="#" method="post" id="fieldSurvyform" name="fieldSurvyform">
					<input type="hidden" name="imerch" value="<s:property  value='imerch'/>"/>
					<table width="85%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
                        <td rowspan="6" class="label">商户经营信息</td>
                        <td class="label">营业用地性质</td>
                        <td class="data">
                        	<input type="radio" id="manageFieldNatureOWN" name="merchFieldSurvy.manageFieldNature" value="OWN" />自有
                          <input type="radio" id="manageFieldNatureRENT" name="merchFieldSurvy.manageFieldNature" value="RENT" />租用
                        </td>
                        <td class="label">营业用地面积(平方)</td>
                        <td class="data"><input id="manageFieldSquare"  name="merchFieldSurvy.manageFieldSquare"  value="<s:property  value='merchFieldSurvy.manageFieldSquare'/>" /></td>
                       </tr>
                       <tr>
                        <td class="label">经营地段</td>
                        <td colspan="3" class="data">
                        	<input type="radio" id="manageDistrictBUSIN" name="merchFieldSurvy.manageDistrict" value="BUSIN" />商业区
                          <input type="radio" id="manageDistrictINDUSTRY" name="merchFieldSurvy.manageDistrict" value="INDUSTRY" />工业区
                          <input type="radio" id="manageDistrictHOUSE" name="merchFieldSurvy.manageDistrict" value="HOUSE" />住宅
                        </td>
                        </tr>
                       <tr>
                        <td class="label">经营区域</td>
                        <td colspan="3" class="data">
                        	<input type="radio" id="manageAreaDOWNTOWN" name="merchFieldSurvy.manageArea" value="DOWNTOWN" />城区
                          <input type="radio" id="manageAreaSUBURB" name="merchFieldSurvy.manageArea" value="SUBURB" />郊区
                          <input type="radio" id="manageAreaREMOTE" name="merchFieldSurvy.manageArea" value="REMOTE" />边远地区
                        </td>
                       </tr>
                       <tr>
                        <td class="label">营业时间</td>
                        <td class="data">
                        <input type="text" name="merchFieldSurvy.startHour" style="width:30px" id="startHour" value="<s:property  value='merchFieldSurvy.startHour'/>"/>:<input style="width:30px" type="text" name="merchFieldSurvy.startMin" id="startMin" value="<s:property  value='merchFieldSurvy.startMin'/>"/> --
                         <input type="text" name="merchFieldSurvy.endHour" id="endHour" style="width:30px" value="<s:property  value='merchFieldSurvy.endHour'/>"/>:<input style="width:30px" type="text" name="merchFieldSurvy.endMin" id="endMin" value="<s:property  value='merchFieldSurvy.endMin'/>"/>
                        <br>(格式:09:30-17:30)</td>
                        <td class="label">员工人数(人)</td>
                        <td class="data"><input id="manageScale"  name="merchFieldSurvy.manageScale"  value="<s:property  value='merchFieldSurvy.manageScale'/>" /></td>
                       </tr>
                       <tr>
                        <td class="label">经营范围--主业</td>
                        <td class="data"><input id="manageRangeMajor"  name="merchFieldSurvy.manageRangeMajor"  value="<s:property  value='merchFieldSurvy.manageRangeMajor'/>" /></td>
                        <td class="label">分支机构--范围</td>
                        <td class="data"><input id="manageBranchRange"  name="merchFieldSurvy.manageBranchRange"  value="<s:property  value='merchFieldSurvy.manageBranchRange'/>" /></td>
                       </tr>
                       <tr>
                        <td class="label">经营范围--副业</td>
                        <td class="data"><input id="manageRangeAvocation"  name="merchFieldSurvy.manageRangeAvocation"  value="<s:property  value='merchFieldSurvy.manageRangeAvocation'/>" /></td>
                        <td class="label">分支机构--数量 (个)</td>
                        <td class="data"><input id="manageBranchCount"  name="merchFieldSurvy.manageBranchCount"  value="<s:property  value='merchFieldSurvy.manageBranchCount'/>" /></td>
                       </tr>
                       <tr>
                        <td rowspan="16" class="label">考察情况及意见</td>
                        <td colspan="4" class="label" style="text-align: left">是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件</td>
                       </tr>
                       <tr>
                       <td class="data"><input type="radio" id="inspectLicYES" name="merchFieldSurvy.inspectLic" value="YES"/>是,符合规定</td>
		<td class="data"><input type="radio" id="inspectLicNO" name="merchFieldSurvy.inspectLic" value="NO"/>否,原因:</td>
		<td class="data" colspan="2"><input id="inspectLicDesc" name="merchFieldSurvy.inspectLicDesc"  value="<s:property  value='merchFieldSurvy.inspectLicDesc'/>" /> </td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">是否了解商户有相对应的财务/销售报表，销售凭证以证明其营业时间和月平均银行卡营业额（人民币）</td>
                       </tr>
                       <tr>
                       	<td class="data"><input type="radio"  id="inspectReportYES" name="merchFieldSurvy.inspectReport" value="YES"/>是</td>
               			<td class="data"><input type="radio"  id="inspectReportNO" name="merchFieldSurvy.inspectReport" value="NO" />否，原因:</td>
                       	<td class="data" colspan="2"><input id="inspectReportDesc" name="merchFieldSurvy.inspectReportDesc" value="<s:property  value='merchFieldSurvy.inspectReportDesc'/>"/></td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">是否对收银员进行业务知识和操作技能的资格审核</td>
                       </tr>
                       <tr>
                       	<td class="data"><input type="radio" id="inspectKnowYES" name="merchFieldSurvy.inspectKnow" value="YES"/>是</td>
                        	<td class="data"><input type="radio" id="inspectKnowNO" name="merchFieldSurvy.inspectKnow" value="NO" />否，原因:</td>
                       	<td class="data" colspan="2"><input id="inspectKnowDesc" name="merchFieldSurvy.inspectKnowDesc" value="<s:property  value='merchFieldSurvy.inspectKnowDesc'/>"/></td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">是否对商户进行实地考察</td>
                       </tr>
                       <tr>
                       	<td class="data"><input type="radio" id="inspectOnsiteYES" name="merchFieldSurvy.inspectOnsite" value="YES"/>是，在商户营业时间进行实地考察。</td>
                        	<td class="data"><input type="radio" id="inspectOnsiteNO" name="merchFieldSurvy.inspectOnsite" value="NO" />否，原因:</td>
                       	<td class="data" colspan="2"><input id="inspectOnsiteDesc" name="merchFieldSurvy.inspectOnsiteDesc" value="<s:property  value='merchFieldSurvy.inspectOnsiteDesc'/>"/></td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">商户营业范围与营业执照登记相符</td>
                       </tr>
                       <tr>
                        <td class="data"><input type="radio" id="inspectRangeYES" name="merchFieldSurvy.inspectRange" value="YES" />是，营业范围与营业执照登记相符</td>
                        	 <td class="data"><input type="radio" id="inspectRangeNO" name="merchFieldSurvy.inspectRange" value="NO" />不符，原因：</td>
                        	 <td class="data" colspan="2"><input id="inspectRangeDesc" name="merchFieldSurvy.inspectRangeDesc" value="<s:property  value='merchFieldSurvy.inspectRangeDesc'/>"/></td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">是否有足够的存贷来支持销售</td>
                       </tr>
                       <tr>
                       	<td class="data"><input type="radio" id="inspectLoanYES" name="merchFieldSurvy.inspectLoan" value="YES" />是。</td>
                        	<td class="data" colspan="3"><input type="radio" id="inspectLoanNO" name="merchFieldSurvy.inspectLoan" value="NO" />否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。</td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">商户的经营状况</td>
                       </tr>
                       <tr>
                        <td class="data" colspan="4">
                        	<input type="radio" id="inspectBusiGOOD" name="merchFieldSurvy.inspectBusi" value="GOOD" />商户经营总体良好。<br/>
                          <input type="radio" id="inspectBusiORDINARY" name="merchFieldSurvy.inspectBusi" value="ORDINARY" />商户经营状况一般。<br/>
                          <input type="radio" id="inspectBusiWORSE" name="merchFieldSurvy.inspectBusi" value="WORSE" />商户经营状况较差，但会有改善。<br/>
                          <input type="radio" id="inspectBusiBAD" name="merchFieldSurvy.inspectBusi" value="BAD" />商户经营状况差，风险很高。
                        </td>
                       </tr>
                       <tr>
                        <td colspan="4" class="label" style="text-align: left">商户的考察意见</td>
                       </tr>
                       <tr>
                        <td class="data" colspan="4">
                          <input type="radio" id="inspectOpinHIGHQUALITY" name="merchFieldSurvy.inspectOpin" value="HIGHQUALITY" />优质商户。<br/>
                          <input type="radio" id="inspectOpinLOWRISK" name="merchFieldSurvy.inspectOpin" value="LOWRISK" />一般商户，风险较低。<br/>
                          <input type="radio" id="inspectOpinGENERALRISK" name="merchFieldSurvy.inspectOpin" value="GENERALRISK" />一般商户，有一定风险，需要对其业务有所监控。<br/>
                          <input type="radio" id="inspectOpinHIGHRISK" name="merchFieldSurvy.inspectOpin" value="HIGHRISK" />高风险等级商户，暂不考虑发展。
                        </td>
                       </tr>
                       <tr>
                       	<td class="label">调查时间</td>
                       	<td class="data" colspan="4"><input  id="survyTime" readonly="readonly" name="merchFieldSurvy.survyTime" value="<s:property value="%{merchFieldSurvy.survyTime}"/>"/></td>
                       </tr>
					</table>
					<div class="uop">
						<n:HcAuthButton  cssClass="dark-btn" value="保存" authCode="SHGL_SHZLGL_XCDC" onclick="return sub();"/>
						<input type="button" class="dark-btn" value="取消" onclick="exec()"/>
					</div>
					</form>
				</div>
			</div>
		</div>
		<s:set var="inspectLic" value="merchFieldSurvy.inspectLic.toString()" />
		<s:set var="inspectReport" value="merchFieldSurvy.inspectReport.toString()" />
		<s:set var="inspectKnow" value="merchFieldSurvy.inspectKnow.toString()" />
		<s:set var="inspectOnsite" value="merchFieldSurvy.inspectOnsite.toString()" />
		<s:set var="inspectRange" value="merchFieldSurvy.inspectRange.toString()" />
		<s:set var="inspectLoan" value="merchFieldSurvy.inspectLoan.toString()" />
		<s:set var="inspectBusi" value="merchFieldSurvy.inspectBusi.toString()" />
		<s:set var="inspectOpin" value="merchFieldSurvy.inspectOpin.toString()" />
		<s:set var="manageFieldNature" value="merchFieldSurvy.manageFieldNature.toString()" />
		<s:set var="manageDistrict" value="merchFieldSurvy.manageDistrict.toString()" />
		<s:set var="manageArea" value="merchFieldSurvy.manageArea.toString()" />
		<script type="text/javascript"> 
		$("#survyTime").datepicker({changeYear:true,changeMonth:true});
		
			var inspectLic='#inspectLic'+'<s:property value="inspectLic"/>';
			$(inspectLic).attr("checked","checked");
			var inspectReport='#inspectReport'+'<s:property value="inspectReport"/>';
			$(inspectReport).attr("checked","checked");
			var inspectKnow='#inspectKnow'+'<s:property value="inspectKnow"/>';
			$(inspectKnow).attr("checked","checked");
			var inspectOnsite='#inspectOnsite'+'<s:property value="inspectOnsite"/>';
			$(inspectOnsite).attr("checked","checked");
			var inspectRange='#inspectRange'+'<s:property value="inspectRange"/>';
			$(inspectRange).attr("checked","checked");
			var inspectLoan='#inspectLoan'+'<s:property value="inspectLoan"/>';
			$(inspectLoan).attr("checked","checked");
			var inspectBusi='#inspectBusi'+'<s:property value="inspectBusi"/>';
			$(inspectBusi).attr("checked","checked");
			var inspectOpin='#inspectOpin'+'<s:property value="inspectOpin"/>';
			$(inspectOpin).attr("checked","checked");
			var manageFieldNature='#manageFieldNature'+'<s:property value="manageFieldNature"/>';
			$(manageFieldNature).attr("checked","checked");
			var manageDistrict='#manageDistrict'+'<s:property value="manageDistrict"/>';
			$(manageDistrict).attr("checked","checked");
			var manageArea='#manageArea'+'<s:property value="manageArea"/>';
			$(manageArea).attr("checked","checked");
			
			$(function(){
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
			});
			
			$(function() {
				$("#fieldSurvyform").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
			});
			
			function sub(){
				if(save()){
					$("#fieldSurvyform").attr("action","${ctx}/backstage/merchmanager/merchManager!savefieldSurvey");
					$("#fieldSurvyform").submit();
				}
			}
			function save(){
				if(!$("#fieldSurvyform").validationEngine("validate")){
					return false;
				}

				var startHour=$("#startHour").val();
				var startMin=$("#startMin").val();
				var endHour=$("#endHour").val();
				var endMin=$("#endMin").val();
				if(startHour!=""){
					if(isNaN(startHour)){
						alert("营业时间请填写数字!");
						return false;
					}else{
						if(startHour<0||startHour>=24){
							alert("营业时间请填写数字!");
							return false;
						}
					}
				}
				
				if(startMin!=""){
					if(isNaN(startMin)){
						alert("营业时间请填写数字!");
						return false;
					}else{
						if(startMin<0||startMin>=60){
							alert("营业时间请填写数字!");
							return false;
						}
					}
				}
				if(endMin!=""){
					if(isNaN(endMin)){
						alert("营业时间请填写数字!");
						return false;
					}else{
						if(endMin<0||endMin>=60){
							alert("营业时间请填写数字!");
							return false;
						}
					}
				}
				if(endHour!=""){
					if(isNaN(endHour)){
						alert("营业时间请填写数字!");
						return false;
					}else{
						if(endHour<0||endHour>=24){
							alert("营业时间请填写数字!");
							return false;
						}
					}
				}
				
			/**
				if($("#inspectLicYES").attr("checked")==undefined&&$("#inspectLicNO").attr("checked")==undefined){
					alert("请选择是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件");
					return false;
				}
				if($("#inspectLicNO").attr("checked")=="checked"){
					if($("#inspectLicDesc").val()==""){
						alert("请填写检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件原因!");
						return false;
					}
				}
				
				if($("#inspectReportYES").attr("checked")==undefined&&$("#inspectReportNO").attr("checked")==undefined){
					alert("请选择是否了解商户有相对应的财务/销售报表");
					return false;
				}
				if($("#inspectReportNO").attr("checked")=="checked"){
					if($("#inspectReportDesc").val()==""){
						alert("请填写是否了解商户有相对应的财务/销售报表原因!");
						return false;
					}
				}
				
				if($("#inspectReportYES").attr("checked")==undefined&&$("#inspectReportNO").attr("checked")==undefined){
					alert("请选择是否对收银员进行业务知识和操作技能的资格审核");
					return false;
				}
				if($("#inspectReportNO").attr("checked")=="checked"){
					if($("#inspectReportDesc").val()==""){
						alert("请填写是否对收银员进行业务知识和操作技能的资格审核原因!");
						return false;
					}
				}
				
				if($("#inspectKnowYES").attr("checked")==undefined&&$("#inspectKnowNO").attr("checked")==undefined){
					alert("请选择是否对商户进行实地考察");
					return false;
				}
				if($("#inspectReportNO").attr("checked")=="checked"){
					if($("#inspectKnowDesc").val()==""){
						alert("请填写是否对商户进行实地考察原因!");
						return false;
					}
				}
				
				
				if($("#inspectOnsiteYES").attr("checked")==undefined&&$("#inspectOnsiteNO").attr("checked")==undefined){
					alert("请选择商户营业范围与营业执照登记相符");
					return false;
				}
				if($("#inspectOnsiteNO").attr("checked")=="checked"){
					if($("#inspectOnsiteDesc").val()==""){
						alert("请填写商户营业范围与营业执照登记相符原因!");
						return false;
					}
				}
				
				if($("#inspectRangeYES").attr("checked")==undefined&&$("#inspectRangeNO").attr("checked")==undefined){
					alert("请选择是否有足够的存贷来支持销售");
					return false;
				}
				if($("#inspectRangeDesc").attr("checked")=="checked"){
					if($("#inspectOnsiteDesc").val()==""){
						alert("请填写是否有足够的存贷来支持销售原因!");
						return false;
					}
				}
				
				if($("#inspectBusiGOOD").attr("checked")==undefined&&$("#inspectBusiORDINARY").attr("checked")==undefined&&$("#inspectBusiWORSE").attr("checked")==undefined&&$("#inspectBusiBAD").attr("checked")==undefined){
					alert("请选择商户的经营状况!");
					return false;
				}
				
				if($("#inspectOpinHIGHQUALITY").attr("checked")==undefined&&$("#inspectOpinLOWRISK").attr("checked")==undefined&&$("#inspectOpinGENERALRISK").attr("checked")==undefined&&$("#inspectOpinHIGHRISK").attr("checked")==undefined){
					alert("请选择商户的考察意见!");
					return false;
				}
				
				if($("#manageFieldNatureOWN").attr("checked")==undefined&&$("#manageFieldNatureRENT").attr("checked")==undefined){
					alert("请选择营业用地性质-经营地段!");
					return false;
				}
				
				if($("#manageDistrictBUSIN").attr("checked")==undefined&&$("#manageDistrictINDUSTRY").attr("checked")==undefined&&$("#manageDistrictHOUSE").attr("checked")==undefined){
					alert("请选择营业用地性质-经营地段");
					return false;
				}
				
				if($("#manageAreaDOWNTOWN").attr("checked")==undefined&&$("#manageAreaSUBURB").attr("checked")==undefined&&$("#manageAreaREMOTE").attr("checked")==undefined){
					alert("请选择营业用地性质-经营区域");
					return false;
				}
				*/
				return true;
			}
			function exec(){
				window.location.href = "${ctx}/backstage/merchmanager/merchManager!execute";		
			}
			
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>