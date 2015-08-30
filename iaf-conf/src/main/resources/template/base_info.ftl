<#include "base.ftl" >
 <body style="font-size:11px;padding:10px;">
 <div id="Wraper">
  <div class="cl20">
  </div>
  <div class="RepFirst rlt">
   <h2 class="tc">${merch.merchName}经营数据报告由汇融易项目中心提供</h2>
    <div class="cl20"></div>
   <p class="txt">该商户的基本情况及现场调查情况与银行卡收单商户信息调查情况吻合；</p>
   <p class="txt">据金掌柜交易管家录得该商户近半年交易金额为
    <#if transReport.preHalfYearMonthRecive?exists>
      <em style="color:red;font-size:22px;font-family:tahoma;font-weight:bold;">${transReport.preHalfYearMonthRecive.amount?string('currency')}</em>
    <#else>
   	--
   </#if>元；</p>
   <p class="txt">据汇卡商务风险管理中心对该商户的风险评估等级界定为
	<#if merch.credit?exists>
		<#if merch.credit=="A">
			 <em style="color:red;font-size:22px;font-family:tahoma;font-weight:bold;">A级</em>，属于优质商户
		<#elseif merch.credit=="B">
			<em style="color:red;font-size:22px;font-family:tahoma;font-weight:bold;">B级</em>，属于一般商户
		<#elseif merch.credit=="C">
			 <em style="color:red;font-size:22px;font-family:tahoma;font-weight:bold;">C级</em>，属于有一定风险商户
		</#if>
    <#else>
    --
    </#if>
   </p>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <h3>风险等级评估参考：</h3>
    <div class="cl20"></div>
   <table border="0" cellpadding="0" cellspacing="0" class="data-table" width="550">
    <tr>
     <th width="139">风值范围</th>
     <th width="170">风险等级</th>
     <th width="240">描述</th>
    </tr>
    <tr>
     <td class="data">960(含)以上</td>
     <td class="data">A级</td>
     <td class="data">优质商户，基本上没有风险</td>
    </tr>
    <tr>
     <td class="data">840(含)~960</td>
     <td class="data">B级</td>
     <td class="data">一般商户，风险较低</td>
    </tr>
    <tr>
     <td class="data">720(含)~840</td>
     <td class="data">C级</td>
     <td class="data">有一定风险，发展后应加强监控</td>
    </tr>
    <tr>
     <td class="data">600(含)~720</td>
     <td class="data">D级</td>
     <td class="data">风险倾向商户，应加强风险审察、谨慎审批，原则上不予以发展</td>
    </tr>
    <tr>
     <td class="data">600以下</td>
     <td class="data">E级</td>
     <td class="data">高风险商户，不发展</td>
    </tr>
   </table>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   <p>本报告结果仅供商业决策参考之用，不得用作法律诉讼的依据。对由于使用报告所造成的损失，汇卡商务不承担任何责任。</p>
   <span class="logo abs"></span>
  </div>
  <div align="center">
  	<p class="txt">广东汇卡商务服务有限公司</p>
  	<p class="txt">网址：www.uniondai.com</p>
  	<p class="txt">本信用评估报告仅供机构参考，感谢您使用本平台</p>
  </div>
	<div style="page-break-after:always;"></div>
  <div class="">
   <table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table">
    <tr>
     <th colspan="7" class="tl">基本情况<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
    </tr>
    <tr>
     <td class="label">商户名称（注册名)</td>
     <td class="data" colspan="6"><#if merch.merchName?exists>${merch.merchName}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">机具装机地址</td>
     <td colspan="6" class="data"><#if merch.posAddr?exists>${merch.posAddr}<#else>--</#if></td>
    </tr>
    <tr>
    <td class="label">注册地址</td>
     <td colspan="6" class="data"><#if merch.regAddr?exists>${merch.regAddr}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">营业执照号</td>
     <td class="data" style="width:100px"><#if merch.businlic?exists>${merch.businlic}<#else>--</#if></td>
     <td class="label"><p>商户行业类别（MCC）</p></td>
     <td colspan="4" class="data" style="width:40%"><#if industry?exists>${industry}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">结算账号</td>
     <td class="data"><#if busiInfo.accountNo?exists>${busiInfo.accountNo}<#else>--</#if></td>
     <td class="label">结算银行</td>
     <td colspan="4" class="data"><#if busiInfo.bank?exists>${busiInfo.bank}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">注册时间</td>
     <td class="data"><#if merch.regTime?exists>${merch.regTime?string('yyyy年MM月dd日')}	<#else>--</#if></td>
     <td class="label">注册资本</td>
     <td colspan="4" class="data"><#if merch.regCap?exists>${merch.regCap}<#else>--	</#if></td>
    </tr>
    <tr>
     <td class="label">开业时间</td>
     <td class="data"><#if merch.openTime?exists>${merch.openTime?string('yyyy年MM月dd日')}<#else>--</#if></td>
     <td class="label">税务登记号</td>
     <td colspan="4" class="data"><#if merch.taxReg?exists>${merch.taxReg}<#else>--</#if></td>
    </tr>
    <tr>
     <td rowspan="4" class="label">法定代表人情况</td>
     <td class="label">姓名 </td>
     <td class="data"><#if legalPers.legalPersName?exists>${legalPers.legalPersName}<#else>--</#if></td>
     <td class="label">性别</td>
     <td class="data"><#if legalPers.sex?exists>${legalPers.sex.desc}<#else>--</#if></td>
     <td class="label">年龄</td>
     <td class="data"><#if legalPers.age?exists>${legalPers.age}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">身份证号码</td>
     <td class="data"><#if legalPers.cerdNo?exists>${legalPers.cerdNo}<#else>--</#if></td>
     <td class="label">婚姻状况</td>
     <td class="data"><#if legalPers.maritalStatus?exists>${legalPers.maritalStatus.desc}<#else>--</#if></td>
     <td class="label">学历</td>
     <td class="data"><#if legalPers.eduBackground?exists>${legalPers.eduBackground.desc}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">办公电话</td>
     <td class="data" colspan="2"><#if legalPers.officePhone?exists>${legalPers.officePhone}<#else>--</#if></td>
     <td class="label">手机</td>
     <td class="data" colspan="2"><#if legalPers.mobiPhone?exists>${legalPers.mobiPhone}<#else>--</#if></td>
    </tr>
    <tr>
    <td class="label">家庭住址</td>
     <td class="data" colspan="5"><#if legalPers.faAddr?exists>${legalPers.faAddr}<#else>--</#if></td>
    </tr>
    <tr>
     <td class="label">联系人</td>
     <td class="data"><#if merch.contract?exists>${merch.contract}<#else>--</#if></td>
     <td class="label">联系电话</td>
     <td colspan="4" class="data"><#if merch.contractTel?exists>${merch.contractTel}<#else>--</#if></td>
     </tr>
    <tr>
     <td class="label">商户性质</td>
     <td colspan="6" class="data"><#if busiInfo.merchNatrue?exists>
     	<#if busiInfo.merchNatrue=="国营">
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			国营
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			国营
			</#if>
			<#if busiInfo.merchNatrue=="集体">
			 <img src="http://59.41.214.134/resources/images/checked.gif"/>
			集体
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			集体
			</#if>
			<#if busiInfo.merchNatrue=="私营">
			 <img src="http://59.41.214.134/resources/images/checked.gif"/>
			私营
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			私营
			</#if>
			<#if busiInfo.merchNatrue=="个体工商户">
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			个体工商户
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			个体工商户
			</#if>
			<#if busiInfo.merchNatrue=="合资">
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			合资
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			合资
			</#if>
			<#if busiInfo.merchNatrue=="股份制">
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			股份制
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			股份制
			</#if>
			<#if busiInfo.merchNatrue!="国营"&&busiInfo.merchNatrue!="集体"&&busiInfo.merchNatrue!="私营"&&busiInfo.merchNatrue!="个体工商户"&&busiInfo.merchNatrue!="合资"&&busiInfo.merchNatrue!="股份制">
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			其他
			<#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			其他
			</#if>
    <#else>
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			国营
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			集体
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			私营
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			个体工商户
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			合资
			<img src="http://59.41.214.134/resources/images/uncheck.gif"/>
			股份制
			<img src="http://59.41.214.134/resources/images/checked.gif"/>
			其他
    </#if></td>
     </tr>
   </table>
  </div>
 </div>
 </body>
</html>