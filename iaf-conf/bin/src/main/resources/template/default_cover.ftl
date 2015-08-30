<#include "default_style.ftl" >
<body>
	<div style="height:150px;"></div>
	<div style="margin:10px">
		<div style="margin:50px">
			<h3 style="line-height:32px;text-align:center;font-size:30px;">经营数据报告</h3>
		</div>
		<div style="margin:20px;">
			<h3 style="line-height:32px;text-align:center;font-size:25px;">商户名称: ${merchName}</h3>
		</div>
		<div style="margin:20px;">
			<h3 style="line-height:32px;text-align:center;font-size:20px;">报告日期: ${beginDate?string("yyyy年MM月dd日")}--${endDate?string("yyyy年MM月dd日")}</h3>
		</div>
		<div style="margin:20px;vertical-align:bottom;">
			<h3 style="line-height:32px;text-align:center;font-size:20px;">广东汇卡商务服务有限公司</h3>
		</div>
	</div>
</body>
</html>