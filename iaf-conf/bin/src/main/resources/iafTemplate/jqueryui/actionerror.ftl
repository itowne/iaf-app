<#if (actionErrors?exists && actionErrors?size > 0)>
	<script type="text/javascript">
        $(document).ready(function(){
        	jalert("${actionErrors[0]}","<@s.text name="ui.prompt"/>");
        });
	</script>
</#if>
