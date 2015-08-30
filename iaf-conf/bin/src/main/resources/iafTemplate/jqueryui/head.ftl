<meta charset="utf-8"/>
<title><#rt/>
<#if parameters.title??>
	${parameters.title}<#t/>
<#else>
	汇融易 - 信用即财富，流水可贷款<#t/>
</#if>
</title>
<meta name="keywords" content="<#rt/>
<#if parameters.keywords??>
	${parameters.keywords}<#t/>
<#else>
	汇融易,借款,放贷,经营流水,汇卡商务<#t/>
</#if>
" />
<meta name="description" content="<#rt/>
<#if parameters.description??>
	${parameters.description}<#t/>
<#else>
	全国首家只需经营流水即可借贷的投融资服务平台<#t/>
</#if>
" />
<#if parameters.cssFiles??>
	<#list cssFiles as cssFile><#t/>
		<link rel="stylesheet" type="text/css" href="${base}/resources/css/${cssFile}.css"/>
	</#list><#t/>
</#if>
<#if parameters.jsFiles??>
	<#list jsFiles as jsFile><#t/>
		<script type="text/javascript" src="${base}/resources/js/${jsFile}.js"></script>
	</#list><#t/>
</#if>