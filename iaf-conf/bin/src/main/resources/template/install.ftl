<#include "base.ftl" >
<body>
<div id="Wraper" style="font-size:11px;">
	<div class="report"> 
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tc" style="text-align:center;">
		<tr>
			<th colspan="7" class="tl">装机撤机记录<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
		</tr>
		<tr>
			<td width="10%" class="label" style="text-align:center">POS终端号</td>
			<td width="10%" class="label" style="text-align:center">终端状态</td>
			<td width="10%" class="label" style="text-align:center">装机日期</td>
			<td width="10%" class="label" style="text-align:center">装机员</td>
			<td width="10%" class="label" style="text-align:center">撤机日期</td>
			<td width="10%" class="label" style="text-align:center">撤机员</td>
			<td width="20%" class="label" style="text-align:center">撤机原因</td>
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
	      		<td class="data" style="text-align:left;">
	      			<#if log.uninstallReason?exists>
	      				<div style="width:180px;word-wrap: break-word; word-break:  break-all;">${log.uninstallReason}</div>
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
	</div>
</div>
</body>
</html>