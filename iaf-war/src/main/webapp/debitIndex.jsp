<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
		<n:head styles="portal-base,portal-hp"/>
		<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
		<script type="text/javascript">
		function keyEvent(event){
			if (event.keyCode == 13){
				event.returnValue = false;  
				} 
		}
		</script>
		<style type="text/css">
		.thumb{
		 bottom: 5px;
   		 position: absolute;
    	 right: 10px;
    	 z-index: 9999;
    	 }
    	 .thumb a{
		  background: none repeat scroll 0 0 #FCF2CF;
    border: 1px solid #FF9415;
    color: #FF9415;
    display: inline;
    float: left;
    height: 16px;
    line-height: 16px;
    margin-bottom: 2px;
    margin-left: 5px;
    padding: 1px;
    text-align: center;
    text-decoration: none;
    width: 16px;
    	 }
     .thumb .cur{
    background: none repeat scroll 0 0 #FF9415;
    border: 1px solid #FF6700;
    color: #FFFFFF;
    font-weight: bold;
	}
		</style>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="banner" class="iaf-grid" style="height:320px;">
			<div class="content">
				<div class="slider-wrapper theme-light">
					<div class="nivoSlider" id="ads">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/ads/1.jpg" width="1000" height="320" alt="" id="#3379C0"/></a>
						<!-- <a href="javascript:void(0);"><img src="${ctx}/resources/images/ads/2.jpg" width="1000" height="320" alt="" id="#ff8400"/></a> -->
					</div>
					<div class="thumb"> 
						<a id="1" class="" onmouseover="ads(1)">1</a>
						<a id="2" class="" onmouseover="ads(2)">2</a>
					</div>
				</div>
			</div>
		</div>
		<div id="main">
			<div id="main-list">
				<ul class="list-type">
					<li for="prods" id="prods-tab" >热门借款产品</li>
					<li for="reqs" id="reqs-tab" class="cur">最新借款申请</li>
				</ul>
				<s:form id="form2" method="post">
				<div id="reqs">
					<div class="main-search" onkeypress="keyEvent(event)">
					<!-- placeholder="(万元)" -->
						<label>金额：</label><input onkeypress="keyEvent(event)" type="text" id="quotaBid" name="quotaBid" class="main-input" style="width:35px" value="<s:property value='quotaBid'/>" />&nbsp;万元
						<label>周期：</label><input onkeypress="keyEvent(event)" style="width:30px;" type="text" id="termBid"  name="termBid" class="main-input" style="width:35px" value="<s:property value='termBid'/>" />
						<select id="debitTerm" name="debitTerm" style="width:40px;height:22px;" >
								<option value="DAY">天</option>
								<option value="MONTH">个月</option>
								<option value="YEAR">年</option>
							</select>
						<label><select id="rateType" name="rateType" style="width:65px;height:22px;" >
								<option value="DAY">日利率</option>
								<option value="MONTH">月利率</option>
								<option value="YEAR">年利率</option>
							</select></label>
						<input onkeypress="keyEvent(event)" type="text" id="rateBid"  name="rateBid" class="main-input" style="width:30px" value="<s:property value='rateBid'/>" />%
						<label>地区：</label>
						<s:select list="provMap" name="provinceCode" id="provMap1" style="width:65px" headerKey="" headerValue="受理地区"></s:select>
						<select name="region" id="cityCode1" style="width:65px"></select>
						<input type="button" onclick="javaScript:bidQuery();" class="search-btn" />
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-grid" style="padding:0 8px;">
						<tfoot>
							<tr>
								<td colspan="2" style="height:70px;text-align:left;text-indent:2em;"><input type="hidden" id="bidId" name="bidId"/><a href="${ctx}/homePage!offerLoan?bidCurPage=0" class="more">查看更多借贷申请&gt;</a></td>
							</tr>
						</tfoot>
						<tbody>
							<s:if test="debitBidList.size==0">
							<tr class="data-row">
								<td colspan="2" style="color:red;text-align:center;">
									记录不存在
								</td>
							</tr>
							</s:if>
							<s:iterator value="debitBidList" id="bid" status="status">
							<tr class="data-row">
								<td style="text-align:center;width:80px;">
								<s:if test='#bid.merch.logoIfile==""||#bid.merch.logoIfile==null'>
								<img src="${ctx}/resources/images/S-JZG.png" width="65" height="65" alt=""/>
								</s:if>
								<s:else>
								<img src="/DrawImage.do?type=exp&id=<s:property value='#bid.merch.logoIfile'/>" width="65" height="65" alt=""/>
								</s:else>
								</td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" id="req-table">
										<tr>
											<td style="padding:5px 10px;font-size:15px;color:#333;font-weight:bold;">
											<s:if test="#bid.purpose.length()>18">
											<s:property value='#bid.purpose.substring(0,18)'/>...
											</s:if>
											<s:else>
											<s:property value='#bid.purpose'/>	
											</s:else>					
											<s:if test='#bid.merchType.toString()=="GOLD_SHOPKEEPER"'><img src="${ctx}/resources/images/jzg.jpg" style="width:65px;height:17px;"/></s:if>
											</td>
											<td>借款金额</td>
											<td rowspan="3" style="text-align:center;width:120px;"><button class="view-detail" onclick="javascript:viewBid('<s:property value="#bid.idebitBid"/>');">查看详情</button></td>
										</tr>
										<tr>
											<td style="padding:5px 7px;">
												<span>借款期限：&nbsp;<em class="limit"><s:property value='#bid.term'/></em><s:label name="#bid.termType.desc"/></span>
												<span class="mgn-l15">
												<s:if test='#bid.rateType.desc!=""'>
										<s:property value="#bid.rateType.desc"/>
									</s:if>
									<s:else>
									利率
									</s:else>
												：&nbsp;<em class="rate"><s:property value='#bid.yearRate'/></em>%</span>
												<span class="mgn-l15">截止：&nbsp;<em class="rest"><s:date name="#bid.expireDate" format="yyyy-MM-dd"/></em></span>
											</td>
											<td style="text-align:center;"><em class="amount" style="font-size:22px;font-family:tahoma;"><s:property value="#bid.wangyuanQuota"/></em>万元</td>
										</tr>
									</table>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				</s:form>
			</div>
			<aside id="sidebar">
				<div id="latest-loan">
					<h3 class="title">借贷实况</h3>
					<div class="list">
						<ul>
						    <s:iterator value="loanOrdList" id="ordId" status="status">
								<li><a href="javascript:void(0);">&nbsp;<s:property value='#ordId.instName'/>&nbsp;<s:property value='#ordId.merchName'/>&nbsp;<s:property value='#ordId.wangyuanQuota'/>万元</a>&nbsp;<font style="color:blue">已成功放款</font><!--<s:property value='#ordId.ordStat.desc'/>--></li>
							</s:iterator>
						</ul>
					</div>
					<a href="/homePage!loanOrdMore?loanOrdCurPage=0&loanOrdCapacity=10" class="more">+更多</a>
				</div>
				<div id="announcement">
					<h3 class="title">新闻公告</h3>
					<ul class="list">
					    <s:iterator value="instNoticeList" id="noticeId" status="status">
							<li><a href="/homePage!loanNotifyDetail?iinstNotice=<s:property value='#noticeId.iinstNotice'/>">&#149;&nbsp;<s:property value='#noticeId.title'/></a></li>
						</s:iterator>
					</ul>
					<a href="/homePage!loanNotifyMore?noticeCurPage=0&noticeCapacity=10" class="more">+更多</a>
				</div>
				<div id="tools">
					<h3 class="title">借贷助手</h3>
					<ul>
						<li id="t1">
							<a href="calculator.jsp">借款利息计算器</a>
							<p>让您能更直观地了解还款本息详情</p>
						</li>
						<li id="t2">
							<a href="/homePage!assessment">贷款评估</a>
							<p>快速生成企业借款额度评估报告</p>
						</li>
					</ul>
				</div>
			</aside>
			<div class="clear"></div>
		</div>
		<%@include file="/template/portal/co-bank.jspf" %>
		<%@include file="/template/portal/footer.jspf" %>
		<script src="/resources/js/jquery.js" type="text/javascript"></script>
		<script src="/resources/ui/jquery.ui.js" type="text/javascript"></script>
		<script src="/resources/js/portal.js" type="text/javascript"></script>
		<script type="text/javascript">
		function ads(id){
			var obj = document.getElementById("#3379C0");
			 var arr = [
				        "${ctx}/resources/images/ads/1.jpg",
				        "${ctx}/resources/images/ads/2.jpg"
				    ];
			if(id==1){
				$("#1").addClass("cur");
				$("#2").removeClass("cur");
				obj.src = arr[0];
			}
			if(id==2){
				$("#2").addClass("cur");
				$("#1").removeClass("cur");
				obj.src = arr[1];
			}
		}
		
		
		var curIndex = 0;
	    //时间间隔 单位毫秒
	    var timeInterval = 3000;
	    var arr = [
	        "${ctx}/resources/images/ads/1.jpg",
	        "${ctx}/resources/images/ads/2.jpg"
	    ];
	    setInterval(changeImg, timeInterval);
	    function changeImg() {
	        var obj = document.getElementById("#3379C0");
	        if (curIndex == arr.length - 1) {
	            curIndex = 0;
	        } else {
	            curIndex += 1;
	        }
	        obj.src = arr[curIndex];
	        if(curIndex==0){
				$("#1").addClass("cur");
				$("#2").removeClass("cur");
			}
			if(curIndex==1){
				$("#2").addClass("cur");
				$("#1").removeClass("cur");
			}
	    }
		
		var rateType='${rateType}';
		$("#rateType option[value='"+rateType+"']").attr("selected","selected");
		
		var debitTerm='${debitTerm}';
		$("#debitTerm option[value='"+debitTerm+"']").attr("selected","selected");
		
		$("#prods-tab").click(function (){
			window.location.href='${ctx}/homePage!loanPdtIndex';
		})
		
		//最新借款产品
				$("#provMap1").change(function(){   
			if($("#provMap1").val()==""){
				$("#cityCode1").empty();                  
	        	$("#cityCode1").html('<option value="">受理地区</option>'); 
			}else{
				$.getJSON("/../province",{provinceCode:$(this).val()},
				function(myJSON){
					var myOptions='<option value="">所有地区</option>';
					for(var i=0;i<myJSON.provinceList.length;i++){    
						myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
		             }   
		             $("#cityCode1").empty();                  
		             $("#cityCode1").html(myOptions);              
		       	});  
			};
	   	});
		$("#provMap1").change();
	
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
			$("#form2").attr("action","homePage!debitSe.action?bidFlag="+1);
			$("#form2").submit();
		}
		//热门借贷产品详细
		function viewLoanProduct(id){
			$("#idProcduct").val(id);
			$("#form1").attr("action","homePage!loanDetail.action");
			$("#form1").submit();
		}
		function viewBid(id){
			$("#bidId").val(id);
			$("#form2").attr("action","homePage!bidDetail.action?");
			$("#form2").submit();
		}
		$(function(){
		    $("#nav-home").addClass("cur");
			if($("#bidFlag").val() =="1"){
			  $("#prods,#reqs").hide();
			  $("#reqs").show();
			  $("#reqs-tab").addClass("cur").siblings().removeClass("cur");
			}
		});
		
		$(function(){
			$("#main-list .list-type li").click(function(){
				if($(this).attr("class") === "cur"){
					return;
				}
				var showID = $(this).attr("for");
				$("#prods,#reqs").hide();
				$(this).addClass("cur").siblings().removeClass("cur");
				$("#"+showID).show();
			});
			$(".search-btn").hover(function(){
				$(this).addClass("search-btn-hover");
			},function(){
				$(this).removeClass("search-btn-hover");
			});
			// marquee
			var marTimer = null,interval = 3000,speed = 1000,marParentID = "latest-loan",stopMar = false;
			function doMarquee(){
				if(stopMar){
					return;
				}
				var marParent = $("#"+marParentID),liNum = marParent.find("li").length;
				if(liNum >= 5){
					var marContainer = marParent.find(".list"),mar = marParent.find("ul"),marH = mar.height();
					marContainer.css({"height":marH,"overflow":"hidden"});
					var firstData = mar.find('li:first'),delta = firstData.height();
					firstData.clone().appendTo(mar);
					firstData.animate({
						marginTop: -delta
					}, speed, function(){
						clearTimeout(marTimer);
						firstData.remove();
						marTimer = setTimeout(doMarquee, interval);
					});
				}
			}
			marTimer = setTimeout(doMarquee,interval);
			$("#latest-loan ul").hover(function(){
				stopMar = true;
				clearTimeout(marTimer);
			},function(){
				stopMar = false;
				marTimer = setTimeout(doMarquee, interval);
			});
		});
		</script>
		<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>