<#include "base.ftl" >
 <body>
 <div id="Wraper">
  <div class="CoReport rlt">
   <span class="name abs">${merchName}</span>
   <span class="num abs">
   <#if merchId?exists>
   ${merchId}
   <#else>
   	非金掌柜用户
   	</#if>
   </span>
   <span class="rnum abs">${reportId}</span>
   <span class="date abs">${reportDate?string("yyyy年MM月dd日")}</span>
  </div>
  <div align="center">
  	<p>广东汇卡商务服务有限公司<br/>
  	网址：www.uniondai.com<br/>
  	本信用评估报告仅供机构参考，感谢您使用本平台</p>
  </div>
 </div>
 </body>
</html>