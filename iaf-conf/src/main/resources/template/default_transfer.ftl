<#include "default_style.ftl" >
<body class="rotated">
<div style="width:100%" align="center">
<#if viewType=="MERCH">
(备注：如您对下列内容有异议的，请点击经营数据报告申诉！)
</#if>
</div>
<h3 style="line-height:32px;text-align:center;font-size:15px;">交易记录</h3>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table center-align">
                             <tr>
                              <td class="label" rowspan="2"> 日期 </td>
                              <td class="label" rowspan="2"> 项目</td>
                              <td colspan="2" class="label">收款流水</td>
                              <td class="label" rowspan="2">付款流水</td>
                              <td class="label" rowspan="2">平台内贷款流水</td>
                              <td class="label" rowspan="2">平台内还款流水</td>
                              </tr>
                              <tr>
                              	<td class="label">银行卡收款流水</td>
                             	<td class="label">现金收款流水</td>
                              </tr>
							<#if trList?exists>
	                          <#list  trList as transRec>
	                          	<#if transRec?exists>
	                          		<tr>
	                          			<td rowspan="3" style="text-align:center">${transRec.date}</td>
	                          			<td class="data">金额</td>
	                          			<td class="data">${transRec.bankCardTrans?string('currency')}</td>
	                          			<td class="data">${transRec.cashTrans?string('currency')}</td>
	                          			<td class="data">${transRec.payTrans?string('currency')}</td>
	                          			<td class="data">${transRec.loanTrans?string('currency')}</td>
	                          			<td class="data">${transRec.refundTrans?string('currency')}</td>
	                          		</tr>
	                          		<tr>
	                          			<td class="data">笔数</td>
	                          			<td class="data">${transRec.bankCardCount}笔</td>
	                          			<td class="data">${transRec.cashCount}笔</td>
	                          			<td class="data">${transRec.payCount}笔</td>
	                          			<td class="data">${transRec.loanCount}笔</td>
	                          			<td class="data">${transRec.refundCount}笔</td>
	                          		</tr>
	                          		<tr>
	                          			<td class="data">日均</td>
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
</body>
</html>