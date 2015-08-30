<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />

<html>
<head>
	<n:ui includes="form"/>
</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;修改商户</p>
			</div>
			<div id="content">
				<h3 class="title"><span>修改商户详细信息</span></h3>
				<div class="results report">
				<form id="merchform" name="merchform" action="#" method="post" enctype="multipart/form-data">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="data" colspan="4">商户基本信息</td>
						</tr>
						<tr>
							<td class="label">商户名称：</td>
							<td class="data"><input  name="merch.merchName" class="validate[required]" value="<s:property  value='merch.merchName'/>"  maxLength="30"/><font color=red>*</font></td>
							<td class="label">商户别称：</td>
							<td class="data"><input  name="merch.alias"  value="<s:property  value='merch.alias'/>"  maxLength="30"/><font color=red>用于首页显示</font></td>
						</tr>
						<tr>
							<td class="label">注册时间：</td>
							<td class="data"><input  id="merchRegTime" readonly="readonly" name="merch.regTime" value="<s:date name="merch.regTime" format="yyyy-MM-dd"/>"/></td>
							<td class="label">注册编号：</td>
							<td class="data"><input  name="merch.regNo"  value="<s:property  value='merch.regNo'/>"  maxLength="30"/></td>
						</tr>
						<tr>
							<td class="label">注册地址：</td>
							<td class="data"><input  name="merch.regAddr"  value="<s:property  value='merch.regAddr'/>" /></td>
							<td class="label">注册资本：</td>
							<td class="data"><input  name="merch.regCap" value="<s:property  value='merch.regCap'/>" /></td>
						</tr>
						<tr>
							<td class="label">营业执照号：</td>
							<td class="data"><input  name="merch.businlic"  value="<s:property  value='merch.businlic'/>" /></td>
							<td class="label">税务登记号：</td>
							<td class="data"><input  name="merch.taxReg"  value="<s:property  value='merch.taxReg'/>" /></td>
						</tr>
						<tr>
							<td class="label">开业时间：</td>
							<td class="data"><input id="merchOpenTime" readonly="readonly" name="merch.openTime" value="<s:date name="merch.openTime" format="yyyy-MM-dd"/>" /></td>
							<td class="label">公司规模：</td>
							<td class="data"><input  name="merch.companySize"  value="<s:property  value='merch.companySize'/>" /></td>
						</tr>
						<tr>
							<td class="label">联系人：</td>
							<td class="data"><input  name="merch.contract"  class="validate[required]" value="<s:property  value='merch.contract'/>" /><font color=red>*</font></td>
							<td class="label">联系电话：</td>
							<td class="data"><input  name="merch.contractTel" class="validate[required,custom[phone]]" value="<s:property  value='merch.contractTel'/>" /><font color=red>*</font></td>
						</tr>
						<tr>
							<td class="label">机具安装地址：</td>
							<td class="data"><input  name="merch.posAddr" class="validate[required]" value="<s:property  value='merch.posAddr'/>" /><font color=red>*</font></td>
							<td class="label">联系邮箱地址：</td>
							<td class="data"><input  name="merch.email"  value="<s:property  value='merch.email'/>" /></td>
						</tr>
						<tr>
							<td class="label">所属行业：</td>
							<td class="data">
							 <s:select list="mccMap" name="mccCode" id="mccMap" listKey="key" listValue="value" headerKey="" headerValue="请选择" style="width:100px;" class="validate[required]"></s:select>
							<select name="code" id="code" style="width:100px;"></select> <font color=red>*</font>
							<!--  <input  name="merch.industry" class="validate[required]" value="<s:property  value='merch.industry'/>" /><font color=red>*</font></td>-->
							</td>
							<td class="label">所属省市：</td>
							<td class="data">
							<s:select cssClass="validate[required]" list="provMap" name="provinceCode" id="provMap"  listKey="key" listValue="value" headerKey="" headerValue="请选择省份" style="width:130px;"></s:select>
							<select class="validate[required]" name="cityCode" id="cityCode" style="width:120px;"></select>
							<!--  <input  name="merch.province" class="validate[required]" value="<s:property  value='merch.province'/>" />-->
							</td>
						</tr>
						<tr>
							<td class="label">商户类型：</td>
							<td class="data">
							<s:property value="%{merch.merchType.desc}"/>
							</td>
							<td class="label">汇卡商户号：</td>
							<td class="data">
							<s:if test='merch.merchType.toString()=="GOLD_SHOPKEEPER"'>
							<s:property value="%{merch.merchNo}"/>
							</s:if>
							<s:else>
							--
							</s:else>
							</td>
						</tr>
						<!-- 
						<tr>
							<td class="label">地址是否核实：</td>
							<td class="data"><input  name="merch.auditAddr" class="validate[required]" value="<s:property  value='merch.auditAddr'/>" /><font color=red>*</font></td>
							<td class="label">营业执照号是否核实：</td>
							<td class="data"><input  name="merch.audirBusinlic" class="validate[required]" value="<s:property  value='merch.audirBusinlic'/>" /><font color=red>*</font></td>
						</tr>
						 -->
						<tr>
							<td class="label">在线联系QQ号</td>
							<td class="data" colspan="3"><input  onkeypress="return onlyNum()" name="merch.qqUid" class="validate[required]" value="<s:property  value='merch.qqUid'/>" /></td>
						</tr>
						<tr>
							<td class="data" colspan="4">商户业务资料信息</td>
						</tr>
						<s:if test='merch.merchType.toString()=="GOLD_SHOPKEEPER"'>
						<tr>
							<td class="label">还款/收款(帐)号开户行：</td>
							<td class="data"><input  name="merchBusiInfo.bank" class="validate[required]" value="<s:property  value='merchBusiInfo.bank'/>" disabled="disabled"/></td>
							<td class="label">还款/收款(帐)号开户银行编号：</td>
							<td class="data"><input  name="merch.bankCode" class="validate[required]" value="<s:property  value='merch.bankCode'/>" disabled="disabled"/></td>
						</tr>
						<tr>
							<td class="label">还款/收款(帐)号：</td>
							<td class="data"><input maxlength="19"  onkeypress="return onlyNum()" name="merchBusiInfo.accountNo"  value="<s:property  value='merchBusiInfo.accountNo'/>" disabled="disabled"/></td>
							<td class="label">还款/收款(帐)号开户名：</td>
							<td class="data"><input  name="merchBusiInfo.accountName" value="<s:property  value='merchBusiInfo.accountName'/>" disabled="disabled"/></td>
						</tr>
						</s:if>
						<s:else>
						<tr>
							<td class="label">还款/收款(帐)号开户行：</td>
							<td class="data"><input  name="merchBusiInfo.bank"  value="<s:property  value='merchBusiInfo.bank'/>" /></td>
							<td class="label">还款/收款(帐)号开户银行编号：</td>
							<td class="data"><input  name="merch.bankCode"  value="<s:property  value='merch.bankCode'/>" /></td>
						</tr>
						<tr>
							<td class="label">还款/收款(帐)号：</td>
							<td class="data"><input maxlength="19" onkeypress="return onlyNum()" id="cardId" name="merchBusiInfo.accountNo" class="validate[custom[onlyNumberSp],maxSize[19]]" value="<s:property  value='merchBusiInfo.accountNo'/>" /></td>
							<td class="label">还款/收款(帐)号开户名：</td>
							<td class="data"><input  name="merchBusiInfo.accountName" value="<s:property  value='merchBusiInfo.accountName'/>" /></td>
						</tr>
						</s:else>
						<tr>
							<td class="label">商户性质：</td>
							<td class="data"  colspan="3"><input  name="merchBusiInfo.merchNatrue"  value="<s:property  value='merchBusiInfo.merchNatrue'/>" /></td>
						</tr>
						<tr>
							<td class="data" colspan="4">商户负责人资料信息</td>
						</tr>
						<tr>
							<td class="label">负责人姓名：</td>
							<td class="data"><input  name="merchLegalPers.legalPersName"  value="<s:property  value='merchLegalPers.legalPersName'/>" /></td>
							<td class="label">身份证号码：</td>
							<td class="data"><input  name="merchLegalPers.cerdNo"  value="<s:property  value='merchLegalPers.cerdNo'/>" /></td>
						</tr>
						<tr>
							<td class="label">性别：</td>
							<td class="data">
							<s:select name="merchLegalPers.sex" list="@newland.iaf.base.model.dict.SexType@values()"  listKey="toString()" listValue="getDesc()" headerKey="" headerValue="请选择" value="merchLegalPers.sex"></s:select>
							</td>
							<td class="label">年龄：</td>
							<td class="data"><input  name="merchLegalPers.age"  value="<s:property  value='merchLegalPers.age'/>" /></td>
						</tr>
						<tr>
							<td class="label">学历：</td>
							<td class="data">
							<s:select name="merchLegalPers.eduBackground" list="@newland.iaf.base.model.dict.EducationType@values()" listKey="toString()" listValue="getDesc()" headerKey="" headerValue="请选择" value="merchLegalPers.eduBackground"></s:select>
							</td>
							<td class="label">婚姻状况：</td>
							<td class="data">
							<s:select name="merchLegalPers.maritalStatus" list="@newland.iaf.base.model.dict.MerryType@values()" listKey="toString()" headerKey="" headerValue="请选择" listValue="getDesc()" value="merchLegalPers.maritalStatus"></s:select>
							</td>
						</tr>
						<tr>
							<td class="label">办公电话：</td>
							<td class="data"><input  name="merchLegalPers.officePhone"  value="<s:property  value='merchLegalPers.officePhone'/>" /></td>
							<td class="label">手机号码：</td>
							<td class="data"><input  name="merchLegalPers.mobiPhone"  value="<s:property  value='merchLegalPers.mobiPhone'/>" /></td>
						</tr>
						<tr>
							<td class="label">家庭住址：</td>
							<td class="data"><input  name="merchLegalPers.faAddr"  value="<s:property  value='merchLegalPers.faAddr'/>" /></td>
							<td class="label"></td>
							<td class="data"></td>
						</tr>
					<tr>
							<td class="data" colspan="4">门面图片详情</td>
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
					<div class="uop">
						<n:HcAuthButton id="confirm" cssClass="dark-btn" value="确定" authCode="SHGL_SHZLGL_XG"/>
						<input type="button" class="dark-btn" value="取消" onclick="cancel()"/>
					</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$("#merchform").validationEngine("attach",{   
				promptPosition:"centerRight"
			});
			$("#merchRegTime,#merchOpenTime").datepicker({changeYear:true,changeMonth:true});
		});
		
		$("#mccMap").change(function(){
			if($("#mccMap").val()==""){
				$("#code").empty();                  
		    	$("#code").html('<option value="">请选择</option>'); 
			}else{
				$.getJSON("/../mcc",{code:$(this).val()},
				function(myJSON){
					var myOptions="";                  
					for(var i=0;i<myJSON.mccList.length;i++){    
						myOptions += '<option value="' + myJSON.mccList[i].id + '">' +myJSON.mccList[i].name + '</option>';                  
		             }   
		             $("#code").empty();                  
		             $("#code").html(myOptions);              
		       	}); 
			}         
			});
		$("#mccMap").change();
		
		$("#confirm").click(function(){
			if($("#upload").val()==""){
				$("#merchLogo").attr("disabled","disabled");
			}
			if($("#debitupload").val()==""){
				$("#debitLogo").attr("disabled","disabled");
			}
			if (check()) {
				$("#merchform").attr("action", "${ctx}/backstage/merchmanager/merchManager!updateMerchInfo");
				$("#merchform").submit();
			}
		});

		function check(){
			if(!$("#merchform").validationEngine("validate")){
				return false;
			}
			var cardNo=$("#cardId").val();
			if(cardNo!=""){
				var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
				if(txt.test(cardNo)){
					alert("还款/收款帐号请填写数字!");
					return false;
				}
			}
			return true;
		}
		
		$("#provMap").change(function(){  
			if($("#provMap").val()==""){
				$("#cityCode").empty();                  
	        	$("#cityCode").html('<option value="">请选择市区</option>'); 
			}else{
				$.getJSON("/../province",{provinceCode:$(this).val()},
						function(myJSON){
							var myOptions="<option value=''>请选择市区</option>";                  
							for(var i=0;i<myJSON.provinceList.length;i++){    
								myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
				             }   
				             $("#cityCode").empty();                  
				             $("#cityCode").html(myOptions);              
				       	});      
			}
	   	});
		$("#provMap").change();
		
		function cancel(){
			window.location.href = "${ctx}/backstage/merchmanager/merchManager";
		}
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
			});

			var secondcode ='<s:property value="%{secondCode}"/>';
			var firstcode='<s:property value="%{firstCode}"/>';
			$("#mccMap option[value='"+firstcode+"']").attr("selected","selected"); 
				$.getJSON("/../mcc",{code:firstcode},
				function(myJSON){
					var myOptions="";                  
					for(var i=0;i<myJSON.mccList.length;i++){  
						myOptions += '<option value="' + myJSON.mccList[i].id + '">' +myJSON.mccList[i].name + '</option>';  
		             }   
		             $("#code").empty();                  
		             $("#code").append(myOptions); 
		       	});   
			setTimeout(function (){
				$("#code option[value='"+secondcode+"']").attr("selected","selected");
			}, 300);
			
			var region='${merch.cityCode}';
			var province='${provinceId}';
			$("#provMap option[value="+province+"]").attr("selected","selected");
			$.getJSON("/../province",{provinceCode:province},
					function(myJSON){
						var myOptions="<option value=''>请选择市区</option>";                  
						for(var i=0;i<myJSON.provinceList.length;i++){    
							myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
			             }   
			             $("#cityCode").empty();                  
			             $("#cityCode").html(myOptions);              
			       	});    
			setTimeout(function (){
				$("#cityCode option[value='"+region+"']").attr("selected","selected");
			}, 300);
			
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