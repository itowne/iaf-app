<#include "base.ftl" >
 <body>
 <div id="Wraper">
  <div class="cl20"></div>
 
  <div class="report"> 
   <table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tr">
    <tr>
     <th colspan="5" class="tl">现场调查<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}&nbsp;&nbsp;</span></th>
    </tr>
    <tr>
     <td width="12%" rowspan="5" class="label">经营信息</td>
     <td width="23%" class="label">营业用地性质</td>
     <td width="27%" class="data"><#if fieldSurvy.manageFieldNature?exists>
      <#if fieldSurvy.manageFieldNature=="OWN">
		<img src="http://59.41.214.134/resources/images/checked.gif"/>自有
	  <#else>
		<img src="http://59.41.214.134/resources/images/uncheck.gif"/>自有
	  </#if>
	  <#if fieldSurvy.manageFieldNature=="RENT">
	  	 <img src="http://59.41.214.134/resources/images/checked.gif"/>租用
	  <#else>
	  	<img src="http://59.41.214.134/resources/images/uncheck.gif"/>租用
	  </#if><#else>--</#if></td>
     <td width="18%" class="label">营业用地面积</td>
     <td width="20%" class="data"><#if fieldSurvy.manageFieldSquare?exists>${fieldSurvy.manageFieldSquare}<#else>--</#if>平方米</td>
    </tr>
    <tr>
     <td class="label">经营地段</td>
     <td colspan="3" class="data">
     <#if fieldSurvy.manageDistrict?exists>
     <#if fieldSurvy.manageDistrict=="BUSIN">
		 <img src="http://59.41.214.134/resources/images/checked.gif"/>商业区
	  <#else>
		<img src="http://59.41.214.134/resources/images/uncheck.gif"/>商业区
		</#if>
		<#if fieldSurvy.manageDistrict="INDUSTRY">
		 <img src="http://59.41.214.134/resources/images/checked.gif"/>工业区
		<#else>
		<img src="http://59.41.214.134/resources/images/uncheck.gif"/>工业区
		</#if>
		 <#if fieldSurvy.manageDistrict="HOUSE">
		 <img src="http://59.41.214.134/resources/images/checked.gif"/>住宅
		<#else>
		<img src="http://59.41.214.134/resources/images/uncheck.gif"/>住宅
		</#if>
     <#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">经营区域</td>
     <td colspan="3" class="data"><#if fieldSurvy.manageArea?exists>
      <#if fieldSurvy.manageArea=="DOWNTOWN">
		 <img src="http://59.41.214.134/resources/images/checked.gif"/>城区
		<#else>
		<img src="http://59.41.214.134/resources/images/uncheck.gif"/>城区
	  </#if>
	  <#if fieldSurvy.manageArea="SUBURB">
		 <img src="http://59.41.214.134/resources/images/checked.gif"/>郊区
	  <#else>
	 	<img src="http://59.41.214.134/resources/images/uncheck.gif"/>郊区
	  </#if>
	  <#if fieldSurvy.manageArea="REMOTE">
		<img src="http://59.41.214.134/resources/images/checked.gif"/>边远地区
	  <#else>
		 <img src="http://59.41.214.134/resources/images/uncheck.gif"/>边远地区
	  </#if>
     
     <#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">营业时间</td>
     <td class="data"><#if fieldSurvy.manageBussinessHours?exists>${fieldSurvy.manageBussinessHours}<#else>--</#if></td>
     <td class="label">员工人数</td>
     <td class="data"><#if fieldSurvy.manageScale?exists>${fieldSurvy.manageScale}<#else>--</#if>人</td>
    </tr>
    <tr>
     <td class="label">经营范围</td>
     <td class="data"><#if fieldSurvy.manageRangeMajor?exists>${fieldSurvy.manageRangeMajor}<#else>--</#if></td>
     <td class="label">分支机构</td>
     <td class="data"><#if fieldSurvy.manageBranchRange?exists>${fieldSurvy.manageBranchRange}<#else>--</#if></td>
    </tr>
    <tr>
     <td rowspan="15" class="label">调查员评价</td>
     <td  colspan="4" class="label" style="text-align:left">商户考察情况及意见</td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">是否检查验证商户的营业执照、身份证、税务登记证、组织机构证等证明文件</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
     <#if fieldSurvy.inspectLic?exists>
     <#if fieldSurvy.inspectLic=="YES">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>是,符合规定
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>是,符合规定
			</#if>
			<#if fieldSurvy.inspectLic=="NO">
				 <img src="http://59.41.214.134/resources/images/checked.gif"/>否,原因:${fieldSurvy.inspectLicDesc}
			<#else>
				 <img src="http://59.41.214.134/resources/images/uncheck.gif"/>否,原因:${fieldSurvy.inspectLicDesc}
			</#if>
     <#else>--</#if></td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">是否了解商户有相对应的财务/销售报表、销售凭证以证明其营业时间和月平均营业额（人民币）</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
     <#if fieldSurvy.inspectReport?exists>
     <#if fieldSurvy.inspectReport=="YES">
				 <img src="http://59.41.214.134/resources/images/checked.gif"/>是
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>是
			</#if>
			<#if fieldSurvy.inspectReport=="NO">
				 <img src="http://59.41.214.134/resources/images/checked.gif"/>,原因:${fieldSurvy.inspectReportDesc}
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>否,原因:${fieldSurvy.inspectReportDesc}
			</#if>
     <#else>	--</#if></td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">是否对商户进行实地考察</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
     <#if fieldSurvy.inspectOnsite?exists>
     <#if fieldSurvy.inspectOnsite=="YES">
				 <img src="http://59.41.214.134/resources/images/checked.gif"/>是
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>是
			</#if>
			<#if fieldSurvy.inspectOnsite=="NO">
				 <img src="http://59.41.214.134/resources/images/checked.gif"/>否,原因:${fieldSurvy.inspectOnsiteDesc}
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>否,原因:${fieldSurvy.inspectOnsiteDesc}
			</#if>
     <#else>	--</#if></td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">商户营业范围与营业执照登记是否相符</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
     <#if fieldSurvy.inspectRange?exists>
     <#if fieldSurvy.inspectRange=="YES">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>是，营业范围与营业执照登记相符
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>是，营业范围与营业执照登记相符
			</#if>
			<#if fieldSurvy.inspectRange=="NO">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>不符，原因：:${fieldSurvy.inspectRangeDesc}
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>不符，原因：:${fieldSurvy.inspectRangeDesc}
			</#if>
     <#else>	--</#if> </td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">是否有足够的存货来支持销售</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
     <#if fieldSurvy.inspectLoan?exists>
     <#if fieldSurvy.inspectLoan=="YES">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>是。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>是。
			</#if>	
			<#if fieldSurvy.inspectLoan=="NO">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>否，存贷数量不够或存贷与营业执照登记的营业类型不匹配。
			</#if>
     <#else>	--</#if> </td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">商户经营状</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
       <#if fieldSurvy.inspectBusi?exists>
			<#if fieldSurvy.inspectBusi=="GOOD">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>商户经营总体良好。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>商户经营总体良好。
			</#if>
			<#if fieldSurvy.inspectBusi=="ORDINARY">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>商户经营状况一般。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>商户经营状况一般。
			</#if>
			<#if fieldSurvy.inspectBusi=="WORSE">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>商户经营状况较差，但会有改善。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>商户经营状况较差，但会有改善。
			</#if>
			<#if fieldSurvy.inspectBusi=="BAD">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>商户经营状况差，风险很高。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>商户经营状况差，风险很高。
			</#if>
			<#else>
				--
			</#if>
     </td>
    </tr>
    <tr>
     <td colspan="4" class="label" style="text-align:left">商户考察意见</td>
    </tr>
    <tr>
     <td colspan="4" style="text-align:left" class="data">
       <#if fieldSurvy.inspectOpin?exists>
			<#if fieldSurvy.inspectOpin=="HIGHQUALITY">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>优质商户。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>优质商户。
			</#if>
			<#if fieldSurvy.inspectOpin=="LOWRISK">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>一般商户，风险较低。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>一般商户，风险较低。
			</#if>
			<#if fieldSurvy.inspectOpin=="GENERALRISK">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>一般商户，有一定风险，需要对其业务有所监控。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>一般商户，有一定风险，需要对其业务有所监控。
			</#if>
			<#if fieldSurvy.inspectOpin=="HIGHRISK">
				<img src="http://59.41.214.134/resources/images/checked.gif"/>高风险等级商户，暂不考虑发展。
			<#else>
				<img src="http://59.41.214.134/resources/images/uncheck.gif"/>高风险等级商户，暂不考虑发展。
			</#if>
			<#else>
				--
			</#if>
     </td>
    </tr>
   </table>
  </div>
 </div>
 </body>
</html>