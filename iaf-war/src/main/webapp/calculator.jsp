<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/template/portal/meta.jspf" %>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="main" class="tools">
			<div class="title" id="tools-t">
				<div class="iaf-grid">
					<h3>贷款工具</h3>
					<ul>
						<li class="cur"><a href="calculator.jsp" class="calculator"><span>贷款计算器</span></a></li>
						<li><a href="/homePage!assessment" class="assessment"><span>借款评估</span></a></li>
					</ul>
				</div>
			</div>
			<div class="content" id="tools-b">
				<div class="iaf-grid">
					<h3>借款利息计算器</h3>
					<p>汇融易采用的是通用的"<strong>等额本息还款法</strong>"，即借款人每月以相等的金额偿还贷款本息，也是银行房贷等采用的方法。</p>
					<p>使用利息计算器，能帮您计算每月需要偿还的本息情况；</p>
					<p>同时，一份完整的本息偿还时间表，让您能更直观地了解还款本息详情(计算中采用四舍五入)</p>
					<table id="calculator-table">
						<tr>
							<td class="label">借款金额：</td>
							<td><input maxlength="9" type="text" class="calculator-input" id="amount" onblur="checkAmt();"/>&nbsp;<em>(万元)</em></td>
						</tr>
						<tr>
							<td class="label">借款利率：</td>
							<td><input maxlength="5" type="text" class="calculator-input" id="rate"/>&nbsp;<em>(%)</em></td>
						</tr>
						<tr>
							<td class="label">借款期限：</td>
							<td><input maxlength="5" type="text" class="calculator-input" id="term"/>&nbsp;<em>(个月)</em></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="checkbox" id="plan"/>&nbsp;<label for="plan">显示还款时间表</label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><button id="calculate" onclick="calculate();">&nbsp;</button></td>
						</tr>
					</table>
					<div class="result">
						<table id="calculator-result">
							<tr>
								<td><h3>您输入的借款信息：</h3></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td class="label">本金(借款金额)：</td>
											<td><span id="input-amount" class="result-input">&nbsp;</span>元</td>
											<td class="label" style="padding-left:25px;">利率：</td>
											<td><span id="input-rate" class="result-input">&nbsp;</span>%</td>
											<td class="label" style="padding-left:25px;">借款期限：</td>
											<td><span id="input-term" class="result-input">&nbsp;</span>个月</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td><h3 style="margin-top:10px;">计算结果：</h3></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td class="label">总利息：</td>
											<td><span id="interest" class="result-input">&nbsp;</span>元</td>
											<td class="label" style="padding-left:25px;">总本金+总利息：</td>
											<td><span id="total" class="result-input">&nbsp;</span>元</td>
											<td class="label" style="padding-left:25px;">每月还款金额：</td>
											<td><span id="mPayment" class="result-input">&nbsp;</span>元</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="plan-list">
								<td>
									<table id="plan-table">
										<thead>
											<tr>
												<th>月份</th>
												<th>还款金额(元)</th>
												<th>本金(元)</th>
												<th>利息(元)</th>
												<th>剩余还款金额(元)</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</td>
							</tr>
						</table>
						<span id="arrow">&nbsp;</span>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/template/portal/co-bank.jspf" %>
		<%@include file="/template/portal/footer.jspf" %>
		<script type="text/javascript" src="/resources/js/jquery.js"></script>
		<script type="text/javascript" src="/resources/js/portal.js"></script>
		<script type="text/javascript">
			function checkAmt(){
				var amt = $("#amount").val();
				if(amt!=""){
					if(isNaN(amt)){
						alert("请输入合法的借款金额！");
						return false;
					}else{
						if(amt.length>1){
							if(!/^\d+(\d|(\.[0-9]{1}))$/.test(amt)){
								alert("借款金额只能保存一位小数,请重新输入！");
								return false;
							}
						}
					}
					//var amount = parseFloat(amt).toFixed(1);
					$("#amount").val(amt);
				}
			}
			function calculate(){
				var reg = /^\d+$/,amount = $("#amount").val();
				if(!reg.test(amount))
				if(isNaN(amount)){
					alert("请输入合法的借款金额！");
					$("#amount").select().focus();
					return;
				}else{
					if(amount.length>1){
						if(!/^\d+(\d|(\.[0-9]{1}))$/.test(amount)){
							alert("借款金额只能保存一位小数,请重新输入！");
							$("#amount").select().focus();
							return ;
						}
					}
				}
				amount = amount*10000;
				var rate = $("#rate").val();
				if(isNaN(rate)){
					alert("请输入合法的借款利率！");
					$("#rate").select().focus();
					return;
				}else{
					if(rate.length>1){
						if(!/^-?\d+\.?\d{0,2}$/.test(rate)){
							alert("借款利率只能保存两位小数,请重新输入!");
							$("#rate").select().focus();
							return;
						}
					}
					if(rate==0){
						alert("借款利率请填写大于0的数字!");
						$("#rate").select().focus();
						return;
					}
				}
				
				var term = $("#term").val();
				if(isNaN(term)){
					alert("借款金额请输入数字!");
					$("#term").select().focus();
					return;
				}else{
					if(term==0){
						alert("借款金额请输入大于0的数字!");
						$("#term").select().focus();
						return;
					}
				}
				var rex = /^\+?[1-9][0-9]*$/;				
				if(!rex.test(term)){
					alert("借款期限请输入整数！");
					$("#term").select().focus();
					return;
				}
				
				$("#input-amount").html(amount);
				$("#input-rate").html(rate);
				$("#input-term").html(term);
				var mRate = parseFloat(rate)/12/100;// 月利率
				var delta = Math.pow(1+mRate,term);
				var mPayment = (amount*mRate*delta) / (delta-1);// 月均还款金额=[贷款总额×月利率×（1＋月利率）＾贷款期数(月)] / [（1＋月利率）＾贷款期数(月)－1]
				var total = mPayment * term;
				var interest = total - amount;
				mPayment = toDecimal(mPayment);
				total = toDecimal(total);
				$("#mPayment").html(mPayment);
				$("#total").html(total);
				$("#interest").html(toDecimal(interest));
				if($("#plan").is(":checked")){
					$("#plan-list tbody").empty();
					var principal = amount;// 剩余本金--初始为>借款金额
					var mRest = total;// 剩余还款金额--初始为>总本金+总利息
					for(var i=1;i<term;i++){
						var html = "<tr><td>"+i+"</td>";// 月份
						html += "<td>"+mPayment+"</td>";// 每月还款金额--固定不变
						var mInterest = toDecimal(principal*mRate);// 每月还款利息=剩余本金*月利率
						var mPrincipal = toDecimal(mPayment - mInterest);// 每月还款本金=每月月还款金额-每月还款利息
						html += "<td>"+mPrincipal+"</td>";
						html += "<td>"+mInterest+"</td>";
						mRest = toDecimal(mRest - mPayment);// 剩余还款金额
						html += "<td>"+mRest+"</td>";
						html += "</tr>";
						principal = principal -mPrincipal;// 更新剩余本金
						$("#plan-list tbody").append(html);
					}
					
					//还款最后一个月单独处理
					var html = "<tr><td>"+term+"</td>";// 月份
					html += "<td>"+mRest+"</td>";// 月还款金额=上月剩余还款金额
					var mInterest = toDecimal(principal*mRate);// 每月还款利息=剩余本金*月利率
					var mPrincipal = toDecimal(mRest - mInterest);// 每月还款本金=月还款金额-每月还款利息
					html += "<td>"+mPrincipal+"</td>";
					html += "<td>"+mInterest+"</td>";
					html += "<td>0.00</td>";
					html += "</tr>";
					$("#plan-list tbody").append(html);
					
					$("#plan-list").show();
				}else{
					$("#plan-list").hide();
				}
				$("#main.tools .result").show();
				var $body = (window.opera)? (document.compatMode=="CSS1Compat"? $('html') : $('body')) : $('html,body');
				$body.animate({scrollTop:$("#tools-b").offset().top},1000);
			}
			// 将浮点数四舍五入，保留小数点后2位
			function toDecimal(x){ 
				var f = parseFloat(x);
				if (isNaN(f)){
					return;
				}
				return Math.round(x*100)/100;
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>