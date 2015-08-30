<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/nl-tags" prefix="n" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<n:head styles="portal-base,portal-hp"/>
		<!--[if IE]><script type="text/javascript" src="/resources/js/html5.js"></script><![endif]-->
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="banner" class="iaf-grid" style="height:320px;">
			<div class="content">
				<div class="slider-wrapper theme-light">
					<div class="nivoSlider" id="ads">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/ads/1.jpg" width="1000" height="320" alt="" id="#3379C0"/></a>
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/ads/2.jpg" width="1000" height="320" alt="" id="#ff8400"/></a>
					</div>
				</div>
			</div>
		</div>
		<div id="main">
			<div id="main-list">
				<ul class="list-type">
					<li for="prods" id="prods-tab" class="cur">热门借款产品</li>
					<li for="reqs" id="reqs-tab">最新借款申请</li>
				</ul>
				<s:form id="form1" method="post">
				<div id="prods">
					<div class="main-search">
						<label>金额：</label><input type="text" id="quota" name="quota" class="main-input" value="<s:property value='quota'/>" />&nbsp;元
						<label>周期：</label><input type="text" id="term"  name="term" class="main-input" value="<s:property value='term'/>"  />&nbsp;月
						<label>利率：</label><input type="text" id="rate"  name="rate" class="main-input" value="<s:property value='rate'/>" />&nbsp;%
						<label>地区：</label>
						<s:select list="provMap" name="provinceCode" id="provMap" style="width:65px" headerKey="" headerValue="受理地区"></s:select>
						<select name="region" id="cityCode" style="width:65px"></select>
						<input type="button" onclick="javaScript:loanQuery();" class="search-btn" />
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-grid" style="padding:0 8px;">
						<tfoot>
							<tr>
								<td colspan="2" style="height:70px;text-align:left;text-indent:2em;"><input type="hidden" name="id" id="idProcduct"/><a href="/homePage!askLoan?curPage=0" class="more">查看更多借贷产品&gt;</a></td>
							</tr>
						</tfoot>
						<tbody>
							<s:if test="loanPdtList.size==0">
							<tr class="data-row">
								<td colspan="2" style="color:red;text-align:center;">
									记录不存在
								</td>
							</tr>
							</s:if>
							<s:iterator value="loanPdtList" id="id" status="status">
							<tr class="data-row">
								<td class="brief">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="pic" align="left" style="width:43px;height:43px"><img src="/DrawImage.do?type=exp&id=<s:property value='#id.logoIfile'/>" width="42" height="42" alt=""/></td>
											<td class="feature" style="color:#FF8500;text-align:center;"><s:property value='#id.pdtName'/></td>
										</tr>
										<tr>
											<td class="inst" colspan="2" ><s:property value='#id.inst.instName'/></td>
										</tr>
									</table>
								</td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td style="padding:0 3px 0 8px;font-size:15px;color:#333;font-weight:bold;">
											<s:if test="#id.introduce.length()>20">
											<s:property value='#id.introduce.substring(0,20)'/>...
											</s:if>
											<s:else>
											<s:property value='#id.introduce'/>
											</s:else>					
											</td>
											<td style="padding:6px 3px;font-size:13px;">最快放款</td>
											<td rowspan="2" style="text-align:center;width:78px;"><button class="view-detail" onclick="javaScript:viewLoanProduct(<s:property value='#id.iloanPdt'/>);">查看详情</button></td>
										</tr>
										<tr>
											<td style="padding:0 3px 0 8px;font-size:12px;">
												<span>最高额度：<em class="lowest"><s:property value='#id.maxQuota'/></em>万元</span>
												<span class="mgn-l5">利率：<em class="lowest"><s:property value='#id.rate'/></em>%</span>
												<span class="mgn-l5">贷款期限：<em class="lowest"><s:property value='#id.minTerm'/>-<s:property value='#id.maxTerm'/></em>月</span>
											</td>
											<td style="padding:6px 3px;text-align:center;">
												<em class="lowest" style="font-size:22px;font-family:tahoma;"><s:property value='#id.creditTerm'/></em>&nbsp;天
											</td>
										</tr>
									</table>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				</s:form>
				<s:form id="form2" method="post">
				<div id="reqs" style="display:none;">
					<div class="main-search">
					<!-- placeholder="(万元)" -->
						<label>金额：</label><input type="text" id="quotaBid" name="quotaBid" class="main-input" value="<s:property value='quotaBid'/>" />&nbsp;万
						<label>周期：</label><input type="text" id="termBid"  name="termBid" class="main-input" value="<s:property value='termBid'/>" />&nbsp;月
						<label>利率：</label><input type="text" id="rateBid"  name="rateBid" class="main-input" value="<s:property value='rateBid'/>" />&nbsp;%
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
								<td style="text-align:center;width:80px;"><img src="/DrawImage.do?type=exp&id=<s:property value='#bid.merch.logoIfile'/>" width="50" height="45" alt=""/></td>
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
											<td style="padding:5px 10px;">
												<span>借款期限：&nbsp;<em class="limit"><s:property value='#bid.term'/></em>个月</span>
												<span class="mgn-l15">利率：&nbsp;<em class="rate"><s:property value='#bid.yearRate'/></em>%</span>
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
								<li><a href="javascript:void(0);">&nbsp;<s:property value='#ordId.instName'/>&nbsp;<s:property value='#ordId.merchName'/>&nbsp;<s:property value='#ordId.wangyuanQuota'/>万元</a>&nbsp;<s:property value='#ordId.ordStat.desc'/></li>
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
		$("#prods-tab").click(function (){
			window.location.href='';
		})
		//热门借款产品
		$("#provMap").change(function(){   
			if($("#provMap").val()==""){
				$("#cityCode").empty();                  
	        	$("#cityCode").html('<option value="">受理地区</option>'); 
			}else{
				$.getJSON("/../province",{provinceCode:$(this).val()},
				function(myJSON){
					var myOptions='<option value="">所有地区</option>';
					for(var i=0;i<myJSON.provinceList.length;i++){    
						myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
		             }   
		             $("#cityCode").empty();                  
		             $("#cityCode").html(myOptions);              
		       	});  
			};
	   	});
		$("#provMap").change();
		
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
		$("#provMap").change();
		
		//热门借贷产品列表
		function loanQuery(){
			var quota = $("#quota").val();
			var term = $("#term").val();
			var rate = $("#rate").val();
			
			if(isNaN(quota)){
				alert("金额请填写数字!");
				return false;
			}
			if(quota!='' && quota<=0){
				alert("金额必须大于0!");
				return false;
			}
			if(isNaN(term)){
				alert("周期请填写数字!");
				return false;
			}
			if(term!='' && term<=0){
				alert("周期必须大于0!");
				return false;
			}
			if(isNaN(rate)){
				alert("利率请填写数字!");
				return false;
			}
			if(rate!='' && rate<=0){
				alert("利率必须大于0!");
				return false;
			}
			$("#form1").attr("action","homePage!executeSe.action?bidFlag="+0);
			$("#form1").submit();
		}
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
			$("#form2").attr("action","homePage!executeSe.action?bidFlag="+1);
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
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>