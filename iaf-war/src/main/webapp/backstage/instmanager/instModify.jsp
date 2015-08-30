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
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构资料详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>机构资料详情</span></h3>
				<div class="search-bar">
				<s:form id="instform" action="instManager" namespace="/backstage/instmanager" enctype="multipart/form-data">
					<table width="90%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">机构基本信息</td>
						</tr>
						<tr>
							<td class="label">机构名称</td>
							<td class="data"><input  name="inst.instName" class="validate[required,minSize[2]]"  value="<s:property  value='inst.instName'/>"  maxLength="30" /><font color=red>*</font></td>
							<td class="label">融资性机构经营许可证</td>
							<td class="data"><input  name="inst.loanPermit"  value="<s:property  value='inst.loanPermit'/>"  maxLength="30" /> </td>
						</tr>
						<tr>
							<td class="label">注册时间</td>
							<td class="data">
							<input  id="instRegTime" readonly="readonly"  name="inst.regTime"  value="<s:date name="inst.regTime" format="yyyy-MM-dd"/>" /> </td>
							<td class="label">注册资金(万元)</td>
							<td class="data"><input  name="inst.regCapital"  value="<s:property  value='inst.regCapital'/>" /> </td>
						</tr>
						<tr>
							<td class="label">营业执照号</td>
							<td class="data"><input  name="inst.busiLicense"  value="<s:property  value='inst.busiLicense'/>"  /> </td>
							<td class="label">税务登记号</td>
							<td class="data"><input  name="inst.taxRegNo"  value="<s:property  value='inst.taxRegNo'/>"  /> </td>
						</tr>
						<tr>
							<td class="label">开业时间</td>
							<td class="data"><input id="instOpenTime" readonly="readonly" name="inst.openTime"  value="<s:date name="inst.openTime" format="yyyy-MM-dd"/>" /> </td>
							<td class="label">机构性质</td>
							<td class="data"><input name="inst.instNature"  value="<s:property value='inst.instNature'/>" /> </td>
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
							<td class="label">放贷卡(帐)号开户姓名</td>
							<td class="data"><input  name="instBusiInfo.loanName"  value="<s:property  value='instBusiInfo.loanName'/>" /> </td>
						</tr>
						<tr>
							<td class="label">放贷卡(帐)号开户行</td>
							<td class="data"><input  name="instBusiInfo.bank"  value="<s:property  value='instBusiInfo.bank'/>" /> </td>
							<td class="label">放贷卡(帐)号开户银行编号</td>
							<td class="data"><input  name="instBusiInfo.loanBankCode"  value="<s:property  value='instBusiInfo.loanBankCode'/>" /> </td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号</td>
							<td class="data"><input maxlength="19"  onkeypress="return onlyNum()" id="refundNo" name="instBusiInfo.repaymentNo" class="validate[custom[onlyNumberSp],maxSize[19]]" value="<s:property  value='instBusiInfo.repaymentNo'/>" /> </td>
							<td class="label">收款卡(帐)号开户姓名</td>
							<td class="data"><input  name="instBusiInfo.repaymentName"  value="<s:property  value='instBusiInfo.repaymentName'/>" /> </td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号开户行</td>
							<td class="data"><input  name="instBusiInfo.repaymentBank"  value="<s:property  value='instBusiInfo.repaymentBank'/>" /> </td>
							<td class="label">收款卡(帐)号开户银行编号</td>
							<td class="data"><input onkeypress="return onlyNum()" name="instBusiInfo.bankCode"  value="<s:property  value='%{instBusiInfo.bankCode}'/>" /> </td>
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
						<tr>
							<td class="data" colspan="4">机构LOGO信息</td>
						</tr>
						<tr>
							<td class="data"><input type="button" class="dark-btn" onclick="logoUpload()" value="上传LOGO资料"/></td>
							<td class="data" colspan="3">
								<s:if test="logoTFile.size>=0">
      							<iframe id="logoFrame" name="logoFrame" marginheight="0" marginwidth="0" src="/backstage/instmanager/instManager!logoInfo" width="100%" align="middle" scrolling="yes" frameborder="0" >
      							</iframe>
      							</s:if>
      							<s:else>
      							&nbsp;
      							</s:else>
							</td>
						</tr>
						<tr>
							<td class="data" colspan="4">机构其他资料</td>
						</tr>
						<tr>
							<td class="data"><input type="button" class="dark-btn" onclick="showUpload()" value="上传其他资料"/></td>
							<td class="data" colspan="3">
								<s:if test="tfileList.size>=0">
      							<iframe id="merchFrame" name="merchFrame" marginheight="0" marginwidth="0" src="/backstage/instmanager/instManager!otherInfo" width="100%" align="middle" scrolling="yes" frameborder="0" >
      							</iframe>
      							</s:if>
      							<s:else>
      							&nbsp;
      							</s:else>
							</td>
						</tr>
					</table>
					<div class="uop">
						<n:HcAuthButton cssClass="dark-btn" value="修改" onclick="return checkForm()" authCode="JGGL_JGGL_XG"/>&nbsp;&nbsp;
						<s:if test='inst.instStat.toString()=="USED"'>
						<n:HcAuthButton cssClass="dark-btn" authCode="JGGL_JGGL_JY" onclick="jinyong();" value="禁用"></n:HcAuthButton>&nbsp;&nbsp;
						</s:if>
						<s:else>
						<n:HcAuthButton cssClass="dark-btn" authCode="JGGL_JGGL_QY" onclick="keyong();" value="启用"></n:HcAuthButton>&nbsp;&nbsp;
						</s:else>
						<s:submit cssClass="dark-btn" value="取消" method="execute"/>
					</div>
					</s:form>

				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$("#instform").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
				$("#instRegTime,#instOpenTime").datepicker({changeYear:true,changeMonth:true});
			});
			
			function checkForm(){
				if(confirm("是否确认修改?")){
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
				
				document.instform.action="${ctx}/backstage/instmanager/instManager!updateInstInfo";	
		        document.instform.submit();
				return true;
				}
			}
			
			function onlyNum(){
				 var num = event.keyCode;
				  if (num >=48 && num<=57){
				   return true;
				  }else{
				   return false;
				  }
			}
			
			function showUpload(){
				parent.jDialog("上传其他资料","${ctx}/backstage/instmanager/instManager!otherUpload",800,500,true,function(){
					merchFrame.location = "${ctx}/backstage/instmanager/instManager!otherInfo";
				});
			}
			
			function logoUpload(){
				parent.jDialog("上传LOGO资料","${ctx}/backstage/instmanager/instManager!logoUpload",800,500,true,function(){
					logoFrame.location = "${ctx}/backstage/instmanager/instManager!logoInfo";
				});
			}
			
			function jinyong(){
				if(confirm("是否确定要禁用该机构?")){
					document.instform.action="${ctx}/backstage/instmanager/instManager!disableInst";	
			        document.instform.submit();
				}
			}
			
			function keyong(){
				if(confirm("是否确定要启用该机构?")){
					document.instform.action="${ctx}/backstage/instmanager/instManager!enableInst";	
			        document.instform.submit();
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>