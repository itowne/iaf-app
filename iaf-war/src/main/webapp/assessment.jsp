<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
				<li><a href="/../calculator.jsp" class="calculator"><span>贷款计算器</span></a></li>
				<li class="cur"><a href="/homePage!assessment" class="assessment"><span>借款评估</span></a></li>
			</ul>
		</div>
	</div>
	<div class="content" id="tools-b">
		<div class="iaf-grid clearfix" >
			<div id="assess-form">
				<h3>借贷成功率评估</h3>
				<table id="calculator-table">
					<tr>
						<td class="label">期望借款额度（万元）：</td>
						<td>
							<input type="text" name="loan" id="loan" class="calculator-input"/>
						</td>
					</tr>
					<tr>
						<td class="label">期望借款期限（个月）：</td>
						<td>
							<input type="text" name="term" id="term" class="calculator-input"/>
						</td>
					</tr>
					<tr>
						<td class="label">选择贷款产品：</td>
						<td>
							<s:select style="padding:5px;width:156px" id="prodFactor" list="pdtList"  listValue="pdtName " listKey="iloanPdt " headerKey="" headerValue="请选择"></s:select>
						</td>
					</tr>
					<tr>
						<td class="label">您的月均营业额（万元）：</td>
						<td>
							<input type="text" name="turnover" id="turnover"/>
						</td>
					</tr>
					<tr>
						<td class="label">您的企业所属的行业：</td>
						<td>
						 <s:select list="mccMap" name="mccCode" id="mccMap" listKey="key" listValue="value" headerKey="" headerValue="请选择" style="width:156px;"></s:select>
						<select name="code" id="code" style="width:156px;"></select> 
						<!-- <select id="vocationFactor" style="padding:5px;">
								<option>请选择所属行业大类...</option>
								<option value="1">批发和零售业</option>
								<option value="2">租赁和商务服务业</option>
							</select>
							 -->	
						</td>
					</tr>
					<tr>
						<td class="label">您是否有营业执照？</td>
						<td>
							<input type="radio" name="license" id="license-1" value="0.2"/><label for="license-1">有</label>
							<input type="radio" name="license" id="license-2" value="0"/><label for="license-2">无</label>
						</td>
					</tr>
					<tr>
						<td class="label">您是否有固定经营场所？</td>
						<td>
							<input type="radio" name="place" id="place-1" value="0.2"/><label for="place-1">有</label>
							<input type="radio" name="place" id="place-2" value="0"/><label for="place-2">无</label>
						</td>
					</tr>
					<tr>
						<td class="label">您的户籍？</td>
						<td>
							<input type="radio" name="hukou" id="hukou-1" value="0.2"/><label for="hukou-1">本地户籍</label>
							<input type="radio" name="hukou" id="hukou-2" value="0"/><label for="hukou-2">外地户籍</label>
						</td>
					</tr>
					<tr>
						<td class="label">您是否已开通金掌柜？</td>
						<td>
							<input type="radio" name="goldkeeper" id="goldkeeper-1" value="0.2"/><label for="goldkeeper-1">是</label>
							<input type="radio" name="goldkeeper" id="goldkeeper-2" value="0"/><label for="goldkeeper-2">否</label>
						</td>
					</tr>
					<tr>
						<td class="label">您的企业经营年资：</td>
						<td>
							<input type="radio" name="own" id="own-1" value="0"/><label for="own-1">两年以下</label>
							<input type="radio" name="own" id="own-2" value="0.2"/><label for="own-2">两年或以上</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button id="calculate" onclick="calculate();">&nbsp;</button></td>
					</tr>
				</table>
			</div>
			<div class="superline2 left" style="height:600px;margin:0 10px"></div>
			<span>
				<h3>借款等级说明：</h3>
				<p>企业借款能力采用等级制评估。根据企业经营状况和偿还能力，由高到低划分为：A、B、C级。等级越高，代表您申请本贷款产品的成功率越高。</p>
				<h3 style="color:#f00;">A级:</h3>
				<p>预计您目前的贷款成功率约为:71%-100%之间;</p>
				<h3 style="color:#00f;">B级:</h3>
				<p>预计您目前的贷款成功率约为:41%-70%之间;</p>
				<h3 style="color:#999;">C级:</h3>
				<p>预计您目前的贷款成功率约为:0%-40%之间;</p>
			</span>
		</div>
		<div id="result" class="result" NAME="result" style="display:none;margin-top:15px;border-top:1px solid #0086CA" >
					<h3 style="font-size:16px;">综合评估您的借款能力：</h3>
					<p id="msg" style="text-align: center"></p>
					<span id="arrow">&nbsp;</span>
			</div>
	</div>
</div>
<%@include file="/template/portal/co-bank.jspf" %>
<%@include file="/template/portal/footer.jspf" %>
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/portal.js"></script>
<script type="text/javascript">
$("#mccMap").change(function(){
	if($("#mccMap").val()==""){
		$("#code").empty();                  
    	$("#code").html('<option value="">请选择行业</option>'); 
	}else{
		$.getJSON("/../mcc",{code:$(this).val()},
		function(myJSON){
			var myOptions="";                  
			for(var i=0;i<myJSON.mccList.length;i++){    
				myOptions += '<option value="' + myJSON.mccList[i].id + '">' +myJSON.mccList[i].name + '</option>';                  
             }   
             $("#code").empty();                  
             $("#code").html(myOptions);              
       	}); 
	}         
	});
$("#mccMap").change();

//var first="<option value='' selected='selected'>请选择</option>";
//var str="";
//var data='${loanPdtList}';
//<c:forEach items="${loanPdtList}" var="loanPdt">
	//var id='${loanPdt.iloanPdt}';
	//var name='${loanPdt.pdtName}';
	//str+="<option value='"+id+"'>"+name+"</option>";
//</c:forEach>
//$("#prodFactor").html(first+str);
function calculate(){
	$("#result").hide();
	var reg = /^\d+$/,loan = $("#loan").val();// 贷款额度
	if(!reg.test(loan)){
		alert("请输入合法的借款额度！");
		$("#loan").select().focus();
		return;
	}
	var term = $("#term").val();// 借款期限
	if(!reg.test(term)){
		alert("请输入合法的借款期限！");
		$("#term").select().focus();
		return;
	}
	var prodFactor = $("#prodFactor").val();// 产品系数
	if(isNaN(prodFactor)){
		alert("请选择贷款产品！");
		$("#prodFactor").select().focus();
		return;
	}
	var turnover = $("#turnover").val();// 月营业额
	if(!reg.test(turnover)){
		alert("请输入合法的月营业额！");
		$("#turnover").select().focus();
		return;
	}
	var vocationFactor = $("#vocationFactor").val();// 行业系数
	//if(isNaN(vocationFactor)){
		//alert("请选择行业！");
		//$("#vocationFactor").select().focus();
		//return;
	//}
	var weight = 0;// 权重
	var w_license = $("input[name='license']:checked").val();
	if(!w_license){
		alert("是否有营业执照？");
		return;
	}
	weight += parseFloat(w_license);
	var w_place = $("input[name='place']:checked").val();
	if(!w_place){
		alert("是否有固定经营场所？");
		return;
	}
	weight += parseFloat(w_place);
	var w_hukou = $("input[name='hukou']:checked").val();
	if(!w_hukou){
		alert("您的户籍？");
		return;
	}
	weight += parseFloat(w_hukou);
	var w_goldkeeper = $("input[name='goldkeeper']:checked").val();
	if(!w_goldkeeper){
		alert("是否已开通金掌柜？");
		return;
	}
	weight += parseFloat(w_goldkeeper);
	
	var w_own = $("input[name='own']:checked").val();
	if(!w_own){
		alert("您的企业经营年资？");
		return;
	}
	weight += parseFloat(w_own);
	weight = toDecimal(weight);
	// 贷款成功率=[产品系数*（月营业额*贷款期限）*行业系数*（权重1*条件1+权重2*条件2+。。。权重n*条件n）/（100）]/贷款额度
	//var successRate = (prodFactor*(turnover*term)*vocationFactor*weight)/loan;
	var successRate = (1*(turnover*term)*1*weight)/loan;
	successRate = toDecimal(successRate);
	if(successRate >= 0.71){
		$("#msg").html("<span style='color:red;font-size:30px;font-weight:bold;'>A级</span><br>&nbsp;&nbsp;很高成功率哦，祝您早日申请成功！'");
	}else if(0.4 <= successRate && successRate < 0.71){
		$("#msg").html("<span style='color:red;font-size:30px;font-weight:bold;'>B级</span><br>&nbsp;&nbsp;成功率一般，要多积累经营流水，向更高成功率迈进！'");
	}else{
		$("#msg").html("<span style='color:red;font-size:30px;font-weight:bold;'>C级</span><br>&nbsp;&nbsp;成功率比较低呀，是否有使用金掌柜积累您的经营流水呢？需要更加努力哦！'");
	}
	$("#result").css({"background-color":"#E9F2F4"});
	$("#result").show();
	window.location.hash="result";
}
//将浮点数四舍五入，保留小数点后2位
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