<#include "base.ftl">
 <body>
 <div id="Wraper">
  <div class="report"> 
  <table width="650" border="0" cellspacing="0" cellpadding="0" class="data-table" style="page-break-after: auto;">
  	<tr>
	<th colspan="2" >其他资料<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
    </tr>
    <tr>
    	<td class="label" colspan="2" align="center">营业执照:<#if YYZZ_count?exists>${YYZZ_count}<#else>--</#if>张&nbsp;&nbsp;
    	法人身份证:<#if FRSFZ_count?exists>${FRSFZ_count}<#else>--</#if>张&nbsp;&nbsp;
    	税务登记证:<#if SWDJZ_count?exists>${SWDJZ_count}<#else>--</#if>张&nbsp;&nbsp;
    	组织机构证:<#if ZZJGDMZ_count?exists>${ZZJGDMZ_count}<#else>--</#if>张&nbsp;&nbsp;
    	其他:<#if merchFile_count?exists>${merchFile_count}<#else>--</#if>张</td>
    </tr>
    <tr>
    	<td class="data" style="text-align:left;"><div style="width:640px;word-wrap: break-word; word-break:  break-all;">[补充资料概述]:${memo}</div></td>
    </tr>
			<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="YYZZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
		      	<tr>
		      		<td class="label" align="center">营业执照</td>
		      	</tr>
		      	<tr>
		      		<td class="data" align="center" width="650" height="500" >
					<img src="http://113.108.195.242:20880/resources/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="650" height="600" alt=""/>
					</td>
				</tr>
				</#if>
				</#if>
			</#if>
           </#list>
		<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="FRSFZ">
		      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
		      	<tr>
		      		<td class="label" align="center">法人身份证</td>
		      	</tr>
		      	<tr>
		      		<td class="data" align="center" width="650" height="500" >
					<img src="http://113.108.195.242:20880/resources/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="650" height="600" alt=""/>
					<div class='pageNext'></div>
					</td>
				</tr>
				</#if>
				</#if>
			</#if>
           </#list>
		<#list otherInfo as file>
	      	<#if file?exists>
	      		<#if file.fileType=="SWDJZ">
			      	<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
			      	<tr>
			      		<td class="label" align="center">税务登记证</td>
			      	</tr>
			      	<tr>
			      		<td class="data" align="center" width="650" height="500" >
						<img src="http://113.108.195.242:20880/resources/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="650" height="600" alt=""/>
						</td>
					</tr>
					</#if>
					</#if>
	      	</#if>
           </#list>
           <#list otherInfo as file>
	      	<#if file?exists>
	      			<#if file.fileType=="ZZJGDMZ">
		      			<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
		      			<tr>
			      		<td class="label" align="center">组织机构代码证</td>
			      		</tr>
			      		<tr>
			      			<td class="data" align="center" width="650" height="500" >
							<img src="http://113.108.195.242:20880/resources/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="650" height="600" alt=""/>
							</td>
						</tr>
						</#if>
						</#if>
	      	</#if>
           </#list>
           <#list otherInfo as file>
	      	<#if file?exists>
	      			<#if file.fileType=="merchFile">
		      					<#if file.metaType == "JPG" || file.metaType == "GIF" || file.metaType == "PBM" || (file.metaType?index_of("image") >= 0)>
								<tr>
			      				<td class="label" align="center">其他资料</td>
			      				</tr>
			      				<tr>
			      				<td class="data" align="center" width="650" height="500">
								<img src="http://113.108.195.242:20880/resources/DrawImage.do?id=${file.ifile?string("####")}&amp;type=exp" width="650" height="600" alt=""/>
								</td>
						</tr>
								</#if>
								</#if>
	      	</#if>
           </#list>
		</table>
  </div>
 </div>
 </body>
</html>