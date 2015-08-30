<#if parameters.resp??>
<#assign reps = stack.findValue(parameters.resp)/>
<@s.i18n name="menu">
<#if reps.currentBreadCrumb[0] != "">
<p id="crumbs" class="curr_pos clearfix">
    <@s.text name="menu.location"/>：
    <#list reps.currentBreadCrumb as item>
        <#if item != "">
            <#assign component = reps.breadCrumbComponent[item] />
            <#if component??>
            <span class="<#if item_has_next>node<#else>highLight</#if>" ><@s.text name="${component.title}"/></span>
            </#if>
            <#if item_has_next>&gt;</#if>
        </#if>
    </#list>
</p>
</#if>
</@s.i18n>
</#if>