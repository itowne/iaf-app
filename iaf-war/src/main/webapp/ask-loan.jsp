<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<n:head styles="portal-base,portal-hp" scripts="jquery,portal"/>
<script type="text/javascript" src="${ctx}/resources/ui/jquery.ui.js"></script>
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
.main-search .pager{
	position:absolute;right:25px;top:12px;height:20px;line-height:20px;
}
.main-search .pager a{
	padding:2px 5px;border:1px solid #d1d1d1;color:#4a4a4a;
}
.main-search .pager a.cur,.main-search .pager a:hover{
	background:#0157ad;color:#fff;
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
				<img src="${ctx}/resources/images/portal/bg-ask-steps.jpg" width="978" height="50" alt="" />
			</div>
			<p class="tip2">
				您还未注册？<a href="merchReg.jsp">轻松注册</a>
			</p>
		</div>
		<div id="gold-keeper">
			<img src="resources/images/portal/gold-keeper.jpg" width="1000" height="225" alt="" usemap="#gold-keeper" />
			<map name="gold-keeper">
				<area shape="rect" coords="904,13,989,43" href="javaScript:addHicard();" />
			</map>
		</div>
		<div id="hicardDiv" style="display:none">
			<br><br>
			<div style="text-align: center;font-size: x-large;">信用即财富,流水可贷款。</div><br>
			<div>尊敬的客户:</div><br>
			<div>&nbsp;&nbsp;&nbsp;&nbsp;如若您想安装金掌柜,请致电<font style="color:red">400-628-6928</font>申请开通。</div><br>
		</div>
	</div>
	<input type="hidden"  id="bidFlag" value="<s:property value='bidFlag'/>">
				<s:form id="form1" method="post">
				<div id="prods">
				<div style="padding-left:22px;padding-top: 10px"><label><font style="font-size: 18px;font-weight: bold;">热门借款产品</font></label></div>
					<div class="main-search">
						<label>金额：</label><input type="text" id="quota" name="quota" class="main-input" value="<s:property value='quota'/>" />&nbsp;万元
						<label>周期：</label><input type="text" id="term" style="width:30px;" name="term" class="main-input" value="<s:property value='term'/>" />
						<select id="pdtTerm" name="pdtTerm" style="width:50px;height:21px;" >
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
						<input type="text" id="rate"  name="rate" class="main-input" value="<s:property value='rate'/>" />&nbsp;%
						<label>地区：</label>
						<s:select list="provMap" name="provinceCode" id="provMap" style="width:100px" headerKey="" headerValue="受理地区"></s:select>
						<select name="region" id="region" style="width:100px"></select>
						<!--<s:select id="region" name="region" list="#request.provinceMap" listKey="key" listValue="value" headerKey="NONE" headerValue="所有地区" style="width:120px;"></s:select> -->
						<input type="button" onclick="javaScript:loanQuery();" class="search-btn" />
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-grid">
					    <tr>
							<td colspan="4">
							    <input type="hidden" name="curPage" id="curPage"/>
							    <input  type="hidden" name="id" id="idProcduct"/>
								<s:if test="loanPdtList.size==0">
										<span style="color:red;text-align:center;line-height:88px;">记录不存在</span>
								</s:if>
							</td>
						</tr>
					   <s:iterator value="loanPdtList" id="id" status="status"> 
						<tr class="data-row">
							<td class="" style="width:302px;height: 88px;padding-left: 30px">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td rowspan="2" class="" style="width:150px"><img src="/DrawImage.do?type=exp&id=<s:property value='#id.logoIfile'/>" width="150" height="70" alt=""/></td>
										<td class="" style="color:#FF8500;font-weight: bold;text-align: center;"><s:property value='#id.pdtName'/></td>
									</tr>
									<tr>
										<td class="" style="text-align: center"><s:property value='#id.inst.instName'/></td>
									</tr>
								</table>
							</td>
							<td colspan="4">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="3" class="label1" style="height:28px;text-align:left;font-size:15px;color:#333;font-weight:bold;">
										<s:if test="#id.introduce.length()>19">
										<s:property value='#id.introduce.substring(0,19)'/>...
										</s:if>
										<s:else>
										<s:property value='#id.introduce'/>
										</s:else>
										</td>
									</tr>
									<tr>
										<td class="label1" style="text-align:left;" width="21%">最高额度：&nbsp;<em class="lowest"><s:property value='#id.maxQuota'/></em>&nbsp;万元</td>
										<td class="label1" style="text-align:left;" width="20%">
											<s:if test='#id.rateType.desc!=""'>
												最低<s:property value="#id.rateType.desc"/>
											</s:if>
											<s:else>
											利率
											</s:else>
										：&nbsp;<em class="lowest"><s:property value='#id.rate'/></em>&nbsp;%起</td>
										<td class="label1" style="text-align:left;"  width="20%">贷款期限：&nbsp;<em class="lowest"><s:property value='#id.minTerm'/><s:label name="#id.minTermType.desc"/>-<s:property value='#id.maxTerm'/><s:label name="#id.maxTermType.desc"/></em></td>
									</tr>
								</table>
							</td>
							<td class="label1" style="text-align:left;" >最快放款<br/><br/>&nbsp;&nbsp;<em class="lowest" style="font-size:22px;font-family:tahoma;"><s:property value='#id.creditTerm'/></em>&nbsp;天</td>
							<td width="120"><button class="view-detail" onclick="javaScript:viewLoanProduct(<s:property value='#id.iloanPdt'/>);">查看详情</button></td>
									
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="pager" align="center" style="font-size:15px">
						 	第  <font style="color:red">${curPage+1}</font> 页
							<a href="javascript:upPage(${curPage});">上一页</a>
							<a href="javascript:downPage(${curPage},${pageAmt});">下一页</a>
							总页数：${pageAmt}
						</div>
				</s:form>
	<%@include file="/template/portal/footer.jspf" %>
	<script type="text/javascript">
	var rateType='${rateType}';
	$("#rateType option[value='"+rateType+"']").attr("selected","selected");
	
	var pdtTerm='${pdtTerm}';
	$("#pdtTerm option[value='"+pdtTerm+"']").attr("selected","selected");
	
	$(function() {
		$("#provMap").change(function(){
			if($("#provMap").val()==""){
				$("#region").empty();                  
	        	$("#region").html('<option value="">受理地区</option>'); 
			}else{
				$.getJSON("province",{provinceCode:$(this).val()},
				function(myJSON){
					var myOptions='<option value="">所有地区</option>';                
					for(var i=0;i<myJSON.provinceList.length;i++){    
						myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
		             }   
		             $("#region").empty();                  
		             $("#region").html(myOptions);              
		       	}); 
			};
	   	});
		$("#provMap").change();
	});
		    //下一页
		    function downPage(curPage,pageAmt){
		       var nextPage=curPage+1;
		       if(pageAmt==nextPage){
		            return alert("当前页已经是最后页！");
		       }else if(nextPage<=pageAmt){
		       		 $("#curPage").val(nextPage);
		             $("#form1").attr("action","homePage!askLoanSe");
			     	 $("#form1").submit();
		       };
		    }
		    //上一页
		    function upPage(curPage){
		         var nextPage=curPage-1;
		         if(curPage==0){
		            return alert("当前页已经是首页！");
		         }else if(nextPage>=0){
		         	 $("#curPage").val(nextPage);
		             $("#form1").attr("action","homePage!askLoanSe");
			     	 $("#form1").submit();
		         };
		    }
			$(function(){
			 	$("#askLoan").addClass("cur");
				$("#nav li").hover(function(){
					if($(this).hasClass("cur")){
						return;
					}
					$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
				});
				doPlaceholder();
				$(".data-row").hover(function(){
					$(this).addClass("data-row-hover");
				},function(){
					$(this).removeClass("data-row-hover");
				});
				$(".search-btn").hover(function(){
					$(this).addClass("search-btn-hover");
				},function(){
					$(this).removeClass("search-btn-hover");
				});
			});
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
				$("#form1").attr("action","homePage!askLoanSe");
				$("#form1").submit();
			};
			//热门借贷产品详细
			function viewLoanProduct(id){
				$("#idProcduct").val(id);
				$("#form1").attr("action","homePage!loanDetailForMenu.action");
				$("#form1").submit();
				//window.location.href="${ctx}/homePage!detail.action?id="+id;
			};
			function addHicard(){
				$("#hicardDiv").dialog({
					modal: true,
					title: "加入金掌柜",
					width: 500,
					height: 300,
					close: function(){
						$(this).dialog("destroy");
					},
					buttons: [{
						text: "关闭",
						click: function(){
							$(this).dialog("destroy");
						}
					}]
				});
			};
		</script>
		<script type="text/javascript" src="http://kf.5251.net/jsp_admin/float_adjustable.jsp?companyId=39771&style=81311&keyword=1&auto=1&locate=cn"></script>
	<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>