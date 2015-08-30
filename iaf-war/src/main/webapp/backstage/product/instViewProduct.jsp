<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<jsp:include page="${ctx}/template/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<body>
			<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：贷款产品&nbsp;&gt;&gt;&nbsp;产品管理&nbsp;&gt;&gt;&nbsp;产品详情</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查看产品详细信息</span></h3>
				<div class="results report">
				<s:form id="instprodform" action="instProduce" namespace="/backstage/product">
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
						<n:HcAuthButton cssClass="dark-btn"  value="修&nbsp;&nbsp;改" onclick="edit();" authCode="HDCP_XG"/> 
						<n:HcAuthButton  cssClass="dark-btn"  value="删&nbsp;&nbsp;除" onclick="deleteProduct();" authCode="HDCP_SC"/> 
						<s:if test=" 1 == loanPdt.pdtStatus.ordinal()">
							<n:HcAuthButton cssClass="dark-btn"  value="下&nbsp;&nbsp;架" onclick="doDown();" authCode="HDCP_XJ"/> 
						</s:if>
						<s:else>
							<n:HcAuthButton cssClass="dark-btn"  value="上&nbsp;&nbsp;架" onclick="doUp();" authCode="HDCP_SJ"/> 
						</s:else>
						<s:submit cssClass="dark-btn" value="取消" method="instProdInfo"/>
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
				document.instprodform.action="${ctx}/backstage/product/instProduce!editProduct";	
		        document.instprodform.submit();
			}
			
			function deleteProduct(){
				if(confirm("是否确定删除该借贷产品?")){
					document.instprodform.action="${ctx}/backstage/product/instProduce!deleteProduct";	
			        document.instprodform.submit();
				}
			}
			
			function doUp(){
				if(confirm("是否确定上架该借贷产品?")){
					window.location.href="${ctx}/backstage/product/instProduce!upStoreview";
				}
			}
			
			function doDown(){
				if(confirm("是否确定下架该借贷产品?")){
					window.location.href="${ctx}/backstage/product/instProduce!downStoreView";
				}
			}
			
		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>