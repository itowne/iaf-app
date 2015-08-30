<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;资料管理</p>
  </div>
  <div id="content">
    <h3 class="title"><span>机构基本资料</span></h3>
    <div class="search-bar">
    <div align="right"><font style="color:red">如果想修改资料,请拨打我们的客服热线400-628-6928</font></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
      	<tr>
							<td class="label">机构名称</td>
							<td class="data"><s:property  value='inst.instName'/></td>
							<td class="label">融资性机构经营许可证</td>
							<td class="data"><s:property  value='inst.loanPermit'/></td>
						</tr>
						<tr>
							<td class="label">注册时间</td>
							<td class="data">
							<s:date name="inst.regTime" format="yyyy-MM-dd"/></td>
							<td class="label">注册资金(万元)</td>
							<td class="data"><s:property  value='inst.regCapital'/></td>
						</tr>
						<tr>
							<td class="label">营业执照号</td>
							<td class="data"><s:property  value='inst.busiLicense'/></td>
							<td class="label">税务登记号</td>
							<td class="data"><s:property  value='inst.taxRegNo'/></td>
						</tr>
						<tr>
							<td class="label">开业时间</td>
							<td class="data"><s:date name="inst.openTime" format="yyyy-MM-dd"/></td>
							<td class="label">机构性质</td>
							<td class="data"><s:property value='inst.instNature'/></td>
						</tr>
						<tr>
							<td class="label">联系人</td>
							<td class="data"><s:property  value='inst.contact'/></td>
							<td class="label">联系电话</td>
							<td class="data"><s:property  value='inst.contactPhone'/></td>
						</tr>
						<tr>
							<td class="label">注册地址</td>
							<td class="data"><s:property  value='inst.regAddr'/></td>
							<td class="label">机构英文名称</td>
							<td class="data"><s:property  value='inst.englishName'/></td>
						</tr>
						<tr>
							<td class="label">经营范围</td>
							<td class="data"><s:property  value='inst.busiRegion'/></td>
							<td class="label">公司口号</td>
							<td class="data"><s:property  value='inst.catchword'/></td>
						</tr>
						<tr>
							<td class="label">员工人数</td>
							<td class="data"><s:property  value='inst.peopleCount'/></td>
							<td class="label">公司网址</td>
							<td class="data"><s:property  value='inst.officialWebsite'/></td>
						</tr>
						<tr>
							<td class="label">官方电话</td>
							<td class="data"><s:property  value='inst.shortPhone'/></td>
							<td class="label">总资产(万元)</td>
							<td class="data"><s:property  value='inst.totalCapital'/></td>
						</tr>
						<tr>
							<td class="label">汇卡商户号</td>
							<td class="data"><s:property  value='inst.instId'/></td>
							<td class="label">在线联系QQ号</td>
							<td class="data"><s:property  value='inst.qqUid'/></td>
						</tr>
      </table>
    </div>
    <h3 class="title"><span>业务资料</span></h3>
    <div class="search-bar">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
      	<tr>
							<td class="label">放贷卡(帐)号</td>
							<td class="data"><s:property  value='instBusiInfo.loanAccountNo'/></td>
							<td class="label">放贷卡(帐)号开户银行编号</td>
							<td class="data"><s:property  value='instBusiInfo.loanBankCode'/></td>
						</tr>
						<tr>
							<td class="label">放贷卡(帐)号开户行</td>
							<td class="data"><s:property  value='instBusiInfo.bank'/></td>
							<td class="label">放贷卡(帐)号开户姓名</td>
							<td class="data"><s:property  value='instBusiInfo.loanName'/></td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号</td>
							<td class="data"><s:property  value='instBusiInfo.repaymentNo'/></td>
							<td class="label">收款卡(帐)号开户行</td>
							<td class="data"><s:property  value='instBusiInfo.repaymentBank'/></td>
						</tr>
						<tr>
							<td class="label">收款卡(帐)号开户姓名</td>
							<td class="data"><s:property  value='instBusiInfo.repaymentName'/></td>
							<td class="label">收款卡(帐)号开户银行编号</td>
							<td class="data"><s:property  value='%{instBusiInfo.bankCode}'/></td>
						</tr>
						<tr>
							<td class="label">业务受理地区</td>
							<td class="data"><s:property  value='instBusiInfo.acceptRegion'/></td>
							<td class="label">&nbsp;</td>
							<td class="data">&nbsp;</td>
						</tr>
      </table>
    </div>
    <h3 class="title"><span>法人代表资料</span></h3>
    <div class="search-bar">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
      <tr>
							<td class="label">姓名</td>
							<td class="data"><s:property  value='instLegalPers.legalPersName'/></td>
							<td class="label">身份证号码</td>
							<td class="data"><s:property  value='instLegalPers.cerdNo'/></td>
						</tr>
						<tr>
							<td class="label">性别</td>
							<td class="data">
							<s:select disabled="true" name="instLegalPers.sex" list="@newland.iaf.base.model.dict.SexType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.sex"></s:select>
							 </td>
							<td class="label">年龄</td>
							<td class="data"><s:property  value='instLegalPers.age'/></td>
						</tr>
						<tr>
							<td class="label">学历</td>
							<td class="data">
							<s:select disabled="true" name="instLegalPers.education" list="@newland.iaf.base.model.dict.EducationType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.education"></s:select>
							 </td>
							<td class="label">婚姻状况</td>
							<td class="data">
							<s:select disabled="true" name="instLegalPers.maritalStatus" list="@newland.iaf.base.model.dict.MerryType@values()" listKey="toString()" listValue="getDesc()" value="instLegalPers.maritalStatus"></s:select>
							 </td>
						</tr>
						<tr>
							<td class="label">家庭住址</td>
							<td class="data"><s:property  value='instLegalPers.familyAddr'/></td>
							<td class="label">&nbsp;</td>
							<td class="data">&nbsp;</td>
						</tr>
      </table>
    </div>
 	 <h3 class="title"><span>机构LOGO</span></h3>
    <div class="search-bar" style="padding:10px 10px 30px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
      	<tr>
      	<td class="data" style="width:110px">
      		<n:instAuthButton cssClass="dark-btn" onclick="logoUpload()" value="上传机构LOGO" authCode="WDZL_XG"/><br/><br/>
      		<!--<s:submit cssClass="dark-btn" onclick="detail();" value="机构LOGO详情"></s:submit>-->
      		</td>
      		<td class="data" style="text-align:center;">
      		<s:if test="tfileList.size>=0">
      		<iframe id="logoFrame" name="merchFrame" marginheight="0" marginwidth="0" src="/inst/information/instInformation!logoInfo" width="100%" align="middle" scrolling="yes" frameborder="0" >
      		</iframe>
      		</s:if>
      		<s:else>
      		&nbsp;
      		</s:else>
      		</td>
      	</tr>
      </table>
    </div>
    <h3 class="title"><span>机构其它资料上传</span><p style="width:700px;color:black">(如果您有其他资料需要补充,可点击[上传]进行信息补充.本功能只支持图片格式的文件上传)</p></h3>
    <div class="search-bar" style="padding:10px 10px 30px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
      	<tr>
      	<td class="data" style="width:110px">
      		<n:instAuthButton cssClass="dark-btn" onclick="showUpload()" value="上传其他资料" authCode="WDZL_XG"/><br/><br />
			<!--<s:submit cssClass="dark-btn" onclick="detail();" value="其他资料详情"></s:submit>-->
      		</td>
      		<td class="data" style="text-align:center;">
      		<s:if test="tfileList.size>=0">
      		<iframe id="merchFrame" name="merchFrame" marginheight="0" marginwidth="0" src="/inst/information/instInformation!otherInfo" width="100%" align="middle" scrolling="yes" frameborder="0" >
      		</iframe>
      		</s:if>
      		<s:else>
      		&nbsp;
      		</s:else>
      		</td>
      	</tr>
      </table>
    </div>
  </div>
</div>
<script type="text/javascript">
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

			function showUpload(){
				parent.jDialog("上传其他资料","${ctx}/inst/information/instInformation!uploadIndex",800,500,true,function(){
					merchFrame.parent.location = "${ctx}/inst/information/instInformation";
				});
			}
			
			function logoUpload(){
				parent.jDialog("上传LOGO资料","${ctx}/inst/information/instInformation!toLogoUpload",800,500,true,function(){
					logoFrame.parent.location = "${ctx}/inst/information/instInformation";
				});
			}
			
			function detail(){
				parent.jDialog("资料详情","${ctx}/inst/information/instInformation!detail?filetype=instFile",700,550,true,function(){
					merchFrame.parent.location = "${ctx}/inst/information/instInformation";
				});
			}
			
		</script> 
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>