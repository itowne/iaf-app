<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>汇融易</title>
		<meta name="keywords" content="汇融易,借款,放贷,经营流水,汇卡商务" />
		<meta name="description" content="全国首家只需经营流水即可借贷的投融资服务平台" />
		<n:head styles="mp-workframe,portal-base,portal-hp" scripts="jquery"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/ui/jquery.ui.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/portal-inner.css" />
		<!--[if IE]><script src="resources/js/html5.js" type="text/javascript"></script><![endif]-->
		<style type="text/css">
			.small-title{
				padding:5px 55px;font-size:15px;
			}
			#content .results p{
				line-height:28px;padding-left:54px;
			}
			.main-search .pager{
				position:absolute;right:25px;top:3px;
			}
			.main-search .pager a{
				display:inline-block;padding:2px 5px;border:1px solid #d1d1d1;color:#4a4a4a;
			}
			.main-search .pager a.cur,.main-search .pager a:hover{
				background:#0157ad;color:#fff;
			}
		</style>
	</head>
	<body id="body">
		<%@include file="/template/portal/header.jspf" %>
		<div id="main" class="iaf-grid">
			<div id="prods">
				<s:form id="form1" method="post">
					<div class="main-search">
						<input type="hidden" name="loanOrdCurPage" id="loanOrdCurPage"/>
						<div class="pager">
						 	第${loanOrdCurPage+1} 页
							<a href="javascript:upPage(${loanOrdCurPage});">上一页</a>
							<a href="javascript:downPage(${loanOrdCurPage},${loanOrdPageAmt});">下一页</a>
							总页数：${loanOrdPageAmt}
						</div>
					</div>
				</s:form>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table" align="center">
				<tr>
					<th class="label" style="background: #F1F7FD">放贷机构</th>
					<th class="label" style="background: #F1F7FD">借款商户</th>
					<th class="label" style="background: #F1F7FD">借款金额</th>
					<th class="label" style="background: #F1F7FD">申请结果</th>
					<th class="label" style="width:150px;background: #F1F7FD">放款日期</th>
				</tr>
				   <tr>
						<td colspan="5">
							<s:if test="loanOrdList.size==0">
									<span style="color:red;text-align:center;">记录不存在</span>
							</s:if>
						</td>
					</tr>
					<s:iterator value="loanOrdList" id="ordId" status="status">
						<tr class="data-row">
							<td style="text-align:center;color:#6B6B6B;text-indent:15px;">
								<s:property value='#ordId.instName'/><!--<s:property value='#ordId.ordStat.desc'/>-->
							</td>
							<td style="text-align:center;color:#6B6B6B;text-indent:15px;">
							<s:property value='#ordId.merchName'/>
							</td>
							<td style="text-align:center;color:#6B6B6B;text-indent:15px;">
							<font style="color:red">￥<s:property value='#ordId.wangyuanQuota'/>万元</font>
							</td>
							<td style="text-align:center;color:#6B6B6B;text-indent:15px;">
							已成功放款
							</td>
							<td style="text-align:center;color:#6B6B6B;text-indent:15px;">
							<s:if test='#ordId.creditDate!=null||#ordId.creditDate!=""'>
							<fmt:formatDate value="${ordId.creditDate}" pattern="yyyy-MM-dd"/>
							</s:if>
							<s:else>
							&nbsp;
							</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	<%@include file="/template/portal/co-bank.jspf" %>
	<%@include file="/template/portal/footer.jspf" %>
	<script type="text/javascript">
	//下一页
    function downPage(curPage,pageAmt){
       var nextPage=curPage+1;
       if(pageAmt==nextPage){
            return alert("当前页已经是最后页！");
       }else if(nextPage<=pageAmt){
       		 $("#loanOrdCurPage").val(nextPage);
             $("#form1").attr("action","homePage!loanOrdMore");
	     	 $("#form1").submit();
       };
    }
    //上一页
    function upPage(curPage){
         var nextPage=curPage-1;
         if(curPage==0){
            return alert("当前页已经是首页！");
         }else if(nextPage>=0){
         	 $("#loanOrdCurPage").val(nextPage);
             $("#form1").attr("action","homePage!loanOrdMore");
	     	 $("#form1").submit();
         };
    }
	</script>
	</body>
</html>