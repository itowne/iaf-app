<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 机构管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="/resources/css/mp-workframe.css"/>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的首页</p>
			</div>
			<div id="content" class="welcome clearfix">
				<div class="info-box" id="user-info">
					<h3 class="title">机构信息</h3>
					<ul>
						<li>
						<strong><s:property value="#session['IAF_LOGIN_SESSION'].inst.instName" />机构</strong>
						&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].instUsr.loginName" />
						，<s:property value="#session['IAF_LOGIN_SESSION'].amOrPm" />好！</li>
						<li>您的帐号目前状态：正常。</li>
					</ul>
				</div>
				<div class="info-box" id="note">
					<h3 class="title">温馨提醒</h3>
					<ul>
						<li>● 您目前共有<a href="${ctx}/inst/loanord/refundMgr!loanQuery?ordStat=DELIN_QUENCY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.expireRefund"/>笔逾期</a>未收到,应还款额合计￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.curPeriodRefundAmount})}"/>，请及时联系借款人!</li>
						<li>● 目前向您借款的借款人中，共有<s:if test="fcount==0">0</s:if><s:else><a href="${ctx}/inst/loanord/refundMgr!loanQuery?freeze=freeze"><s:property value="fcount"/></a></s:else>个被其他机构冻结清算账户的记录，请留意放贷风险!</li>
						<li>● 至<s:property value="str"/>，您将收到<a href="${ctx}/inst/loanord/refundMgr!loanQuery?ten=ten"><s:property value="tenCount"/>笔还款</a>，应还款额合计￥<s:property value="tenTotal"/>，请留意借贷人的还款情况!</li>
						<li>● 您目前共收到&nbsp;<a href="${ctx}/inst/loanord/prodAccept!loanQueryPage?ordStat=APPLY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAcceptCount"/>笔借款申请</a>未处理,其中<a href="${ctx}/inst/loanord/prodAccept!loanQueryPage?ordStat=APPLY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAcceptCount"/>笔申请未受理</a>，<a href="${ctx}/inst/loanord/prodAudit!loanQueryPage?ordStat=ACCEPT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAuditCount"/>笔申请未审核</a>， <a href="${ctx}/inst/loanord/prodCredit!loanQuery?ordStat=AUDIT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curCreditCount"/>笔申请未放款</a>!</li>
						<!-- 
						<li>您目前共有&nbsp;<a href="${ctx}/inst/loanord/prodAudit!loanQueryPage?ordStat=ACCEPT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curAuditCount"/>笔的借款申请</a>&nbsp;受理审核中，请尽快处理。</li>
						<li>您目前共有&nbsp;<a href="${ctx}/inst/loanord/prodCredit!loanQuery?ordStat=AUDIT"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.curCreditCount"/>笔的借款申请</a>&nbsp;需要放款，请尽快处理。</li>
						<li>您目前共有&nbsp;<a href="${ctx}/inst/loanord/refundMgr!loanQuery?ordStat=DELIN_QUENCY"><s:property value="#session['IAF_LOGIN_SESSION'].statistics.expireRefund"/>笔的借款申请</a>&nbsp;到期需还款，共计：<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.curPeriodRefundAmount})}"/>元，请注意查收。</li>
						<li>本平台共有&nbsp;<a href="${ctx}/inst/loanord/prodAccept!loanQueryPage?pdtType=MERCH_PROD"><s:property value="#session['IAF_LOGIN_SESSION'].debitBidCount"/>个竟投产品信息</a>，请立刻选择产品进行受理。</li> -->
					</ul>
				</div>
				<div class="aside">
					<div class="info-box" id="statistic">
					<s:if test="instRole.isCheck==1">
						<h3 class="title">借贷统计</h3>
						<ul>
							<li>·【借款总额】:<em class="amount">￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.loanAmount})}"/>元</em></li>
							<li>·【还款总额】:<em class="amount">￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.refundAmount})}"/>元</em></li>
							<li>·【欠款总额】:<em class="amount">￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.debtAmount})}"/>元</em></li>
							<li>·【逾期欠款总额】:<em class="amount">￥<s:property value="%{getText('format.money', {#session['IAF_LOGIN_SESSION'].statistics.overDueAmount})}"/>元</em></li>
							<li>·【申请订单数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.loanApplyCount"/>条</em></li>
							<li>·【撤销订单数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.loanCancelCount"/>条</em></li>
							<li>·【受理订单数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.loanAcceptCount"/>条</em></li>
							<li>·【拒绝受理数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.refuseAcceptCount"/>条</em></li>
							<li>·【审核通过数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.loanAuditCount"/>条</em></li>
							<li>·【审核不通过数量】:<em class="amount">&nbsp;&nbsp;<s:property value="#session['IAF_LOGIN_SESSION'].statistics.refuseAuditCount"/>条</em></li>
						</ul>
						</s:if>
						<s:else>
						&nbsp;
						</s:else>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>