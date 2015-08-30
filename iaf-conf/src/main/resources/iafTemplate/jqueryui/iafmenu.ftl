<ul class="parent">
<#list parameters.menu as node>
<#assign auth=node.backStageAuth>
<#if node.subNodeList.size()=0>
	<li><a href="${auth.url}" class="top-href">${auth.menuName}</a></li>
<#else>
	<li>
		<span class="top-href">${auth.menuName}</span>
		<ul class="sub">
			<#list node.subNodeList as subNode>
			<#assign subAuth=subNode.backStageAuth>
			<li><a href="${subAuth.url}">${subAuth.menuName}</a></li>
			</#list>
		</ul>
	</li>
</#if>
</#list>
</ul>