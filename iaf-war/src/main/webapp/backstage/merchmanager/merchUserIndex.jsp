<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;商户用户</p>
			</div>
			<div id="content">
				<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="merchMainForm" action="merchUser" namespace="/backstage/merchmanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >商户操作员账号</td>
								<td class="data"><s:textfield id="loginName" name="loginName"/>&nbsp;</td>
								<td class="label" >操作员姓名</td>
								<td class="data"><s:textfield id="userName" name="userName"/>&nbsp;</td>
								<td class="label" >状态</td>
								<td class="data"><s:select name="userStat" id="stat" list="@newland.iaf.base.model.dict.ForbidenType@values()" listKey="toString()" listValue="getDesc()" headerKey="" headerValue="请选择状态"></s:select>&nbsp;</td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
										&nbsp;&nbsp;
										<!--<s:submit cssClass="dark-btn" value="新增商户用户" method="merchUserAdd"/>&nbsp;&nbsp;-->
									</td>
			        </tr>
			      </table>
			      </s:form>
			    </div>
				<h3 class="title"><span>商户用户列表</span></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
						</div>
				<div align="center"><input type="button" class="dark-btn"  value="返回" onclick="backQuery();"/> </div>
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
				getList();
			});
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/merchmanager/merchUser!list',
					colNames:['商户操作员账号','操作员姓名','状态','操作选项'],
					colModel:[
						//{name:'imerchUser',index:'imerchUser',align:"center", width:50},
						{name:'loginName',index:'loginName',align:"center", width:90},
						{name:'userName',index:'userName',align:"center", width:110},
						//{name:'genTime',index:'genTime',align:"center",formatter:"date",formatoptions:{srcformat:'Y-m-d H:i',newformat:'Y-m-d H:i'}, width:80},
						//{name:'lastLoginTime',index:'lastLoginTime',formatter:"date",formatoptions:{srcformat:'Y-m-d H:i',newformat:'Y-m-d H:i'},align:"center", width:85},
						{name:'merchStat.desc',index:'merchStat.desc',align:"center", width:110,formatter:operFormatter1},
						{name:'oper',index:'oper',align:"center", width:85, formatter:operFormatter},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"商户用户列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter1(cellvalue, options, rowObject){
				var merchStat = rowObject[2];
				if(merchStat=="禁用"){
					return "<div style='color:red'>禁用</div>";
				}else{
					return merchStat;
				}
			}
			
			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '<a href="javaScript:void(0)" onclick="modify('+index+');">[详情]</a>';
				//var merchStat = rowObject[3];
				//if(merchStat=="禁用"){
					//return '[<n:HcAuthA authCode="SHGL_SHZLGL_QY" href="JavaScript:qiyong('+index+');" value="启用"></n:HcAuthA>]';
				//}else{
					//return '[<n:HcAuthA authCode="SHGL_SHZLGL_XG" href="JavaScript:modify('+index+');" value="修改"></n:HcAuthA>]&nbsp&nbsp' + 
					//'[<n:HcAuthA authCode="SHGL_SHZLGL_JY" href="JavaScript:jinyong('+index+');" value="禁用"></n:HcAuthA>]';
				//}
			}
			
			function modify(index){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/merchUser!merchUserEdit?index="+index;	
		        document.merchMainForm.submit();
			}
			
			function qiyong(index){
				if(confirm("是否确定要启用该商户用户?")){
					document.merchMainForm.action="${ctx}/backstage/merchmanager/merchUser!enablemerchUser?index="+index;	
			        document.merchMainForm.submit();
				}
			}
			
			function jinyong(index){
				if(confirm("是否确定要禁用该商户用户?")){
					document.merchMainForm.action="${ctx}/backstage/merchmanager/merchUser!disablemerchUser?index="+index;	
			        document.merchMainForm.submit();
				}
			}
			
			function backQuery(){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/merchManager";	
		        document.merchMainForm.submit();
			}
			
			function storeQuery(){
				var loginName = $("#loginName").val();
				var userName = $("#userName").val();
				var stat = $("#stat").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/merchmanager/merchUser!list?loginName='+loginName+'&userName='+encodeURI(userName)+"&stat="+stat,page:1}).trigger("reloadGrid");
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
