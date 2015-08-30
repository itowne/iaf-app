<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/mp/meta.jspf" %>
<!doctype html>
<html>
	<head>
		<%@include file="/template/mp/head.jspf" %>
	</head>
	<body id="workframe">
		<div id="wrapper">
			<div id="location">
				<p>当前位置：我的资料&nbsp;&gt;&gt;&nbsp;经营数据报告管理</p>
			</div>
			<div id="content" class="report">
				<h3 class="title"><span>我的经营数据报告</span></h3>
				<div class="search-bar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">经营数据报告生成时间范围</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<input type="radio" name="genTimeRange" class="radio" id="gtr-1"/>&nbsp;<label class="gtr" for="gtr-1">上个月</label>
											<input type="radio" name="genTimeRange" class="radio" id="gtr-2"/>&nbsp;<label class="gtr" for="gtr-2">上3个月</label>
											<input type="radio" name="genTimeRange" class="radio" id="gtr-3"/>&nbsp;<label class="gtr" for="gtr-3">上季度</label>
											<input type="radio" name="genTimeRange" class="radio" id="gtr-4" checked="checked"/>&nbsp;<label class="gtr" for="gtr-4">上半年</label>
										</td>
										<td align="right">
											<span class="related-menu"><a href="javascript:void(0);">查看最新报告</a></span>
											<span class="related-menu"><a href="save-report.html">保存报告</a></span>
											<span class="related-menu"><a href="javascript:void(0);">查看历史报告</a></span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="label">报告统计日期范围</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											【2012年6月】---【2012年12月】
										</td>
										<td align="right">
											<span class="related-menu"><a href="credit-report-secret-set.html">经营数据报告保密设置</a></span>
											<span class="related-menu"><a href="credit-report-ss.html">经营数据报告申诉</a></span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="report-tab-area">
					<ul class="tab-title clearfix">
						<li class="cur" for="basic-info" onClick="location='credit-report.html'">基本资料</li>
						<li onClick="location='credit-report-xc.html'">现场调查情况</li>
						<li onClick="location='credit-report-jy.html'">交易记录</li>
						<li onClick="location='credit-report-fw.html'">服务巡检记录</li>
						<li onClick="location='credit-report-qt.html'">其他资料</li>
					</ul>
					<div class="tab-content">
						<div id="basic-info" style="padding:0 30px;">
							<div style="height:36px;text-align:right;padding-right:20px;">
								<button class="dark-btn">修改</button>
							</div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
								<tr>
									<td class="label">商户名称</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.merchName}</td>
									<td class="label">商户行业类别</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.industry}</td>
								</tr>
								<tr>
									<td class="label">注册地址</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.regAddr}</td>
									<td class="label">机具装机地址</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.posAddr}</td>
								</tr>
								<tr>
									<td class="label">注册时间</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.regTime}</td>
									<td class="label">注册资本</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.regCap}</td>
								</tr>
								<tr>
									<td class="label">营业执照号</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.businlic}</td>
									<td class="label">税务登记号</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.taxReg}</td>
								</tr>
								<tr>
									<td class="label">开业时间</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.openTime}</td>
									<td class="label">注册资本</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.regCap}</td>
								</tr>
								<tr>
									<td class="label">联系人</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.contract}</td>
									<td class="label">联系电话</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.contractTel}</td>
								</tr>
								<tr>
									<td class="label">QQ号</td>
									<td class="data">${IAF_LOGIN_SESSION.merch.qqUid}</td>
									<td class="label">&nbsp;</td>
									<td class="data">&nbsp;</td>
								</tr>
								<tr>
									<td class="label">法定代表人情况</td>
									<td class="data embed" colspan="3">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="label">姓名</td>
												<td class="data">${IAF_LOGIN_SESSION.merchLegalPers.legalPersName}</td>
												<td class="label" style="color:blue;">性别</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.sex}</td>
												<td class="label" style="color:blue;">年龄</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.age}</td>
											</tr>
											<tr>
												<td class="label">身份证号码</td>
												<td class="data">${IAF_LOGIN_SESSION.merchLegalPers.cerdNo}</td>
												<td class="label" style="color:blue;">婚姻状况</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.maritalStatus}</td>
												<td class="label" style="color:blue;">学历</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.eduBackground}</td>
											</tr>
											<tr>
												<td class="label" style="color:blue;">办公电话</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.officePhone}</td>
												<td class="label" style="color:blue;">手机</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.mobiPhone}</td>
												<td class="label" style="color:blue;">家庭住址</td>
												<td class="data" style="color:blue;">${IAF_LOGIN_SESSION.merchLegalPers.faAddr}</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="label">结算银行</td>
									<td class="data">${IAF_LOGIN_SESSION.merchBusiInfo.bank}</td>
									<td class="label">银行编号</td>
									<td class="data">${IAF_LOGIN_SESSION.merchBusiInfo.bankCode}</td>
								</tr>
								<tr>
									<td class="label">贷款资金划入账号</td>
									<td class="data" colspan="3">${IAF_LOGIN_SESSION.merchBusiInfo.accountNo}</td>
								</tr>
								<tr>
									<td class="label">商户性质</td>
									<td class="data" colspan="3">${IAF_LOGIN_SESSION.merchBusiInfo.merchNatrue}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="../resources/js/jquery-min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$(".data-list tr:even").addClass("even");
				$(".data-list tr").hover(function(){
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				$(".dark-btn").hover(function(){
					$(this).addClass("dark-btn-hover");
				},function(){
					$(this).removeClass("dark-btn-hover");
				});
			});
			function newReq(){
				
			}
		</script>
	</body>
</html>