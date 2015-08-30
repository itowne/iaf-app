<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：机构管理&nbsp;&gt;&gt;&nbsp;机构申请</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="instMainForm" action="instManager" namespace="/backstage/instmanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >机构名称</td>
								<td class="data"><s:select style="width:120px" id="instName" name="instName" list="instApplyReqList"  listValue="instName " listKey="instName" headerKey="" headerValue="请选择机构"></s:select></td>
								<td class="label">机构注册地址</td>
								<td class="data"><s:select list="provMap" name="provinceCode" id="provMap" listKey="key" listValue="value" headerKey="" headerValue="请选择省份"></s:select>
										<select name="region" id="region"></select></td>
								<td class="label" >联系人</td>
								<td class="data"><s:textfield id="contactName" name="contactName"/>&nbsp;</td>
							</tr>
							<tr>
								<td class="label">联系电话</td>
								<td class="data"><s:textfield id="phone" name="phone"/></td>
								<td class="label">注册申请时间</td>
								<td class="data"><s:textfield id="regTime" name="regTime"/></td>
								<td class="label">状态</td>
								<td class="data">
								<select name="stat" id="stat">
								<option value="">请选择状态</option>
								<option value="APPLY">申请中</option>
								<option value="SUCCESS">已处理</option>	
								<option value="REFUSE">退回</option>
								</select></td>
							</tr>
			            </table></td> 
			          <td class="search-btn">
										&nbsp;&nbsp;
										<input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/> 
										&nbsp;&nbsp;
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
		
		$("#regTime").datepicker({changeMonth:true,changeYear:true});
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
				
				$("#provMap").change(function(){
					if($("#provMap").val()==""){
						$("#region").empty();                  
			        	$("#region").html('<option value="">请选择</option>'); 
					}else{
						$.getJSON("/../province",{provinceCode:$(this).val()},
						function(myJSON){
							var myOptions='<option value="">所有地区</option>';
							for(var i=0;i<myJSON.provinceList.length;i++){    
								myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
				             }   
				             $("#region").empty();                  
				             $("#region").html(myOptions);              
				       	}); 
					}         
			   	});
				$("#provMap").change();
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/instmanager/instApplyRequest!list',
					colNames:['机构名称','机构注册地址','联系人','联系电话','注册申请时间','状态','操作'],
					colModel:[
						//{name:'iinstApplyReq',index:'iinstApplyReq',align:"center", width:50},
						{name:'instName',index:'instName',align:"center", width:80},
						{name:'addr',index:'addr',align:"center", width:90},
						{name:'contactName',index:'contactName',align:"center", width:110},
						{name:'mobilePhone',index:'mobilePhone',align:"center", width:70},
						{name:'genTime',index:'genTime',align:"center", width:85},
						{name:'stat.desc',index:'stat.desc',align:"center", width:40},
						{name:'oper',index:'oper',align:"center", width:85, formatter:operFormatter},
					],
					rownumbers:true,
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					shrinkToFit:true,
					autowidth:true,
					caption:"机构信息列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<n:HcAuthA authCode="JGGL_JGSQ_CX" href="JavaScript:detail('+index+');" value="详情"></n:HcAuthA>]&nbsp&nbsp';
				//+ 
				//'[<n:HcAuthA authCode="JGGL_JGSQ_TG" href="JavaScript:check('+index+');" value="通过"></n:HcAuthA>]&nbsp&nbsp'+'[<n:HcAuthA authCode="JGGL_JGSQ_TH" href="JavaScript:cancel('+index+');" value="退回"></n:HcAuthA>]';
			}
			
			function detail(index){
				document.instMainForm.action="${ctx}/backstage/instmanager/instApplyRequest!detail?index="+index;	
		        document.instMainForm.submit();
			}
			
			function check(index){
				if(confirm("确定要执行该通过操作?")){
					if(confirm("是否需要执行通过操作并新增机构，选择【取消】只改变申请信息，选择【确定】将会跳转到新增页面?")){
						document.instMainForm.action="${ctx}/backstage/instmanager/instApplyRequest!checkAndInsert?index="+index;	
				        document.instMainForm.submit();
					}else{
						document.instMainForm.action="${ctx}/backstage/instmanager/instApplyRequest!check?index="+index;	
				        document.instMainForm.submit();
					}
				}
			}
			
			function cancel(index){
				if(confirm("确定要执行该退回操作?")){
					document.instMainForm.action="${ctx}/backstage/instmanager/instApplyRequest!cancel?index="+index;	
			        document.instMainForm.submit();
				}
			}
			
			function storeQuery(){
				var instName = $("#instName").val();
				var contactName = $("#contactName").val();
				var regTime = $("#regTime").val();
				var addr = $("#addr").val();
				var stat = $("#stat").val();
				var phone = $("#phone").val();
				var provinceCode =  $("#provMap").val();
				var region =  $("#region").val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/instmanager/instApplyRequest!list?instName='+encodeURI(instName)+'&contactName='+encodeURI(contactName)+'&regTime='+regTime+'&addr='+encodeURI(addr)+'&stat='+stat+'&phone='+phone+'&provinceCode='+provinceCode+'&region='+encodeURI(region),page:1}).trigger("reloadGrid");
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
