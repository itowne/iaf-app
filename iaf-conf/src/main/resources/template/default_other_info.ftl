<#include "default_style.ftl" >
<body>
<#if (otherInfo)??>
<h3 style="line-height:32px;text-align:center;font-size:15px;">其他资料</h3>
<div id="basic-info" style="padding:0 30px;">
	<div style="padding:15px 0;text-align:center;">
		<form action="${action}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
		<tr>
			<td class="data" align="left" style="font-weight:bold">补充资料概述:</td>
		</tr>
		<tr>
			<td class="data">
			<#if viewType=="INST">
			<div style="text-align:left;">${memo}</div>
			<#else>
			<textarea name="memo" size="300" style="width:100%" rows="5">${memo}</textarea>
			</#if>
			</td> 
		</tr>
		<tr>
			<#if viewType!="INST">
			<td class="data" align="center"><input class="dark-btn" type="submit" value="提交"/>
			</#if>
			</td>
		</tr>
		</table>
		</form>
	</div>
	</div>
	<div id="basic-info" style="padding:0 30px;">
		<div style="padding:15px 0;text-align:center;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
		<tr>
			<td class="data" align="left" style="font-weight:bold" colspan="2">补充资料上传图片:</td>
		</tr>
		<tr>
			<td class="label" width="150px">营业执照:</td>
			<td class="data">
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="YYZZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
					<img src="/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="500" height="460" alt=""/><br/>
				</#if>
				</#if>
			</#if>
           </#list>
			</td>
		</tr>
				<tr>
			<td class="label" width="150px">法人身份证:</td>
			<td class="data">
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="FRSFZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
					<img src="/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="500" height="460" alt=""/><br/>
				</#if>
				</#if>
			</#if>
           </#list>
			</td>
		</tr>
		<tr>
			<td class="label" width="150px">税务登记证:</td>
			<td class="data">
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="SWDJZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
					<img src="/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="500" height="460" alt=""/><br/>
				</#if>
				</#if>
			</#if>
           </#list>
			</td>
		</tr>
		<tr>
			<td class="label" width="150px">组织机构代码证</td>
			<td class="data">
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="ZZJGDMZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
					<img src="/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="500" height="460" alt=""/><br/>
				</#if>
				</#if>
			</#if>
           </#list>
			</td>
		</tr>
		<tr>
		<td class="label" width="150px">其他资料</td>
		<td class="data">
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="merchFile">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
					<img src="/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="500" height="460" alt=""/><br/>
				</#if>
				</#if>
			</#if>
           </#list>
		</td>
		</tr>
		</table>
		</div>
	</div>
	<#else>
	查询无记录!
	</#if>
</body>
</html>