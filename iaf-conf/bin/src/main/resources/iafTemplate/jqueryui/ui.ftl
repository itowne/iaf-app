<#assign version=323>
<link rel="stylesheet" type="text/css" href="${base}/resources/ui/jquery.ui.css?${version}" />
<#if parameters.grid??>
	<link rel="stylesheet" type="text/css" href="${base}/resources/grid/jqGrid.css?${version}" />
</#if>
<#if parameters.form??>
	<link rel="stylesheet" type="text/css" href="${base}/resources/form/jquery.validationEngine.css?${version}" />
</#if>
<script type="text/javascript" src="${base}/resources/ui/jquery.ui.js?${version}"></script>
<!--[if IE 6]><script type="text/javascript" src="${base}/resources/js/jquery.bgiframe.js"></script><![endif]-->
<#if parameters.grid??>
	<script type="text/javascript" src="${base}/resources/grid/jqGrid.js?${version}"></script>
	<script type="text/javascript" src="${base}/resources/grid/grid.locale-cn.js?${version}"></script>
</#if>
<#if parameters.form??>
	<script type="text/javascript" src="${base}/resources/form/jquery.validationEngine.js?${version}"></script>
	<script type="text/javascript" src="${base}/resources/form/jquery.validationEngine-zh_CN.js?${version}"></script>
</#if>
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
<script type="text/javascript">
	jQuery(function($){
		$.datepicker.regional['zh-CN'] = {
			closeText: '关闭',
			prevText: '&#x3c;上月',
			nextText: '下月&#x3e;',
			currentText: '今天',
			monthNames: ['一月','二月','三月','四月','五月','六月',
			'七月','八月','九月','十月','十一月','十二月'],
			monthNamesShort: ['一','二','三','四','五','六',
			'七','八','九','十','十一','十二'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
			dayNamesMin: ['日','一','二','三','四','五','六'],
			dateFormat: 'yy-mm-dd', firstDay: 1,
			isRTL: false};
		$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	});
	$(function(){
		$(".dark-btn").hover(function(){
			$(this).addClass("dark-btn-hover");
		},function(){
			$(this).removeClass("dark-btn-hover");
		});
	});
</script>