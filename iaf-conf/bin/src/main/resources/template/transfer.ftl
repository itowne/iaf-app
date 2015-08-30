<#include "base.ftl" >
  	<body style="font-size:9px;padding:10px;">
	<div id="Wraper">
		<div class="report"> 
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tc">
				<tr>
					 <th colspan="7" class="tl">交易记录<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
		    	</tr>
     			 <tr>
                              <td class="label" rowspan="2" style="font-size:11px;"> 日期 </td>
                              <td class="label" rowspan="2" style="font-size:11px;"> 项目</td>
                              <td colspan="2" class="label" style="font-size:11px;">收款流水</td>
                              <td class="label" rowspan="2" style="font-size:11px;">付款流水</td>
                              <td class="label" rowspan="2" style="font-size:11px;">平台内贷款流水</td>
                              <td class="label" rowspan="2" style="font-size:11px;">平台内还款流水</td>
                              </tr>
                              <tr>
                              	<td class="label" style="font-size:11px;">银行卡收款流水</td>
                             	<td class="label" style="font-size:11px;">现金收款流水</td>
                              </tr>
							<#if trList?exists>
	                          <#list  trList as transRec>
	                          	<#if transRec?exists>
	                          		<tr>
	                          			<td class="data" rowspan="3" style="text-align:center">${transRec.date}</td>
	                          			<td class="data" style="text-align:center">金额</td>
	                          			<td class="data">${transRec.bankCardTrans?string('currency')}</td>
	                          			<td class="data">${transRec.cashTrans?string('currency')}</td>
	                          			<td class="data">${transRec.payTrans?string('currency')}</td>
	                          			<td class="data">${transRec.loanTrans?string('currency')}</td>
	                          			<td class="data">${transRec.refundTrans?string('currency')}</td>
	                          		</tr>
	                          		<tr>
	                          			<td class="data" style="text-align:center">笔数</td>
	                          			<td class="data">${transRec.bankCardCount}笔</td>
	                          			<td class="data">${transRec.cashCount}笔</td>
	                          			<td class="data">${transRec.payCount}笔</td>
	                          			<td class="data">${transRec.loanCount}笔</td>
	                          			<td class="data">${transRec.refundCount}笔</td>
	                          		</tr>
	                          		<tr>
	                          			<td class="data" style="text-align:center">日均</td>
	                          			<td class="data">${transRec.bankCardAvg?string('currency')}</td>
	                          			<td class="data">${transRec.cashAvg?string('currency')}</td>
	                          			<td class="data">${transRec.payAvg?string('currency')}</td>
	                          			<td class="data">${transRec.loanAvg?string('currency')}</td>
	                          			<td class="data">${transRec.refundAvg?string('currency')}</td>
	                          		</tr>
	                          	</#if>
	                          </#list>
	                        </#if>
	                        <#if trTotal?exists>
	                        	 <tr>
	                        	<td class="data" rowspan="8" style="text-align:center;color:red">汇总统计</td>
	                        	<td class="data" style="color:red">总金额</td>
	                        	<td class="data" style="color:red">${trTotal.bankCardTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.cashTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.payTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.loanTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.refundTotal?string('currency')}</td>
	                        </tr>
	                        <tr>
	                        	<td class="data" style="color:red">金额合计</td>
	                        	<td class="data" style="color:red" colspan="2">${trTotal.amountTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        </tr>
	                        <tr>
	                        	<td class="data" style="color:red">总笔数</td>
	                        	<td class="data" style="color:red">${trTotal.bankCardTotalCount}</td>
	                        	<td class="data" style="color:red">${trTotal.cashTotalCount}</td>
	                        	<td class="data" style="color:red">${trTotal.payTotalCount}</td>
	                        	<td class="data" style="color:red">${trTotal.loanTotalCount}</td>
	                        	<td class="data" style="color:red">${trTotal.refundTotalCount}</td>
	                        </tr>
	                       <tr>
	                        	<td class="data" style="color:red">总笔合计</td>
	                        	<td class="data" style="color:red" colspan="2">${trTotal.conutTotal}</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        </tr>
	                    	<tr>
	                        	<td class="data" style="color:red">日均</td>
	                        	<td class="data" style="color:red">${trTotal.bankCardTotalAvg?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.cashTotalAvg?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.payTotalAvg?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.loanTotalAvg?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.refundTotalAvg?string('currency')}</td>
	                        </tr>
	                        <tr>
	                        	<td class="data" style="color:red">日均合计</td>
	                        	<td class="data" style="color:red" colspan="2">${trTotal.dailyAvgTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        </tr>
	                        <tr>
	                        	<td class="data" style="color:red">月均</td>
	                        	<td class="data" style="color:red">${trTotal.bankCardTotalMonth?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.cashTotalMonth?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.payTotalMonth?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.loanTotalMonth?string('currency')}</td>
	                        	<td class="data" style="color:red">${trTotal.refundTotalMonth?string('currency')}</td>
	                        </tr>
	                        <tr>
	                        	<td class="data" style="color:red">月均合计</td>
	                        	<td class="data" style="color:red" colspan="2">${trTotal.monthTotal?string('currency')}</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        	<td class="data" style="color:red">--</td>
	                        </tr>
	                        </#if>
			</table>
		</div>
		<div style="page-break-after:always;"></div>
		<div class="cl20"></div>
		<div class="report">
		 <table border="0" cellpadding="0" cellspacing="0" width="100%" class="data-table label-tc" style="text-align:center">
				<tr>
					 <th colspan="10" class="tl">数据分析<span class="fr">报告日期：${reportDate?string("yyyy年MM月dd日")}</span></th>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">数据</td>
		    		<td class="label" style="font-size:11px">记录值</td>
		    		<td class="label" style="font-size:11px">单位</td>
		    		<td class="label" style="font-size:11px">参考值</td>
		    		<td class="label" style="font-size:11px">提示</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">日均营业时长</td>
		    		<td class="data" style="font-size:11px">
		    		<#if busTime?exists>
		    		${busTime}
		    		<#else>
		    		--
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">小时/日</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">单笔交易平均金额</td>
		    		<td class="data" style="font-size:11px">
		    		<#if avgTrade?exists>
		    		${avgTrade?string('currency')}
		    		<#else>
		    		0
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">元</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">日均收款交易额</td>
		    		<td class="data" style="font-size:11px">
		    		<#if avgDayTrade?exists>
		    		${avgDayTrade?string('currency')}
		    		<#else>
		    		0
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">元</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">日均收款交易笔数</td>
		    		<td class="data" style="font-size:11px">
		    		<#if avgDayNum?exists>
		    		${avgDayNum}
		    		<#else>
		    		0
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">笔</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">月均收款交易额</td>
		    		<td class="data" style="font-size:11px">
		    		<#if avgMonthTrade?exists>
		    		${avgMonthTrade?string('currency')}
		    		<#else>
		    		0
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">元</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="label" style="font-size:11px">月均收款交易笔数</td>
		    		<td class="data" style="font-size:11px">
		    		<#if avgMonthNum?exists>
		    		${avgMonthNum}
		    		<#else>
		    		0
		    		</#if>
		    		</td>
		    		<td class="data" style="font-size:11px">笔</td>
		    		<td class="data" style="font-size:11px">0</td>
		    		<td class="data">&nbsp;</td>
		    	</tr>
		  </table>
		</div>
	</div>
	</body>
</html>