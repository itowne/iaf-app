<#include "default_style.ftl" >
<body>
<#if (busiVerifition)??>
<h3 style="line-height:32px;text-align:center;font-size:15px;">企业经营报告核查情况</h3>
<div id="basic-info" style="padding:0 30px;">
	<input type="hidden" name="imerch" value="merchs.imerch" />
	<div style="padding:15px 0;text-align:center;">
		<form action="${action}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
			<tr>
				<td class="data" align="left" style="font-weight:bold">以下内容为放贷机构对本商户经营数据报告核查的情况说明:</td>
			</tr>
			<tr>
			<td class="data">
				<textarea name="verification" size="300" style="width:100%" rows="5">${verification}</textarea>
			</td> 
		</tr>
		<tr>
			<td class="data" align="center"><input class="dark-btn" type="submit" value="提交"/></td>
		</tr>
		</table>
		</form>
	</div>
</div>
<#else>
	查询无记录!
</#if>
</body>
</html>