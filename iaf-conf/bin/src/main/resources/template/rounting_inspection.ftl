<#include "base.ftl" >
 <body>
 <div id="Wraper" style="font-size:11px;">
  
  <div class="report"> 
   <table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tc" style="text-align:center">
   <tr>
     <th colspan="6" class="tl">巡检记录<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
    </tr>
    <tr>
                         <td width="10%" class="label" style="text-align:center">巡检日期</td>
                         <td width="10%" class="label" style="text-align:center">POS终端号</td>
                         <td width="10%" class="label" style="text-align:center">巡检状态</td>
                          <td width="10%" class="label" style="text-align:center">巡检员</td>
                          <td width="10%" class="label" style="text-align:center">巡检情况</td>
                          <td width="10%" class="label" style="text-align:center">巡检备注</td>
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
	                           <td class="data" style="text-align:left">
	                          <#if log.inspectCase?exists>
	                          <#if log.inspectCase=="0">
	                           <div style="width:100px;word-wrap: break-word; word-break: break-all;">	--</div>
	                          </#if>
	                          <#if log.inspectCase=="1">
	                         <div style="width:100px;word-wrap: break-word; word-break: break-all;"> POS机正常使用，参数和地址与系统资料相符</div>
	                          </#if>
	                          <#if log.inspectCase=="2">
	                           <div style="width:100px;word-wrap: break-word; word-break: break-all;"> 商户地址变更，但能联系上，清楚POS机去向</div>
	                          </#if>
	                          <#if log.inspectCase=="3">
	                          <div style="width:100px;word-wrap: break-word; word-break: break-all;">商户地址关门或转租，联系不到或找不到商户</div>
	                          </#if>
	                          <#if log.inspectCase=="4">
	                          <div style="width:100px;word-wrap: break-word; word-break: break-all;">POS机正常，但安装的实际地址或POS机参数与系统资料不符</div>
	                          </#if>
	                          <#if log.inspectCase=="5">
	                          	<div style="width:100px;word-wrap: break-word; word-break: break-all;">商户POS机已丢失或不知去向</div>
	                          </#if>
	                          <#if log.inspectCase=="6">
	                           <div style="width:100px;word-wrap: break-word; word-break: break-all;"> 商户实际经营范围与MCC码的核对情况（必填）</div>
	                          </#if>
	                          <#if log.inspectCase=="7">
	                          	<div style="width:100px;word-wrap: break-word; word-break: break-all;">其他情况</div>
	                          </#if>
	                          <#else>
	                          --
	                          </#if>
	                          </td>                               
	                          <td class="data" style="text-align:left">
	                          <#if log.memo?exists>
	                          <div style="width:210px;word-wrap: break-word; word-break: break-all;">${log.memo}</div>
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
  </div>
 </div>
 </body>
</html>