<#include "default_style.ftl" >
<body>
<div style="width:100%" align="center">
<#if viewType=="MERCH">
(备注：如您对下列内容有异议的，请点击经营数据报告申诉！)
</#if>
</div>
<h3 style="line-height:32px;text-align:center;font-size:15px;">装机撤机记录</h3>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table center-align">
                         <tr>
                          <td width="10%" class="label" style="text-align:center">POS终端号</td>
                          <td width="10%" class="label" style="text-align:center">终端状态</td>
                          <td width="10%" class="label" style="text-align:center">装机日期</td>
                          <td width="10%" class="label" style="text-align:center">装机员</td>
                          <td width="10%" class="label" style="text-align:center">撤机日期</td>
                          <td width="10%" class="label" style="text-align:center">撤机员</td>
                          <td width="40%" class="label" style="text-align:center">撤机原因</td>
                          </tr>
                          <#if installLog?exists>
	                          <#list installLog as log>
	                          <#if log?exists>
	                          <tr>
	                          <td class="data">
	                          <#if log.hcTermNo?exists>
	                          ${log.hcTermNo}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.termStat?exists>
	                          <#if log.termStat=="0">
	                         	 丢失
	                          </#if>
	                          <#if log.termStat=="1">
	                         	已装
	                          </#if>
	                         <#if log.termStat=="2">
	                         	 待装
	                          </#if>
	                          <#if log.termStat=="3">
	                         	退单
	                          </#if>
	                          <#if log.termStat=="4">
	                         	 撤机
	                          </#if>
	                          <#if log.termStat=="6">
	                         	停机
	                          </#if>
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.installDate?exists>
	                          ${log.installDate?string('yyyy年MM月dd日')}
	                          <#else>
	                          --
	                          </#if>
	                          </td>  
	                          <td class="data">
	                          <#if log.installOper?exists>
	                          ${log.installOper}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.uninstallDate?exists>
	                          ${log.uninstallDate?string('yyyy年MM月dd日')}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.uninstallOper?exists>
	                          ${log.uninstallOper}
	                          <#else>
	                          --
	                          </#if>
	                          </td>                                
	                          <td class="data">
	                          <#if log.uninstallReason?exists>
	                          ${log.uninstallReason}
							  <#else>
							  --	                          
	                          </#if>
	                          </td>
	                          </tr>
	                          </#if>
	                          </#list>
	                         
                          	  <#else>
	                          	  <tr>
		                          <td class="data" colspan="7"> 查询无记录！ </td>
		                         
		                          </tr>
                          </#if>
						</table>
</body>
</html>