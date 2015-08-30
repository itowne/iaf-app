<#include "base.ftl" >
<body>
<div id="Wraper" style="font-size:11px;">
	<div class="report"> 
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tc" style="text-align:center;">
		<tr>
			<th colspan="1" class="tl">企业经营报告核查情况<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
		</tr>
		<tr>
			<td width="100%" class="label" style="text-align:center">放贷机构对本商户经营数据报告核查的情况说明:</td>
		</tr>
		<tr>
			<td class="data">
				<textarea name="verification" size="300" style="width:100%" rows="5">${verification}</textarea>
			</td>
		</tr> 
	</table>
	</div>
</div>
</body>
</html>