<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/nl-tags" prefix="n" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
				position:absolute;right:25px;top:5px;
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
						<label>公告标题：</label>
						<input type="hidden" name="noticeCurPage" id="noticeCurPage"/>
						<input type="text" id="noticeTitle" name="noticeTitle" width="35px;" value="<s:property value='noticeTitle'/>"/>
						<input type="button" onclick="javaScript:loanQuery();" class="search-btn" />
							<div class="pager">
							 	第${noticeCurPage+1} 页
								<a href="javascript:upPage(${noticeCurPage});">上一页</a>
								<a href="javascript:downPage(${noticeCurPage},${noticePageAmt});">下一页</a>
								总页数：${noticePageAmt}
							</div>
					</div>
				</s:form>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-grid">
				   <tr>
						<td>
							<s:if test="instNoticeList.size==0">
									<span style="color:red;text-align:center;">记录不存在</span>
							</s:if>
						</td>
					</tr>
					<s:iterator value="instNoticeList" id="inl" status="status">
						<tr class="data-row">
							<td style="text-align:left;color:#6B6B6B;text-indent:15px;font-size: large;">
								*&nbsp;&nbsp;
								<em>
								<a href="/homePage!loanNotifyDetail?iinstNotice=<s:property value='#inl.iinstNotice'/>"><s:property value="#inl.title"/></a></em>
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
       		 $("#noticeCurPage").val(nextPage);
             $("#form1").attr("action","homePage!loanNotifyMore");
	     	 $("#form1").submit();
       };
    }
    //上一页
    function upPage(curPage){
         var nextPage=curPage-1;
         if(curPage==0){
            return alert("当前页已经是首页！");
         }else if(nextPage>=0){
         	 $("#noticeCurPage").val(nextPage);
             $("#form1").attr("action","homePage!loanNotifyMore");
	     	 $("#form1").submit();
         };
    }
    function loanQuery(){
    	$("#form1").attr("action","homePage!loanNotifyMore");
    	$("#form1").submit();
    }
	</script>
	</body>
</html>