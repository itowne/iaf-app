<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;新增机构</p>
			</div>
			<div id="content">
				<h3 class="title"><span>新增机构详细信息</span></h3>
				<div class="results report">
				<s:form id="instform" action="instManager" namespace="backstage/instmanager" method = "post" enctype="multipart/form-data">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">机构基本信息</td>
						</tr>
						<tr>
							<td class="label">机构基本名称</td>
							<td class="data"><input  name="inst.instName"  value="<s:property  value='inst.instName'/>" class="validate[required,minSize[2]]" /><font color=red>*</font></td>
							<td class="label">融资性机构经营许可证</td>
							<td class="data"><input  name="inst.loanPermit"  value="<s:property  value='inst.loanPermit'/>" /> </td>
						</tr>
						<tr>
							<td class="label">注册时间</td>
							<td class="data"><input id="instRegTime" readonly="readonly" name="inst.regTime"  value="<s:property  value='inst.regTime'/>" /> </td>
							<td class="label">注册资金(万元)</td>
							<td class="data"><input  name="inst.regCapital"  value="<s:property  value='inst.regCapital'/>" /> </td>
						</tr>
						<tr>
							<td class="label">营业执照号</td>
							<td class="data"><input  name="inst.busiLicense"  value="<s:property  value='inst.busiLicense'/>" /> </td>
							<td class="label">税务登记号</td>
							<td class="data"><input  name="inst.taxRegNo"  value="<s:property  value='inst.taxRegNo'/>" /> </td>
						</tr>
						<tr>
							<td class="label">开业时间</td>
							<td class="data"><input id="instOpenTime" readonly="readonly" name="inst.openTime"  value="<s:property  value='inst.openTime'/>" /> </td>
							<td class="label">机构性质</td>
							<td class="data"><input  name="inst.instNature"  value="<s:property  value='inst.instNature'/>" /> </td>
						</tr>
						<tr>
							<td class="label">联系人</td>
							<td class="data"><input  name="inst.contact"  value="<s:property  value='inst.contact'/>" /> </td>
							<td class="label">联系电话</td>
							<td class="data"><input  name="inst.contactPhone"  value="<s:property  value='inst.contactPhone'/>" /> </td>
						</tr>
						<tr>
							<td class="label">注册地址</td>
							<td class="data"><input  name="inst.regAddr"  value="<s:property  value='inst.regAddr'/>" /> </td>
							<td class="label">机构英文名称</td>
							<td class="data"><input  name="inst.englishName"  value="<s:property  value='inst.englishName'/>" /></td>
						</tr>
						<tr>
							<td class="label">经营范围</td>
							<td class="data"><input  name="inst.busiRegion"  value="<s:property  value='inst.busiRegion'/>" /> </td>
							<td class="label">公司口号</td>
							<td class="data"><input  name="inst.catchword"  value="<s:property  value='inst.catchword'/>" /></td>
						</tr>
						<tr>
							<td class="label">员工人数</td>
							<td class="data"><input onkeypress="return onlyNum()" name="inst.peopleCount"  value="<s:property  value='inst.peopleCount'/>" /></td>
							<td class="label">公司网址</td>
							<td class="data"><input  name="inst.officialWebsite"  value="<s:property  value='inst.officialWebsite'/>" /> </td>
						</tr>
						<tr>
							<td class="label">官方电话</td>
							<td class="data"><input  name="inst.shortPhone"  value="<s:property  value='inst.shortPhone'/>" /> </td>
							<td class="label">总资产(万元)</td>
							<td class="data"><input  name="inst.totalCapital"  value="<s:property  value='inst.totalCapital'/>" /> </td>
						</tr>
						<tr>
							<td class="label">汇卡商户号</td>
							<td class="data"><input  name="inst.instId"  value="<s:property  value='inst.instId'/>" /> </td>
							<td class="label">在线联系QQ号</td>
							<td class="data"><input onkeypress="return onlyNum()" name="inst.qqUid"  value="<s:property  value='inst.qqUid'/>" /> </td>
						</tr>
						<tr>
							<td class="label">机构简介</td>
							<td class="data" colspan="3"><s:textarea name="inst.introduce" rows="3" cols="71" /> </td>
						</tr>
						<tr>
							<td class="data" colspan="4">机构业务资料信息</td>
						</tr>
						<tr>
							<td class="label">放贷卡(帐)号</td>
							<td class="data"><input maxlength="19"  onkeypress="return onlyNum()" id="creditNo" name="instBusiInfo.loanAccountNo" class="validate[custom[onlyNumberSp],maxSize[19]]" value="<s:property  value='instBusiInfo.loanAccountNo'/>" /> </td>
							<td class="label">放贷卡(帐)号开户银行编号</td>
							<td class="data"><input  name="instBusiInfo.loanBankCode"  value="<s:property  value='instBusiInfo.loanBankCode'/>" /> </td>
						</tr>
						<tr>
							<td class="label">放贷卡(帐)号开户行</td>
							<td class="data"><input  name="instBusiInfo.bank"  value="<s:property  value='instBusiInfo.bank'/>" /> </td>
							<td class="label">放贷卡(帐)号开户姓名</td>
							<td class="data"><input  name="instBusiInfo.loanName"  value="<s:property  value='instBusiInfo.loanName'/>" /> </td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号</td>
							<td class="data"><input maxlength="19"  onkeypress="return onlyNum()" id="refundNo" name="instBusiInfo.repaymentNo" class="validate[custom[onlyNumberSp],maxSize[19]]"  value="<s:property  value='instBusiInfo.repaymentNo'/>" /> </td>
							<td class="label">收款卡(帐)号开户行</td>
							<td class="data"><input  name="instBusiInfo.repaymentBank"  value="<s:property  value='instBusiInfo.repaymentBank'/>" /> </td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号开户姓名</td>
							<td class="data"><input  name="instBusiInfo.repaymentName"  value="<s:property  value='instBusiInfo.repaymentName'/>" /> </td>
							<td class="label">收款卡(帐)号开户银行编号</td>
							<td class="data"><input onkeypress="return onlyNum()" name="instBusiInfo.bankCode"  value="<s:property  value='instBusiInfo.bankCode'/>" /> </td>
						</tr>
						<tr>
							<td class="label">业务受理地区</td>
							<td class="data"><input  name="instBusiInfo.acceptRegion"  value="<s:property  value='instBusiInfo.acceptRegion'/>" /> </td>
							<td class="label">&nbsp;</td>
							<td class="data">&nbsp;</td>
						</tr>
						<tr>
							<td class="data" colspan="4">机构法人资料信息</td>
						</tr>
						<tr>
							<td class="label">姓名</td>
							<td class="data"><input  name="instLegalPers.legalPersName"  value="<s:property  value='instLegalPers.legalPersName'/>" /> </td>
							<td class="label">身份证号码</td>
							<td class="data"><input  name="instLegalPers.cerdNo"  value="<s:property  value='instLegalPers.cerdNo'/>" /> </td>
						</tr>
						<tr>
							<td class="label">性别</td>
							<td class="data">
							<s:select name="instLegalPers.sex" list="@newland.iaf.base.model.dict.SexType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.sex"></s:select>
							 </td>
							<td class="label">年龄</td>
							<td class="data"><input  name="instLegalPers.age"  value="<s:property  value='instLegalPers.age'/>" /> </td>
						</tr>
						<tr>
							<td class="label">学历</td>
							<td class="data">
							<s:select name="instLegalPers.education" list="@newland.iaf.base.model.dict.EducationType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.education"></s:select>
							 </td>
							<td class="label">婚姻状况</td>
							<td class="data">
							<s:select name="instLegalPers.maritalStatus" list="@newland.iaf.base.model.dict.MerryType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.maritalStatus"></s:select>
							 </td>
						</tr>
						<tr>
							<td class="label">家庭住址</td>
							<td class="data"><input  name="instLegalPers.familyAddr"  value="<s:property  value='instLegalPers.familyAddr'/>" /> </td>
							<td class="label">&nbsp;</td>
							<td class="data">&nbsp;</td>
						</tr>
					</table>
						<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data">&nbsp;&nbsp;机构图片LOGO <s:file id="fileforload" name="upload" label="File" /></td>

						</tr>
					 	</table>
					<div class="uop">
						<s:hidden name="filetype" value="instLogo"/>
						<n:HcAuthButton cssClass="dark-btn" value="确定" authCode="JGGL_JGGL_XZ" onclick="return checkForm()"/>
						<input type="button" class="dark-btn" value="取消" onclick="cancel()"/>
					</div>
					</s:form>
					
					<div class="results report">
					
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function cancel(){
				window.location.href = "${ctx}/backstage/instmanager/instManager";
			}
			$(function() {
				$("#instform").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
				$("#instRegTime,#instOpenTime").datepicker({changeYear:true,changeMonth:true});
			});
			function checkForm(){
				if (!$("#instform").validationEngine("validate")) {
					return false;
				}
				var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
				var creditNo=$("#creditNo").val();
				if(creditNo!=""){
					if(txt.test(creditNo)){
						alert("放贷卡号请填写数字!");
						return false;
					}
				}
				
				var refundNo=$("#refundNo").val();
				if(refundNo!=""){
					if(txt.test(refundNo)){
						alert("收款卡号请填写数字!");
						return false;
					}
				}
				
				//if(document.getElementById("fileforload").value=="") {
			    	//alert("请先选择LOGO图片！");
			    	//return false;
			   // }
				document.instform.action="${ctx}/backstage/instmanager/instManager!saveInstInfo";	
		        document.instform.submit();
			}
			
			function onlyNum(){
				 var num = event.keyCode;
				  if (num >=48 && num<=57){
				   return true;
				  }else{
				   return false;
				  }
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>