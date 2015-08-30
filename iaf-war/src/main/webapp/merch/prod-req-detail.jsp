<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易 - 商户管理后台</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/mp-workframe.css"/>
		<style type="text/css">
			.small-title{
				padding:5px 55px;font-size:15px;
			}
			#content .results p{
				line-height:28px;padding-left:54px;
			}
			#xxoo td{
				text-align:left;padding:5px 60px;letter-spacing:3px;
			}
		</style>
	</head>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我要借款&nbsp;&gt;&gt;&nbsp;借款申请</p>
			</div>
			<div id="content">
				<h3 class="title">
					<span>放 贷 产 品 详 情</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="related-menu"><a href="javascript:runQQ('${loanPdt.inst.qqUid}');">在线联系</a></span>
				</h3>
    <div class="results report" style="padding:10px 0 10px 50px;">
					<h3 class="small-title">产 品 名 称：<font style="color:red"><s:property value="loanPdt.pdtName"/></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="related-menu">
						<s:if test="collFlag==true">
							&nbsp;&nbsp;已加入我的关注产品
						</s:if>
						<s:else>
							<a href="javascript:addAttentionPdt();">加入我的关注产品</a>
						</s:else>					
					</span>
					</h3>
					<h3 class="small-title">产 品 简 述 </h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<label name="loanPdt.introduce"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdt.introduce'/></label>
		    		</td>
		    	</tr>
		    </table>
		    
			<h3 class="small-title">产 品 特 点</h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!-- <label name="loanPdt.feature"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdt.feature'/></label> -->
		    		<div>${loanPdt.feature}</div>
		    		</td>
		    	</tr>
		    </table>
		    <h3 class="small-title">产 品 详 情</h3>		
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table" style="margin:10px 30px;">
					<input type="hidden" name="loanPdt.iloanPdt" id="iloanPdt" value="<s:property value='loanPdt.iloanPdt'/>"/>
					<input type="hidden" name="merchName" id="merchName" value="<s:property value='merchName'/>"/>
					<input type="hidden" name="industry" id="industry" value="<s:property value='industry'/>"/>
						<tr>
							<td class="label">借款最低金额</td>
							<td class="data"><s:property  value='loanPdt.minQuota'/>（万元）</td>
							<td class="label">借款最高金额</td>
							<td class="data"><s:property  value='loanPdt.maxQuota'/>（万元）</td>
						</tr>
						<tr>
							<td class="label">借款最短周期</td>
							<td class="data"><s:property  value='loanPdt.minTerm'/><s:label name="loanPdt.minTermType.desc"/></td>
							<td class="label">借款最长周期</td>
							<td class="data"><s:property  value='loanPdt.maxTerm'/><s:label name="loanPdt.maxTermType.desc"/></td>
						</tr>
						<tr>
				    		<td class="label">还款方式</td>
				    		<td class="data"><s:property value="loanPdt.repayment.desc"/></td>
				    		<td class="label">最快放款时间</td>
				    		<td class="data"><s:property value="loanPdt.creditTerm"/>（天）</td>
				    	</tr>
				    	<tr>
				    		<td class="label">借款受理地区</td>
				    		<td class="data"><s:property value="loanPdt.area"/></td>
				    		<td class="label">最低利率</td>
				    		<td class="data">
				    		<s:label name="loanPdt.rateType.desc"/>
				    		<s:property  value='loanPdt.rate'/>（%）起</td>
				    	</tr>
				    	<tr>
				    		<td class="label">产品状态</td>
				    		<td class="data">上架</td>
				    		<td class="label">产品上架时间</td>
				    		<td class="data"><fmt:formatDate value="${loanPdt.genTime}" pattern="yyyy-MM-dd"/></td>
				    	</tr>
    	                <tr>
				    		<td class="label">产品所属机构</td>
				    		<td class="data" style="width:270px">
				    			<a href="${ctx}/merch/merchProdReq!instIntro">
				    				<s:property value="loanPdt.inst.instName"/>
				    			</a>
				    			&nbsp;&nbsp;&nbsp;<span class="related-menu"><a href="javascript:addInst(${loanPdt.iinst})">加入我的关注机构</a></span>
				    		</td>
				    		<td class="label">申请统计</td>
				    		<td class="data">
				    			<s:if test="loanPdt.reqTotal==NULL">0(次)</s:if>
				    			<s:else><s:property value="loanPdt.reqTotal"/>(次)</s:else>
				    		</td>
				    	</tr>
					</table>
			<h3 class="small-title">申 请 条 件</h3>
			<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!--  <label name="loanPdt.condition"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdt.condition'/></label>-->
		    		<div>${loanPdt.condition}</div>
		    		</td>
		    	</tr>
		    </table>

		    <h3 class="small-title">所 需 材 料</h3>
		    <table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin:10px 30px;" id="xxoo">
		    	<tr>
		    		<td class="label">
		    		<!--  <label name="loanPdt.cl"  rows="3" cols="86" readonly="readonly"><s:property value='loanPdt.cl'/></label>-->
		    		<div>${loanPdt.cl }</div>
		    		</td>
		    	</tr>
		    </table>
					<div class="uop">
						<input type="button" class="dark-btn"  value="我要申请" onclick="doReq();"/> 
						<input type="button" class="dark-btn"  value="返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回" onclick="back();"/> 
					</div>
					<br/><br/>
				</div>
			</div>
		</div>
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
			
			function doReq(){
				var iloanPdt = $("#iloanPdt").val();
				var merchName = $("#merchName").val();
				var industry = $("#industry").val();
				if(merchName == "--" || industry == "--"){
					parent.jDialog("补充商户基本信息","${ctx}/merch/merchProdReq!infoCheck?merchName="+merchName+"&industry="+industry+"&index="+iloanPdt,600,450,false);
					//window.location.href="${ctx}/merch/merchProdReq!infoCheck?index="+iloanPdt;
				}else{
					window.location.href="${ctx}/merch/merchProdReq!wantProdReq?index="+iloanPdt;	
				}
			}
			
			function back(){
				window.location.href = "${ctx}/merch/merchProdReq.action";
			}
			
			function addAttentionPdt(){
				if(confirm("确认要添加到我的关注产品?")){
					window.location.href="${ctx}/merch/merchProdReq!addAttentionPdt";
				}
			}
			
			function addInst(id){
				if(confirm("确认要添加到我的关注机构?")){
					window.location.href="${ctx}/merch/merchCollInst!add?&iinst="+id;
				}
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>