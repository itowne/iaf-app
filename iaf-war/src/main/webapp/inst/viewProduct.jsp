<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<script language="JavaScript">
   			javascript:window.history.forward(1);
		</script>
</head>
	<body>
			<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：我的产品&nbsp;&gt;&gt;&nbsp;查看新产品</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查看产品详细信息</span></h3>
				<div class="results report">
				<s:form id="storeformDetail" action="">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" class="data-table">
						<tr>
							<td class="label">产品名称</td>
							<td class="data"><s:property value="loanPdt.pdtName"/></td>
							<td class="label">最快放款时间</td>
							<td class="data"><s:property value="loanPdt.creditTerm"/>（天）</td>
						</tr>
						<tr>
							<td class="label">贷款最低金额</td>
							<td class="data"><s:property  value='loanPdt.minQuota'/>（万元）</td>
							<td class="label">贷款最高金额</td>
							<td class="data"><s:property  value='loanPdt.maxQuota'/>（万元）</td>
						</tr>
						<tr>
							<td class="label">贷款最短周期</td>
							<td class="data"><s:property  value='loanPdt.minTerm'/><s:label name="loanPdt.minTermType.desc"/></td>
							<td class="label">贷款最长周期</td>
							<td class="data"><s:property  value='loanPdt.maxTerm'/><s:label name="loanPdt.maxTermType.desc"/></td>
						</tr>
						<tr>
							<td class="label">最低利率</td>
							<td class="data">
							<s:label name="loanPdt.rateType.desc"/>
							<s:property  value='loanPdt.rate'/>（%）起</td>
							<td class="label">放贷受理地区范围</td>
							<td class="data"><s:property value="loanPdt.area"/>
							</td>
						</tr>
						<tr>
							<td class="label">还款方式</td>
							<td class="data" colspan="3"><s:property value="loanPdt.repayment.desc"/>
							</td>
						</tr>
						<tr>
							<td class="label">产品特点简述</td>
							<td class="data" colspan="3"><label name="loanPdt.introduce" rows="1" cols="71" readonly="readonly"><s:property value='loanPdt.introduce'/></label></td>
						</tr>
						<tr>
							<td class="label">产品特点</td>
							<td class="data" colspan="3"><textarea name="loanPdt.feature" disabled="disabled" rows="15" cols="71"><s:property value='loanPdt.feature'/></textarea></td>
						</tr>
						<tr>
							<td class="label">申请条件</td>
							<td class="data" colspan="3"><textarea name="loanPdt.condition" disabled="disabled" rows="15" cols="71"><s:property value='loanPdt.condition'/></textarea></td>
						</tr>
						<tr>
							<td class="label">申请所需材料</td>
							<td class="data" colspan="3"><textarea name="loanPdt.cl" disabled="disabled" rows="15" cols="71"><s:property value='loanPdt.cl'/></textarea></td>
						</tr>
						<tr>
							<td class="label">LOGO大图显示效果</td>
							<td class="data" colspan="3" style="text-align:center;">
      						<img src="/DrawImage.do?id=${tfile.ifile}&type=exp" width="150" height="70"/>&nbsp;&nbsp;<br/>
							</td>
						</tr>
						<tr>
							<td class="label">LOGO小图显示效果</td>
							<td class="data" colspan="3" style="text-align:center;">
      						<img src="/DrawImage.do?id=${pdtFile.ifile}&type=exp" width="45" height="45"/>&nbsp;&nbsp;<br/>
							</td>
						</tr>
					</table>
					<div class="uop">
						<n:instAuthButton value="修&nbsp;&nbsp;改" cssClass="dark-btn" onclick="edit();" authCode="WDCP_XG"/>
						<!--<n:instAuthButton value="删&nbsp;&nbsp;除" cssClass="dark-btn" onclick="deleteProduct();" authCode="WDCP_SC"/>-->
						<s:if test=" 1 == loanPdt.pdtStatus.ordinal()">
							<n:instAuthButton value="下&nbsp;&nbsp;架" cssClass="dark-btn" onclick="doDown();" authCode="WDCP_XJ"/>
						</s:if>
						<s:else>
							<n:instAuthButton value="上&nbsp;&nbsp;架" cssClass="dark-btn" onclick="doUp();" authCode="WDCP_SJ"/>
						</s:else>
						<input type="button" class="dark-btn"  value="取&nbsp;&nbsp;消" onclick="back();"/> 
					</div>
					</s:form>
					<div class="results report">
					
					</div>
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
			
			function edit(){
				document.storeformDetail.action="${ctx}/inst/productPublish!editProduct";	
		        document.storeformDetail.submit();
			}
			
			function deleteProduct(){
				if(confirm("是否确定删除该借贷产品?")){
					document.storeformDetail.action="${ctx}/inst/productPublish!deleteProduct";	
			        document.storeformDetail.submit();
				}
			}
			
			function doUp(){
				if(confirm("是否确定上架该借贷产品?")){
					window.location.href="${ctx}/inst/productPublish!upStoreview";
					//document.storeformDetail.action="${ctx}/inst/productPublish!upStoreview";
					//document.storeformDetail.submit();
				}
			}
			
			function doDown(){
				if(confirm("是否确定下架该借贷产品?")){
					window.location.href="${ctx}/inst/productPublish!downStoreView";
					//document.storeformDetail.action="${ctx}/inst/productPublish!downStoreView";
					//document.storeformDetail.submit();
				}
			}
			
			function back(){
				window.location.href = "${ctx}/inst/productPublish.action";
			}
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>