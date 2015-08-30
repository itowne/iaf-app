<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<n:head styles="mp-workframe" />
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
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的首页</p>
			</div>
			<div id="content" class="welcome clearfix">
				<div class="info-box" id="user-info">
					<h3 class="title">用户信息</h3>
					<ul>
						<li><strong><s:property value="#session['IAF_LOGIN_SESSION'].merch.merchName" />商户</strong>&nbsp;&nbsp;&nbsp;&nbsp;
						<s:property value="#session['IAF_LOGIN_SESSION'].merchUser.loginName" />，
						<s:property value="#session['IAF_LOGIN_SESSION'].amOrPm" />好！</li>
						<li>您的帐号目前状态：正常。</li>
						<li>
						<s:if test="merch.originalTime==null">
							如您成为金掌柜用户,则借款的成功率则大大增加.详情请致电<font style="color:red">400-628-6928</font>申请开通。
						</s:if>
						<s:else>
							<s:if test="merchUsedMonth!=0">
								您已使用金掌柜<s:property value="%{merchUsedMonth}"/>个月,<a href="${ctx}/merch/assessment" target="_blank">可以计算一下自己能够贷多少钱喔！</a>
							</s:if>
							<s:else>
								<s:if test="merchUsedDay!=0">
								您已使用金掌柜<s:property value="%{merchUsedDay}"/>天,<a href="${ctx}/merch/assessment" target="_blank">可以计算一下自己能够贷多少钱喔！</a>
								</s:if>
								<s:else>
								您今天刚加入金掌柜,<a href="${ctx}/merch/assessment" target="_blank">可以计算一下自己能够贷多少钱喔！</a>
								</s:else>
								
							</s:else>
						</s:else>
						</li>
					</ul>
				</div>
				<div class="info-box" id="note">
					<h3 class="title">温馨提醒</h3>
					<ul>
						<!--  <li>您共有&nbsp;&nbsp;<a href="${ctx}/merch/merchMyRefund!loanQuery.action?ordStat=DELIN_QUENCY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.expireRefund" />笔借款</a>即将到期但未还，合计应还款额<em>￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.curPeriodRefundAmount})}" />元</em>，请尽快处理。</li>
						<li>您共有&nbsp;&nbsp;<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=APPLY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAcceptCount" />笔的借款申情</a>&nbsp;&nbsp;受理中。</li>
						<li>您共有&nbsp;&nbsp;<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=ACCEPT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAuditCount" />笔的借款申情</a>&nbsp;&nbsp;审核中。</li>
						<li>您共有&nbsp;&nbsp;<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=AUDIT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curCreditCount" />笔的借款申情</a>&nbsp;&nbsp;审核通过, 等待机构放款。</li>
						<li>您共有&nbsp;&nbsp;<a href="${ctx}/merch/merchMyRefund!loanQuery.action?ordStat=REFUND"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curRefundingCount" />笔的借款申情</a>&nbsp;&nbsp;正在还款中。</li>
						<li>您共发布了&nbsp;&nbsp;<a href="${ctx}/merch/merchDebitBid"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.merchDebidBitCount" />笔的竟投产品</a>。</li> -->
						<s:if test="count!=0">
						<li>● 由于您<a href="${ctx}/merch/merchMyRefund!loanQuery.action?flag=freeze"><s:property value="count"/>笔逾期欠款</a>,机构已经申请冻结您的金掌柜清算资金，请您尽快联系放贷机构还款，以便解冻清算资金！
						</li>
						</s:if>
						<li>● 您目前共有<a href="${ctx}/merch/merchMyRefund!loanQuery.action?debit=debit"><s:property value="debtCount"/>笔逾期欠款未还</a>，应还款合计￥<s:property value="debtTotal"/>元，逾期还款将会影响您的信用记录，请尽快还款.</li>
						<li>● 至<s:property value="str"/>，您共有<s:if test="tenCount==0">0笔借款</s:if><s:else><a href="${ctx}/merch/merchMyRefund!loanQuery.action?ten=ten"><s:property value="tenCount"/>笔借款</a></s:else>即将到期，应还款额合计￥<s:property value="tenTotal"/>元，请注意及时进行还款.</li>
						<li>● 您共发布<s:property value="#session['IAF_LOGIN_SESSION'].statistics.merchDebidBitCount" />条借款需求信息,共有<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=APPLY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAcceptCount" />笔借款申请中</a>，<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=AUDIT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curCreditCount" />笔等待放款</a>，<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=ACCEPT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAuditCount" />笔等待审核</a>，<a href="${ctx}/merch/merchMyReq!loanQuery.action?ordStat=APPLY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAcceptCount" />笔等待受理</a>，<a href="${ctx}/merch/merchMyRefund!loanQuery.action?ordStat=REFUND"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curRefundingCount" />笔借款正在还款</a>!</li>
						<li>● 本平台共有&nbsp;&nbsp;<a href="${ctx}/merch/merchProdReq.action"><s:property value="#session['IAF_LOGIN_SESSION'].loanPdtCount" />个借贷产品</a>，请根据您的需要选择适合您的借贷产品.</li> 
					</ul>
				</div>
				<div class="aside">
					<div class="ads">
					<div class="slider-wrapper theme-light">
					<div class="nivoSlider" id="ads">
						<a href="javascript:void(0);"><img src="${ctx}/resources/images/merchAds1.jpg" width="320" height="167" alt="" id="#3379C0"/></a>
						<!-- <a href="javascript:void(0);"><img src="${ctx}/resources/images/ads/2.jpg" width="1000" height="320" alt="" id="#ff8400"/></a>-->
					</div>
					<!-- 
					<div class="thumb"> 
						<a id="1" class="" onmouseover="ads(1)">1</a>
						<a id="2" class="" onmouseover="ads(2)">2</a>
						<a id="3" class="" onmouseover="ads(3)">3</a>
						</div>
						 -->
					</div>
					</div>
					<div class="info-box" id="important">
						<h3 class="title">公告</h3>
						<ul>
							<s:iterator value="instNoticeList" id="noticeId" status="status">
							<li><a href="JavaScript:showInfo(<s:property value='#noticeId.iinstNotice'/>);">&#149;&nbsp;<s:property value='#noticeId.title'/></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<script src="/resources/js/jquery.js" type="text/javascript"></script>
		<script src="/resources/ui/jquery.ui.js" type="text/javascript"></script>
		<script src="/resources/js/portal.js" type="text/javascript"></script>
		<script type="text/javascript">
		function ads(id){
			var obj = document.getElementById("#3379C0");
			 var arr = [
				        "${ctx}/resources/images/merchAds1.jpg",
				        "${ctx}/resources/images/merchAds2.jpg",
				        "${ctx}/resources/images/merchAds3.jpg"
				    ];
			if(id==1){
				$("#1").addClass("cur");
				$("#2").removeClass("cur");
				$("#3").removeClass("cur");
				obj.src = arr[0];
			}
			if(id==2){
				$("#2").addClass("cur");
				$("#1").removeClass("cur");
				$("#3").removeClass("cur");
				obj.src = arr[1];
			}
			if(id==3){
				$("#3").addClass("cur");
				$("#1").removeClass("cur");
				$("#2").removeClass("cur");
				obj.src = arr[2];
			}
		}
		
		 var curIndex = 0;
		    //时间间隔 单位毫秒
		    var timeInterval = 3000;
		    var arr = [
				        "${ctx}/resources/images/merchAds1.jpg",
				        "${ctx}/resources/images/merchAds2.jpg",
				        "${ctx}/resources/images/merchAds3.jpg"
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
					$("#3").removeClass("cur");
				}
				if(curIndex==1){
					$("#2").addClass("cur");
					$("#1").removeClass("cur");
					$("#3").removeClass("cur");
				}
				if(curIndex==2){
					$("#3").addClass("cur");
					$("#1").removeClass("cur");
					$("#2").removeClass("cur");
				}
		    }
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
		    
			function showInfo(id){
				parent.jDialog("公告信息","${ctx}/merch/welcome!getNoticeDetail?iinstNotice="+id,800,480,true);
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>