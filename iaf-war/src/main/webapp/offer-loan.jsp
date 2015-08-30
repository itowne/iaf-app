<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>

<html>
<head>
<n:head styles="portal-base,portal-hp" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
<style type="text/css">
#prods .data-grid td.label1,#reqs #req-table td {
	padding: 5px 0;
}

#reqs .rate {
	padding-top: 10px;
}

#prods .brief {
	padding: 0 30px;
}

#prods .data-grid td.btn-td {
	width: 150px;
}

.main-search .pager {
	position: absolute;
	right: 25px;
	top: 12px;
	height:20px;line-height:20px;
}

.main-search .pager a {
	padding: 2px 5px;
	border: 1px solid #d1d1d1;
	color: #4a4a4a;
}

.main-search .pager a.cur,.main-search .pager a:hover {
	background: #0157ad;
	color: #fff;
}
</style>
<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
</head>
<body id="body">
	<%@include file="/template/portal/header.jspf"%>
	<div id="main" class="iaf-grid">
		<div id="tips">
			<p class="tip1">欢迎您来到汇融易，汇融易是专门为中小企业信用提供借款的服务平台，4步轻松助您提前实现梦想</p>
			<div style="height:50px;overflow:hidden;">
				<img src="resources/images/portal/bg-offer-steps.jpg" width="978" height="50" alt="" />
			</div>
			<p class="tip2">
				您还未注册？<a href="instReg!toInstReg">轻松注册</a>
			</p>
		</div>
		<div id="about-offer">
			<img src="resources/images/portal/service-advantage.jpg" width="1000" height="175" alt="" />
		</div>
	</div>
	<input type="hidden" id="bidFlag" value="<s:property value='bidFlag'/>">
	<s:form id="form2" method="post">
		<div id="prods">
			<div style="padding-left:22px;padding-top: 10px"><label><font style="font-size: 18px;font-weight: bold;">最新借款申请</font></label></div>
			<div class="main-search">
				<label>金额：</label><input type="text" id="quotaBid" name="quotaBid" class="main-input" value="<s:property value='quotaBid'/>"  />&nbsp;万元
				<label>周期：</label><input type="text" id="termBid" style="width:30px;" name="termBid" class="main-input" value="<s:property value='termBid'/>"  />
				<select id="debitTerm" name="debitTerm" style="width:40px;height:22px;" >
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
				<label>利率：</label>
				<select id="rateType" name="rateType" style="width:120px;" >
								<option value="">请选择利率类型</option>
								<option value="DAY">日利率</option>
								<option value="MONTH">月利率</option>
								<option value="YEAR">年利率</option>
							</select>
				<input type="text" id="rateBid" name="rateBid" class="main-input" value="<s:property value='rateBid'/>" />&nbsp;% 
				<label>地区：</label>
						<s:select list="provMap" name="provinceCode" id="provMap" style="width:100px" headerKey="" headerValue="受理地区"></s:select>
						<select name="region" id="region" style="width:100px"></select>
				<input type="button" onclick="return bidQuery();" class="search-btn" /> 
			</div>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-grid">
				 <tr>
					<td colspan="4">
						<input type="hidden" name="bidCurPage" id="bidCurPage"/>
						<input type="hidden"  id="bidId" name="bidId" >
						<s:if test="debitBidList.size==0">
								<span style="color:red;text-align:center;line-height:88px;">记录不存在</span>
						</s:if>
					</td>
				</tr>
				<s:iterator value="debitBidList" id="bid" status="status">
					<tr class="data-row">
						<td style="text-align:center;width:180px;">
						<s:if test='#bid.merch.debitIFile==""||#bid.merch.debitIFile==null'>
						<img src="${ctx}/resources/images/B-JZG.png" width="150" height="70" alt=""/>
						</s:if>
						<s:else>
						<img src="/DrawImage.do?type=exp&id=<s:property value='#bid.merch.debitIFile'/>" width="150" height="70" alt=""/>
						</s:else>
						</td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="req-table">
								<tr>
									<td class="label1" colspan="3" style="height:30px;:15px;font-size:15px;color:#333;font-weight:bold;text-align:left;">
									<s:if test="#bid.purpose.length()>30">
									<s:property value='#bid.purpose.substring(0,29)'/>...
									</s:if>
									<s:else>
									<s:property value='#bid.purpose'/>
									</s:else>
									<s:if test='#bid.merchType.toString()=="GOLD_SHOPKEEPER"'><img src="${ctx}/resources/images/jzg.jpg" style="width:65px;height:17px;"/></s:if>
									</td>
								</tr>
								<tr>
									<td style="text-align:left;" width="150px;">借款期限：&nbsp;<em class="lowest"><s:property value='#bid.term' />
									</em><s:label name="#bid.termType.desc"/></td>
									<td style="text-align:left;" width="150px;">
									<s:if test='#bid.rateType.desc!=""'>
										<s:property value="#bid.rateType.desc"/>
									</s:if>
									<s:else>
									利率
									</s:else>
									：&nbsp;<em class="lowest"><s:property value='#bid.yearRate' />
									</em>%
									</td>
									<td style="text-align:left;" width="130px;">截止：&nbsp;<em class="lowest"><s:date name="#bid.expireDate" format="yyyy-MM-dd" />
									</em>
									</td>
								</tr>
							</table>
						</td>
						<td width="15%" style="text-align:center;text-indent:10px;">
							借款金额<br/><br/>&nbsp;&nbsp;&nbsp;<em class="lowest" style="font-size:22px;font-family:tahoma;"><s:property value="#bid.wangyuanQuota"/></em>&nbsp;万元
						</td>
						<td><button class="view-detail" onclick="javascript:viewBid('<s:property value="#bid.idebitBid"/>');">查看详情</button>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="pager" align="center" style="font-size:15px">
				         第  <font style="color:red">${bidCurPage+1}</font> 页
					<a href="javascript:upPage(${bidCurPage});">上一页</a>
					<a href="javascript:downPage(${bidCurPage},${bidPageAmt});">下一页</a>
					总页数：${bidPageAmt}
				</div>
	</s:form>
	<%@include file="/template/portal/footer.jspf"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/portal.js" type="text/javascript"></script>
	<script type="text/javascript">
	var rateType='${rateType}';
	$("#rateType option[value='"+rateType+"']").attr("selected","selected");
	
	var debitTerm='${debitTerm}';
	$("#debitTerm option[value='"+debitTerm+"']").attr("selected","selected");
	
	$(function() {
		$("#provMap").change(function(){
			if($("#provMap").val()==""){
				$("#region").empty();                  
	        	$("#region").html('<option value="">受理地区</option>'); 
			}else{
				$.getJSON("province",{provinceCode:$(this).val()},
				function(myJSON){
					var myOptions="";                  
					for(var i=0;i<myJSON.provinceList.length;i++){    
						myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
		             }   
		             $("#region").empty();                  
		             $("#region").html(myOptions);              
		       	});  
			}
	   	});
		$("#provMap").change();
	});
	
			 //下一页
		    function downPage(bidCurPage,bidPageAmt){
		       var nextPage=bidCurPage+1;
		       if(bidPageAmt==bidCurPage+1){
		            return alert("当前页已经是最后页！");
		       }else if(nextPage<=bidPageAmt){
		       		 $("#bidCurPage").val(nextPage);
		             $("#form2").attr("action","homePage!offerLoanSe");
			     	 $("#form2").submit();
		       }
		    }
		    //上一页
		    function upPage(bidCurPage){
		         var nextPage=bidCurPage-1;
		         if(bidCurPage==0){
		            return alert("当前页已经是首页！");
		         }else if(nextPage>=0){
		         	 $("#bidCurPage").val(nextPage);
		             $("#form2").attr("action","homePage!offerLoanSe");
			     	 $("#form2").submit();
		         }
		    }
		$(function() {
			$("#offerLoan").addClass("cur");
			$("#nav li").hover(function() {
				if ($(this).hasClass("cur")) {
					return;
				}
				$(this).addClass("hover");
			}, function() {
				$(this).removeClass("hover");
			});
			doPlaceholder();
			$(".data-row").hover(function() {
				$(this).addClass("data-row-hover");
			}, function() {
				$(this).removeClass("data-row-hover");
			});
			$(".search-btn").hover(function() {
				$(this).addClass("search-btn-hover");
			}, function() {
				$(this).removeClass("search-btn-hover");
			});
		});
		//获取最新贷款申请列表
		function bidQuery(){
			var quotaBid = $("#quotaBid").val();
			var termBid = $("#termBid").val();
			var rateBid = $("#rateBid").val();
			
			if(isNaN(quotaBid)){
				alert("金额请填写数字!");
				return false;
			}
			if(quotaBid!='' && quotaBid<=0){
				alert("金额必须大于0!");
				return false;
			}
			if(isNaN(termBid)){
				alert("周期请填写数字!");
				return false;
			}
			if(termBid!='' && termBid<=0){
				alert("周期必须大于0!");
				return false;
			}
			if(isNaN(rateBid)){
				alert("利率请填写数字!");
				return false;
			}
			if(rateBid!='' && rateBid<=0){
				alert("利率必须大于0!");
				return false;
			}
			$("#form2").attr("action","homePage!offerLoanSe");
			$("#form2").submit();
			return false;
		}
		//最新贷款申请详细

		function viewBid(id){
			$("#bidId").val(id);
			$("#form2").attr("action","homePage!bidDetail.action?");
			$("#form2").submit();
		}
	</script>
	<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>