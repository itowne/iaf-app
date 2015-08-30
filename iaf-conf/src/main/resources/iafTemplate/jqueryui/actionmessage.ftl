<#if (actionMessages?exists && actionMessages?size > 0)>
	<script type="text/javascript">
        $(document).ready(function(){
           	jalert("${actionMessages[0]}","<@s.text name="ui.prompt"/>");
        });
	</script>
</#if>
