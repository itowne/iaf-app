<#include "default_style.ftl" >
<body>
<div style="width:100%" align="center">
<#if viewType=="MERCH">
(备注：如您对下列内容有异议的，请点击经营数据报告申诉！)
</#if>
</div>
<h3 style="line-height:32px;text-align:center;font-size:15px;">现场调查情况</h3>
<#if fieldSurvy?exists>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
	 <tr>
	  <td rowspan="5" class="label">商户经营信息</td>
	  <td class="label">营业用地性质：</td>
	  <td class="data">
	  <#if fieldSurvy.manageFieldNature?exists>
	  <#if fieldSurvy.manageFieldNature=="OWN">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>自有
	  <#else>
		<input type="checkBox" name="checkbox" disabled="disabled"/>自有
	  </#if>
	  <#if fieldSurvy.manageFieldNature=="RENT">
	  	 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>租用
	  <#else>
	  <input type="checkBox" name="checkbox" disabled="disabled"/>租用
	  </#if>
	  <#else>--
	  </#if></td>
	  <td class="label">营业用地面积：</td>
	  <td class="data">
	  <#if fieldSurvy.manageFieldSquare?exists>
	  ${fieldSurvy.manageFieldSquare}
	  <#else>--</#if></td>
	 </tr>
	 <tr>
	  <td class="label">经营地段：</td>
	  <td colspan="3" class="data">
	  <#if fieldSurvy.manageDistrict?exists>
	   <#if fieldSurvy.manageDistrict=="BUSIN">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>商业区
	  <#else>
		<input type="checkBox" name="checkbox" disabled="disabled"/>商业区
		</#if>
		<#if fieldSurvy.manageDistrict="INDUSTRY">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>工业区
		<#else>
		<input type="checkBox" name="checkbox" disabled="disabled"/>工业区
		</#if>
		 <#if fieldSurvy.manageDistrict="HOUSE">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>住宅
		<#else>
		<input type="checkBox" name="checkbox" disabled="disabled"/>住宅
		</#if>
	  <#else>--
	  </#if> </td>
	  </tr>
	 <tr>
	  <td class="label">经营区域：</td>
	  <td colspan="3" class="data">
	  <#if fieldSurvy.manageArea?exists>
	   <#if fieldSurvy.manageArea=="DOWNTOWN">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>城区
		<#else>
		<input type="checkBox" name="checkbox" disabled="disabled"/>城区
	  </#if>
	  <#if fieldSurvy.manageArea="SUBURB">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>郊区
	  <#else>
	 	<input type="checkBox" name="checkbox" disabled="disabled"/>郊区
	  </#if>
	  <#if fieldSurvy.manageArea="REMOTE">
		 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>边远地区
	  <#else>
		 <input type="checkBox" name="checkbox" disabled="disabled"/>边远地区
	  </#if>
	  <#else>--
	  </#if></td>
	  </tr>
	 <tr>
	  <td class="label">营业时间：</td>
	  <td class="data">
	  <#if fieldSurvy.manageBussinessHours?exists>
	  ${fieldSurvy.manageBussinessHours}
	  <#else>--</#if></td>
	  <td class="label"> 员工人数： </td>
	  <td class="data"><#if fieldSurvy.manageScale?exists>${fieldSurvy.manageScale}<#else>--</#if></td>
	 </tr>
	 <tr>
	  <td class="label">经营范围--主业：</td>
	  <td class="data"><#if fieldSurvy.manageRangeMajor?exists>${fieldSurvy.manageRangeMajor}<#else>--</#if></td>
	  <td class="label"> 分支机构--范围： </td>
	  <td class="data"><#if fieldSurvy.manageBranchRange?exists>${fieldSurvy.manageBranchRange}<#else>--</#if></td>
	 </tr>
	<tr>
		<td rowspan="16" class="label">考察情况及意见</td>
    	<td colspan="4" class="label" style="text-align: left">是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectLic?exists>
			<#if fieldSurvy.inspectLic=="YES">
				 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是,符合规定
			<#else>
				 <input type="checkBox" name="checkbox" disabled="disabled"/>是,符合规定
			</#if>
			<#if fieldSurvy.inspectLic=="NO">
				 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>否,原因:${fieldSurvy.inspectLicDesc}
			<#else>
				 <input type="checkBox" name="checkbox" disabled="disabled"/>否,原因:${fieldSurvy.inspectLicDesc}
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否了解商户有相对应的财务/销售报表，销售凭证以证明其营业时间和月平均银行卡营业额（人民币）</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectReport?exists>
			<#if fieldSurvy.inspectReport=="YES">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>是
			</#if>
			<#if fieldSurvy.inspectReport=="NO">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>否,原因:${fieldSurvy.inspectReportDesc}
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>否,原因:${fieldSurvy.inspectReportDesc}
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>
    <tr>
		<td colspan="4" class="label" style="text-align: left">是否对收银员进行业务知识和操作技能的资格审核</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectKnow?exists>
			<#if fieldSurvy.inspectKnow=="YES">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>是
			</#if>
			<#if fieldSurvy.inspectKnow=="NO">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>否,原因:${fieldSurvy.inspectKnowDesc}
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>否,原因:${fieldSurvy.inspectKnowDesc}
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否对商户进行实地考察</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectOnsite?exists>
			<#if fieldSurvy.inspectOnsite=="YES">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>是
			</#if>
			<#if fieldSurvy.inspectOnsite=="NO">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>否,原因:${fieldSurvy.inspectOnsiteDesc}
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>否,原因:${fieldSurvy.inspectOnsiteDesc}
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户营业范围与营业执照登记相符</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectRange?exists>
			<#if fieldSurvy.inspectRange=="YES">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是，营业范围与营业执照登记相符
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>是，营业范围与营业执照登记相符
			</#if>
			<#if fieldSurvy.inspectRange=="NO">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>不符，原因：:${fieldSurvy.inspectRangeDesc}
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>不符，原因：:${fieldSurvy.inspectRangeDesc}
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>                   
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否有足够的存贷来支持销售</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectLoan?exists>
			<#if fieldSurvy.inspectLoan=="YES">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>是。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>是。
			</#if>	
			<#if fieldSurvy.inspectLoan=="NO">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>                  
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户的经营状况</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectBusi?exists>
			<#if fieldSurvy.inspectBusi=="GOOD">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>商户经营总体良好。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>商户经营总体良好。
			</#if>
			<#if fieldSurvy.inspectBusi=="ORDINARY">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>商户经营状况一般。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>商户经营状况一般。
			</#if>
			<#if fieldSurvy.inspectBusi=="WORSE">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>商户经营状况较差，但会有改善。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>商户经营状况较差，但会有改善。
			</#if>
			<#if fieldSurvy.inspectBusi=="BAD">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>商户经营状况差，风险很高。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>商户经营状况差，风险很高。
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>                  
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户的考察意见</td>
	</tr>
	<tr>
		<td colspan="4" class="data">
			<#if fieldSurvy.inspectOpin?exists>
			<#if fieldSurvy.inspectOpin=="HIGHQUALITY">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>优质商户。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>优质商户。
			</#if>
			<#if fieldSurvy.inspectOpin=="LOWRISK">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>一般商户，风险较低。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>一般商户，风险较低。
			</#if>
			<#if fieldSurvy.inspectOpin=="GENERALRISK">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>一般商户，有一定风险，需要对其业务有所监控。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>一般商户，有一定风险，需要对其业务有所监控。
			</#if>
			<#if fieldSurvy.inspectOpin=="HIGHRISK">
				<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>高风险等级商户，暂不考虑发展。
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>高风险等级商户，暂不考虑发展。
			</#if>
			<#else>
				--
			</#if>
		</td>
	</tr>
	</table>
	<#else>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
	 <tr>
	  <td rowspan="6" class="label">营业用地性质：</td>
	  <td class="label">经营地段：</td>
	  <td class="data">--</td>
	  <td class="label">营业用地面积：</td>
	  <td class="data">--</td>
	 </tr>
	 <tr>
	  <td class="label">经营地段：</td>
	  <td colspan="3" class="data">--</td>
	  </tr>
	 <tr>
	  <td class="label">经营区域：</td>
	  <td colspan="3" class="data">--</td>
	  </tr>
	 <tr>
	  <td class="label">营业时间：</td>
	  <td class="data">--</td>
	  <td class="label"> 员工人数： </td>
	  <td class="data">--</td>
	 </tr>
	 <tr>
	  <td class="label">经营范围--主业：</td>
	  <td class="data">--</td>
	  <td class="label"> 分支机构--范围： </td>
	  <td class="data">--</td>
	 </tr>
	 <tr>
	  <td class="label">经营范围--副业：</td>
	  <td class="data">--</td>
	  <td class="label"> 分支机构--数量： </td>
	  <td class="data">--</td>
	 </tr>
	 <tr>
		<td rowspan="16" class="label">考察情况及意见</td>
    	<td colspan="4" class="label" style="text-align: left">是否检查验证商户的营业执照，身份证，税务登记证，组织机构证等证明文件</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否了解商户有相对应的财务/销售报表，销售凭证以证明其营业时间和月平均银行卡营业额（人民币）</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>
    <tr>
		<td colspan="4" class="label" style="text-align: left">是否对收银员进行业务知识和操作技能的资格审核</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否对商户进行实地考察</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户营业范围与营业执照登记相符</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>                   
	<tr>
		<td colspan="4" class="label" style="text-align: left">是否有足够的存贷来支持销售</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>                  
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户的经营状况</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>                  
    <tr>
		<td colspan="4" class="label" style="text-align: left">商户的考察意见</td>
	</tr>
	<tr>
		<td colspan="4" class="data">--</td>
	</tr>
	</table>
	</#if>
</body>
</html>