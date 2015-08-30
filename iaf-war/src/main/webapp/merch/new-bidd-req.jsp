<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
		<n:head styles="mp-workframe" scripts="jquery,mp-workframe"/>
		<n:ui includes="form"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
<meta charset="utf-8"/>
<title>汇融易 - 商户管理后台</title>
<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
<script language="JavaScript">
   javascript:window.history.forward(1);
</script>
</head>
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;发布新借款</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>发布新借款详细信息</span>
			</h3>
		<div class="results report">
			<s:form action="#" id="storeform" name="storeform" method="POST">
				<table width="80%" border="0" cellspacing="0" cellpadding="0"
					class="data-table">
					<tr>
						<td class="label">借款金额：</td>
						<td class="data"><s:textfield name="debitBid.quota" id="quota" cssClass="validate[required,custom[integer],min[1]]"/>&nbsp;(万元)</td>
					</tr>
					<tr>
						<td class="label">借款期限：</td>
						<td class="data"><s:textfield name="debitBid.term" id="term" cssClass="validate[required,custom[integer],min[1]]"/>
						<select id="termType" name="termType" style="width:120px;" class="validate[required]">
								<option value="">请选择单位</option>
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="label">利率：</td>
						<td class="data">
						<select id="rateType" name="rateType" style="width:120px;" class="validate[required]">
								<option value="">请选择利率类型</option>
								<option value="DAY">日利率</option>
								<option value="MONTH">月利率</option>
								<option value="YEAR">年利率</option>
							</select>
						<s:textfield name="debitBid.yearRate" style="width:60px" id="yearRate" cssClass="validate[required,custom[number2],min[0.01]]" onblur="mRate()"/>&nbsp;%</td>
					</tr>
					<!--  
					<tr>
						<td class="label">月利率：</td>
						<td class="data"><span id="monthRate"></span>%</td>
					</tr>
					-->
					<tr>
						<td class="label">还款方式：</td>
						<td class="data"><select style="float:left;margin-top:20px" name="debitBid.refundType" class="validate[required]" id="repayment"><option value="">请选择还款方式</option>
								<option value="TERM_LOAN">等额分期还款</option>
								<option value="ONE_TIME">一次性还本</option></select>&nbsp;&nbsp;&nbsp;
								<div id="tip" style="color:#ff660a;visibility:hidden;float:left;margin-left:60px"><p>等额分期还款：是指借款的本金和利息分成若干次支付,每次支付相同金额.</p>
								<p>一次性还本:一次归还借款的本金.</p></div>
						</td>
					</tr>
					<tr>
						<td class="label">借款地区：</td>
						<td class="data">
						<s:select list="provMap" name="provinceCode" id="provMap" listKey="key" listValue="value" headerKey="" headerValue="请选择省份" cssClass="validate[required]"></s:select>
						<select name="cityCode" id="cityCode" ></select>
						</td>
					</tr>
					<tr>
						<td class="label">受理机构：</td>
						<td class="data" id="inst">
							<!-- <input type="checkbox" id="checkAll">全选</br> -->
							<s:if test="instList.size()<4">
								<s:iterator var="inst" value="instList" status="status">
								<input type="checkbox" disabled="disabled" onclick="this.checked=!this.checked" class="checkbox"/>&nbsp;&nbsp;
								<input type="hidden" name="inst" value="<s:property value="#inst.getIinst()"/>"/>
								<label for="<s:property value="#inst.getIinst()"/>"><s:property value="#inst.getInstName()"/></label>
							</s:iterator>
							</s:if>
							<s:else>
								<s:iterator var="inst" value="instList" status="status" begin="0" end="3">
								<input type="checkbox" disabled="disabled" onclick="this.checked=!this.checked" class="checkbox"/>&nbsp;&nbsp;
								<input type="hidden" name="inst" value="<s:property value="#inst.getIinst()"/>"/>
								<label for="<s:property value="#inst.getIinst()"/>"><s:property value="#inst.getInstName()"/></label>
							</s:iterator>
							<!--  &nbsp;&nbsp;&nbsp;<a href="javascript:showMoreInst();" id="more-inst-link">显示更多机构</a>-->
							<div id="other-inst" style="display:block;">
							<s:iterator var="inst" value="instList" status="status" begin="4">
								<input type="checkbox" disabled="disabled" onclick="this.checked=!this.checked" class="checkbox"/>&nbsp;&nbsp;
								<input type="hidden" name="inst" value="<s:property value="#inst.getIinst()"/>"/>
								<label for="<s:property value="#inst.getIinst()"/>"><s:property value="#inst.getInstName()"/></label>
								<s:if test="#status.count%4==0">
									<br/>
								</s:if>
							</s:iterator>
							<!--  &nbsp;&nbsp;&nbsp;<a href="javascript:hideMoreInst();" id="hide-inst-link">收起更多机构</a>-->
							</div>
							</s:else>
							<br/>
							<span><font style="color: #ff660a;">（你的企业资料和经营数据报告将会向你上述所选的申请受理机构公开）</font></span>
						</td>
					</tr>
					<tr>
						<td class="label">借款用途：</td>
						<td><s:textarea rows="5" cols="80" name="debitBid.purpose" id="purpose" cssClass="validate[required],maxSize[200]"></s:textarea>
						<br><font style="color: #ff660a;">(借款用途前18个字符用于平台首页的借款需求信息显示.)</font>
						</td>
					</tr>
					<tr>
						<td class="label" style="width:100px">借款受理失效日期</td>
						<td class="data"><fmt:formatDate value="${debitBid.expireDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
					<td class="data" colspan="2"><input type="checkbox" id="agree">在汇融易平台内, 本人同意公开"经营报告隐私设置"的勾选项,以供机构使用.</td>
					</tr>
				</table>
				<div class="uop">
					<input type="button" id="confirm"  class="dark-btn" value="确 定" disabled="disabled"/>&nbsp;&nbsp;&nbsp;
					<input type="button" class="dark-btn" onclick="cancel()" value="取 消"/>
				</div>
			</s:form>
			</div>
		</div>
		<script type="text/javascript">
		$("#repayment").focus(function(){
			$("#tip").css("visibility","visible");
		});
		$("#repayment").blur(function(){
			$("#tip").css("visibility","hidden");
		});
		
		//$("#confirm").css("color","#7B7B7B");
		$("#confirm").css("background","#7B7B7B");
		$("#agree").click(function (){
			if($("#agree").attr("checked")=="checked"){
				$("#confirm").removeAttr("disabled");
				//$("#confirm").css("background","url('../images/mp/bg-dark-btn.gif') repeat-x scroll 0 0 transparent;");
				$("#confirm").removeAttr("style");
			}else{
				$("#confirm").attr("disabled","disabled");
				//$("#confirm").css("color","#7B7B7B");
				$("#confirm").css("background","#7B7B7B");
			}
		});
		
		function mRate(){
			//var rate = $("#yearRate").val();
			//if(!isNaN(rate)){
				//if(rate!=0&&rate!=""&&rate){
				//	var monthRate = rate/12;
					//$("#monthRate").html(monthRate.toFixed(2));
				//}
			//}
		}		
		
		function showMoreInst(){
			$("#more-inst-link").hide();
			$("#other-inst").show();
			$("#hide-inst-link").show();
		}
		
		function hideMoreInst(){
			$("#other-inst").hide();
			$("#more-inst-link").show();
		}
			$(function() {
				$(".dark-btn").hover(function() {
					$(this).addClass("dark-btn-hover");
				}, function() {
					$(this).removeClass("dark-btn-hover");
				});
				
				$("#provMap").change(function(){  
					if($("#provMap").val()==""){
						$("#cityCode").empty();                  
			        	$("#cityCode").html('<option value="">所有地区</option>'); 
					}else{
						$.getJSON("/../province",{provinceCode:$(this).val()},
								function(myJSON){
									var myOptions="<option value=''>所有地区</option>";                  
									for(var i=0;i<myJSON.provinceList.length;i++){    
										myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
						             }   
						             $("#cityCode").empty();                  
						             $("#cityCode").html(myOptions);              
						       	});      
					}
			   	});
				$("#provMap").change();
				
			});
			$('#inst :checkbox').attr('checked','checked'); 
			/*
			$('#checkAll').click(function(){
				//alert($('#checkAll').attr('checked'));
				if($('#checkAll').attr('checked')=="checked"){
					$(':checkbox').attr('checked','checked'); 
				}else{
					$(':checkbox').removeAttr('checked'); 
				}
			});
			
			$("input[name='inst']").click(function(){
				if($("input[name='inst']:checked").length==$("input[name='inst']").length){
					$('#checkAll').attr('checked',"checked");
				}else{
					$('#checkAll').removeAttr('checked');
				}
			});
			$(function() {
				$("#storeform").validationEngine("attach",{   
					promptPosition:"centerRight"
				});
			});
			*/
			$('#confirm').click(function(){
				if(confirm("确认提交请求?")){
				if (check()) {
					$("#storeform").attr("action", "${ctx}/merch/merchDebitBid!saveDebitBit.action");
					$("#storeform").submit();
				}
				}
			});
			
			function check(){
				if(!$("#storeform").validationEngine("validate")){
					return false;
				}
				
				if($('input:checkbox:checked').length<=0){
					alert("请选择一个机构!");
					return false;
				}
				
				return true;
			}

			function cancel() {
				window.location.href = "${ctx}/merch/merchDebitBid";
			}

			function showMoreInst() {
				$("#more-inst-link").hide();
				$("#other-inst").show();
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>