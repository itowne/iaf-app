<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
		<n:ui includes="form"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<base target="_self"/>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;经营数据报告管理&nbsp;&gt;&gt;&nbsp;修改我的基本资料</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>我的基本资料</span></h3>
				<div align="center" style="font-size: 16px;">
					<font color="red">您只能在本系统修改蓝色字体信息，如您其它信息有变动，请致电客服400 628 6928申请修改。</font>
				</div>
				
				<div class="report-tab-area">
					<div class="tab-content">
						<div id="basic-info" style="padding:0 30px;">
						<s:form target="" name="form1" id="form1" action="merchInfoModify" namespace="/merch" method="POST" enctype="multipart/form-data">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
								<tr>
									<td class="label">商户名称</td>
									<td class="data"><s:property value="%{merch.merchName}"/></td>
									<td class="label">商户行业类别</td>
									<td class="data"><s:property value="%{merch.merchType.desc}"/></td>
								</tr>
								<tr>
									<td class="label">注册地址</td>
									<td class="data">
									<s:if test="merch.regAddr==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.regAddr}"/>
									</s:else>
									</td>
									<td class="label">机具装机地址</td>
									<td class="data">
									<s:if test="merch.posAddr==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.posAddr}"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">注册时间</td>
									<td class="data">
									<s:if test="merch.regTime==null">
									--
									</s:if>
									<s:else>
									<fmt:formatDate value="${merch.regTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</s:else>
									</td>
									<td class="label">注册资本</td>
									<td class="data">
									<s:if test="merch.regCap==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.regCap}"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">营业执照号</td>
									<td class="data">
									<s:if test="merch.businlic==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.businlic}"/>
									</s:else>
									</td>
									<td class="label">税务登记号</td>
									<td class="data">
									<s:if test="merch.taxReg==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.taxReg}"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">开业时间</td>
									<td class="data">
									<s:if test="merch.openTime==null">
									--
									</s:if>
									<s:else>
									<fmt:formatDate value="${merch.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</s:else>
									</td>
									<td class="label">公司规模</td>
									<td class="data">
									<s:if test="merch.companySize==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.companySize}"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">联系人</td>
									<td class="data">
									<s:if test="merch.contract==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.contract}"/>
									</s:else>
									</td>
									<td class="label">联系电话</td>
									<td class="data">
									<s:if test="merch.contractTel==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merch.contractTel}"/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label" style="color:blue;">QQ号</td>
									<td class="data"><input type="text" name="merch.qqUid" class="validate[required]" value="<s:property value="merch.qqUid"/>"></td>
									<td class="label">&nbsp;</td>
									<td class="data">&nbsp;</td>
								</tr>
								<tr>
									<td class="label">法定代表人情况</td>
									<td class="data embed" colspan="3">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="label">姓名</td>
												<td class="data">
												<s:if test="merchLegalPers.legalPersName==null">
												--
												</s:if>
												<s:else>
												<s:property value="%{merchLegalPers.legalPersName}"/>
												</s:else>
												</td>
												<td class="label" style="color:blue;">性别</td>
												<td class="data" style="color:blue;">
												<select name="merchLegalPers.sex" id="sex">
												<option value="">请选择</option>
												<option value="MAN">男</option>
												<option value="woman">女</option>
												</select>
												<td class="label" style="color:blue;">年龄</td>
												<td class="data" style="color:blue;"><input type="text" name="merchLegalPers.age" id="age" class="validate[required,custom[onlyNumberSp],min[0],max[99]]" value="<s:property value="%{merchLegalPers.age}"/>"></td>
											</tr>
											<tr>
												<td class="label">身份证号码</td>
												<td class="data">
												<s:if test="merchLegalPers.cerdNo==null">
												--
												</s:if>
												<s:else>
												<s:property value="%{merchLegalPers.cerdNo}"/>
												</s:else>
												</td>
												<td class="label" style="color:blue;">婚姻状况</td>
												<td class="data" style="color:blue;">
												<s:select name="merchLegalPers.maritalStatus" list="@newland.iaf.base.model.dict.MerryType@values()" listKey="toString()" headerKey="" headerValue="请选择" listValue="getDesc()" value="merchLegalPers.maritalStatus"></s:select>
												</td>
												<td class="label" style="color:blue;">学历</td>
												<td class="data" style="color:blue;">
												<select id="eduBackground" name="merchLegalPers.eduBackground">
													<option value="">请选择</option>
													<option value="PRIMARY_SCHOOLS">小学</option>
													<option value="JUNIOR_MIDDLE">初中</option>
													<option value="SENIOR_MIDDLE">高中</option>
													<option value="SECONDARY_VOCATIONAL">中专</option>
													<option value="COLLEGE_DEGREE">大专</option>
													<option value="BACHELOR">本科</option>
													<option value="MASTER">硕士</option>
												</select></td>
											</tr>
											<tr>
												<td class="label" style="color:blue;">办公电话</td>
												<td class="data" style="color:blue;"><input type="text" name="merchLegalPers.officePhone" class="validate[required,custom[phone]]" id="officePhone" value="<s:property value="%{merchLegalPers.officePhone}"/>"></td>
												<td class="label" style="color:blue;">手机</td>
												<td class="data" style="color:blue;"><input type="text" name="merchLegalPers.mobiPhone" class="validate[required,custom[phone]]" id="mobiPhone" value="<s:property value="%{merchLegalPers.mobiPhone}"/>"></td>
												<td class="label" style="color:blue;">家庭住址</td>
												<td class="data" style="color:blue;"><input type="text" name="merchLegalPers.faAddr" class="validate[required]" id="faAddr" value="<s:property value="%{merchLegalPers.faAddr}"/>"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="label">结算银行</td>
									<td class="data">
									<s:if test="merchBusiInfo.bank==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merchBusiInfo.bank}"/>
									</s:else>
									</td>
									<td class="label" >银行编号</td>
									<td class="data"><s:property value="%{merchBusiInfo.bankCode}"/></td>
								</tr>
								<tr>
									<td class="label" style="width:120">贷款资金划入账号</td>
									<td class="data" >
									<s:if test="merchBusiInfo.accountNo==null">
									--
									</s:if>
									<s:else>
									<s:property value="%{merchBusiInfo.accountNo}"/>
									</s:else>
									</td>
									<td class="label" >贷款账户名</td>
									<td class="data"><s:property value="merchBusiInfo.accountName"/></td>
								</tr>
								<tr>
									<td class="label">商户性质</td>
									<td class="data" colspan="3">
									<s:if test="merchBusiInfo.merchNatrue">
									--
									</s:if>
									<s:else>
									<s:property value="%{merchBusiInfo.merchNatrue}"/>
									</s:else>
									</td>
								</tr>
							<tr>
									<td class="label">门面大图显示效果</td>
									<td class="data" colspan="3" style="text-align:center;">
									<s:if test='debitTFile==null||debitTFile==""'>
									<img src="${ctx}/resources/images/B-JZG.png" width="150" height="70"/>&nbsp;&nbsp;<br/>
									</s:if>
									<s:else>
									<img src="/DrawImage.do?id=${debitTFile.ifile}&type=exp" width="150" height="70"/>&nbsp;&nbsp;<br/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">门面小图显示效果</td>
									<td class="data" colspan="3" style="text-align:center;">
									<s:if test='tfile==null||tfile==""'>
									<img src="${ctx}/resources/images/S-JZG.png" width="65" height="60"/>&nbsp;&nbsp;<br/>
									</s:if>
									<s:else>
									<img src="/DrawImage.do?id=${tfile.ifile}&type=exp" width="65" height="60"/>&nbsp;&nbsp;<br/>
									</s:else>
									</td>
								</tr>
								<tr>
									<td class="label">门面大图片</td>
									<td class="data" colspan="3" style="text-align:left;"><s:file id="debitupload" name="upload" label="File"/>&nbsp;&nbsp;&nbsp;(建议上传150X70的图片,此图片将用于平台"我要放贷"频道上显示)
									<input type="hidden" id="debitLogo" name="filetype" value="debitLogo"/>
									</td>
								</tr>
								<tr>
									<td class="label">门面小图片</td>
									<td class="data" colspan="3" style="text-align:left;"><s:file id="upload" name="upload" label="File"/>&nbsp;&nbsp;&nbsp;(建议上传65X65的图片,此图片将用于平台"首页"频道上显示)
									<input type="hidden" id="merchLogo" name="filetype" value="merchLogo"/>
									</td>
								</tr>
							</table>
							<div align="center">
						<!-- 	<s:submit value="修改" cssClass="dark-btn" method="modifyMerchInfo" onclick="return check();"/>-->
							<input type="button" class="dark-btn" id="modify" value="修改"/> 
							<input type="button" id="cancel" class="dark-btn" value="取消"/>
							</div>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var maritalStatus='<s:property value="%{merchLegalPers.maritalStatus}"/>';
		$("#maritalStatus option[value='"+maritalStatus+"']").attr("selected","selected");
		var eduBackground='<s:property value="%{merchLegalPers.eduBackground}"/>';
		$("#eduBackground option[value='"+eduBackground+"']").attr("selected","selected");
		var sex ='<s:property value="%{merchLegalPers.sex}"/>';
		$("#sex option[value='"+sex+"']").attr("selected","selected");
			$('#cancel').click(function(){
				parent.jDialogClose($("body").attr("id"));
			});
			
			$(function() {
				$("#form1").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
			});
			
			$('#modify').click(function(){
				if(confirm("确认提交请求?")){
					if (check()) {
						document.form1.action="${ctx}/merch/merchInfoModify!modifyMerchInfo";
						document.form1.submit();
					}
				}
				
			});
			
			function check(){
				if(!$("#form1").validationEngine("validate")){
					return false;
				}
				if($("#age").val()==0){
					alert("年龄请填写大于0的数字!");
					return false;
				}
				if($("#upload").val()==""){
					$("#merchLogo").attr("disabled","disabled");
				}
				if($("#debitupload").val()==""){
					$("#debitLogo").attr("disabled","disabled");
				}
				return true;
			}
		</script>
	</body>
</html>