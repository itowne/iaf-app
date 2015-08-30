<#include "default_style.ftl" >
<body>
<div style="width:100%" align="center">
<#if viewType=="MERCH">
(备注：如您对下列内容有异议的，请点击经营数据报告申诉！)
</#if>
</div>
<h3 style="line-height:32px;text-align:center;font-size:15px;">服务巡检记录</h3>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table center-align">
                         <tr>
                          <td width="10%" class="label" style="text-align:center">巡检日期</td>
                          <td width="10%" class="label" style="text-align:center">POS终端号</td>
                          <td width="10%" class="label" style="text-align:center">巡检状态</td>
                          <td width="10%" class="label" style="text-align:center">巡检员</td>
                          <td width="30%" class="label" style="text-align:center">巡检情况</td>
                          <td width="30%" class="label" style="text-align:center">巡检备注</td>
                          </tr>
                          <#if inspectLog?exists>
	                          <#list inspectLog as log>
	                          <#if log?exists>
	                          <tr>
	                          <td class="data">
	                          <#if log.inspectDate?exists>
	                          ${log.inspectDate?string('yyyy年MM月dd日')}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.hcTermNo?exists>
	                          ${log.hcTermNo}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.inspectStat?exists>
	                          ${log.inspectStat}
	                          <#else>
	                          --
	                          </#if>
	                          </td>  
	                          <td class="data">
	                          <#if log.inspector?exists>
	                          ${log.inspector}
	                          <#else>
	                          --
	                          </#if>
	                          </td>
	                          <td class="data">
	                          <#if log.inspectCase?exists>
	                          <#if log.inspectCase=="0">
	                          	--
	                          </#if>
	                          <#if log.inspectCase=="1">
	                          POS机正常使用，参数和地址与系统资料相符
	                          </#if>
	                          <#if log.inspectCase=="2">
	                                                   商户地址变更，但能联系上，清楚POS机去向
	                          </#if>
	                          <#if log.inspectCase=="3">
	                                                   商户地址关门或转租，联系不到或找不到商户
	                          </#if>
	                          <#if log.inspectCase=="4">
	                          POS机正常，但安装的实际地址或POS机参数与系统资料不符
	                          </#if>
	                          <#if log.inspectCase=="5">
	                          	商户POS机已丢失或不知去向
	                          </#if>
	                          <#if log.inspectCase=="6">
	                                                      商户实际经营范围与MCC码的核对情况（必填）
	                          </#if>
	                          <#if log.inspectCase=="7">
	                          	其他情况
	                          </#if>
	                          <#else>
	                          --
	                          </#if>
	                          </td>                               
	                          <td class="data">
	                          <#if log.memo?exists>
	                          ${log.memo}
							  <#else>
							  --	                          
	                          </#if>
	                          </td>
	                          </tr>
	                          </#if>
	                          </#list>
	                         
                          	  <#else>
	                          	  <tr>
		                          <td class="data" colspan="3"> 查询无记录！ </td>
		                         
		                          </tr>
                          </#if>
						</table>
</body>
</html>