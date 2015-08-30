<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：系统管理&nbsp;&gt;&gt;&nbsp;操作账号管理</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="instMainForm" action="backUserManager" namespace="/backstage/backuser">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >登陆名称</td>
								<td class="data"><s:textfield id="loginName" name="loginName"/>&nbsp;</td>
								<td class="label" >真实姓名</td>
								<td class="data"><s:textfield id="userName" name="userName"/>&nbsp;</td>
							</tr>
							<tr>
								<td class="label">所属角色</td>
								<td class="data">
								<select id="bsRole" name="bsRole">
								<option value="">请选择所属角色</option>
									<s:iterator var="bsRoleList" value="bsRoleList">
										<option value="<s:property value="#bsRoleList.getiBsRole()"/>">
											<s:property value="#bsRoleList.getRoleName()" />
										</option>
									</s:iterator>
							</select>
								</td>
								<td class="label">状态</td>
								<td class="data">
								<select name="stat" id="stat">
								<option value="">请选择</option>
								<option value="USED">启用</option>
								<option value="UNUSED">停用</option>
								</select>
								</td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
										&nbsp;&nbsp;
										<n:HcAuthButton cssClass="dark-btn" value="新增管理员" onclick="toAdd()" authCode="CZZHGL_XZ"/>
									</td>
			        </tr>
			      </table>
			      </s:form>
			    </div>
				<h3 class="title"><span>查询结果</span></h3>
				<div  style="margin:10px auto;width:100%">
							<table id="list"></table>
							<div id="pager"></div>
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
				getList();
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/backuser/backUserManager!list',
					colNames:['操作员ID','登陆名称','所属角色','真实姓名','注册时间','电话号码','邮箱地址','状态','操作选项'],
					colModel:[
						{name:'iuser',index:'iuser',align:"center", width:50},
						{name:'loginName',index:'loginName',align:"center", width:70},
						{name:'bsRoles',index:'bsRoles',align:"center", width:70},
						{name:'userName',index:'userName',align:"center", width:110},
						{name:'genTime',index:'genTime',align:"center", formatoptions:{srcformat:'Y-m-d H:i',newformat:'Y-m-d H:i'},width:110},
						{name:'mobile',index:'mobile',align:"center", width:85},
						{name:'email',index:'email',align:"center", width:85},
						{name:'stat.desc',index:'stat.desc',align:"center", width:85,formatter:operFormatter1},
						{name:'oper',index:'oper',align:"center", width:135, formatter:operFormatter},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"操作账号管理"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var data = rowObject[7];
				if(data =='正常'){
					return '[<n:HcAuthA authCode="CZZHGL_XG" href="JavaScript:modify('+index+');" value="修改"></n:HcAuthA>]&nbsp&nbsp' + 
					'[<n:HcAuthA authCode="CZZHGL_TY" href="JavaScript:jinyong('+index+');" value="禁用"></n:HcAuthA>]&nbsp&nbsp';
				}else{
				  return '[<n:HcAuthA authCode="CZZHGL_QY" href="JavaScript:enable('+index+');" value="启用"></n:HcAuthA>]&nbsp&nbsp';
				}
				
			}
			function operFormatter1(cellvalue, options, rowObject){
				var index = options.rowId;
				var data = rowObject[7];
				if(data =='正常'){
					return data;
				}else{
				  return '<font style="color:red">停用</font>';
				}
				
			}
			
			function modify(index){
				document.instMainForm.action="${ctx}/backstage/backuser/backUserManager!backstageUserEdit?index="+index;	
		        document.instMainForm.submit();
			}
			
			function jinyong(index){
				if(confirm("是否确定要禁用该操作员?")){
					document.instMainForm.action="${ctx}/backstage/backuser/backUserManager!disableBackstageUser?index="+index;	
			        document.instMainForm.submit();
				}
			}
			
			function enable(index){
				if(confirm("是否确定要启用该操作员?")){
					document.instMainForm.action="${ctx}/backstage/backuser/backUserManager!enableBackstageUser?index="+index;	
			        document.instMainForm.submit();
				}
			}
			
			function storeQuery(){
				var loginName = $("#loginName").val();
				var userName = $("#userName").val();
				var stat=$("#stat").val();
				var bsRole=$("#bsRole").val()
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/backuser/backUserManager!list?loginName='+loginName+'&userName='+encodeURI(userName)+'&stat='+stat+'&bsRole='+bsRole,page:1}).trigger("reloadGrid");
			}
			function toAdd(){
				document.instMainForm.action="${ctx}/backstage/backuser/backUserManager!backstageUserAdd";	
		        document.instMainForm.submit();
			}
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
