<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：商户管理&nbsp;&gt;&gt;&nbsp;商户资料管理</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="merchMainForm" action="merchManager" namespace="/backstage/merchmanager">
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
			              <tr>
								<td class="label" >商户名称:</td>
								<td class="data"><s:textfield id="merchName" name="merchName"/>&nbsp;</td>
								<td class="label" >汇卡商户号:</td>
								<td class="data"><s:textfield id="merchNo" name="merchNo"/>&nbsp;</td>
								<td class="label">商户所属省市:</td>
								<td class="data">
									<s:select list="provMap" name="provinceCode" id="provMap" style="width:90px" listKey="key" listValue="value" headerKey="" headerValue="请选择省份"></s:select>
										<select name="region" id="region" style="width:90px"></select>
								</td>
						</tr>
						<tr>
								<td class="label" >联系人:</td>
								<td class="data"><s:textfield id="contract" name="contract"/>&nbsp;</td>
								<td class="label" >联系电话:</td>
								<td class="data"><s:textfield id="tel" name="tel"/>&nbsp;</td>
								<td class="label">状态:</td>
								<td class="data"><select name="stat" id="stat">
								<option value="">请选择状态</option>
								<option value="1">正常</option>
								<option value="0">禁用</option>
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
		$("#provMap").change(function(){
			if($("#provMap").val()==""){
				$("#region").empty();                  
	        	$("#region").html('<option value="">请选择市区</option>'); 
			}else{
				$.getJSON("/../province",{provinceCode:$(this).val()},
				function(myJSON){
					var myOptions='<option value="">请选择市区</option>';
					for(var i=0;i<myJSON.provinceList.length;i++){    
						myOptions += '<option value="' + myJSON.provinceList[i].code + '">' +myJSON.provinceList[i].name + '</option>';                  
		             }   
		             $("#region").empty();                  
		             $("#region").html(myOptions);              
		       	}); 
			}         
	   	});
		$("#provMap").change();
		
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
					url:'${ctx}/backstage/merchmanager/merchManager!list',
					colNames:['商户名称','汇卡商户号','商户所属省市','联系人','联系电话','状态','操作'],
					colModel:[
						//{name:'imerch',index:'imerch',align:"center", width:50},
						{name:'merchName',index:'merchName',align:"center", width:90},
						{name:'merchNo',index:'merchNo',align:"center", width:90},
						{name:'province',index:'province',align:"center", width:80},
						{name:'contract',index:'contract',align:"center", width:85},
						{name:'contractTel',index:'contractTel',align:"center", width:85},
						{name:'stat',index:'stat',align:"center", width:50,formatter:operFormatter1},
						{name:'oper',index:'oper',align:"center", width:130, formatter:operFormatter},
					],
					rownumbers:true,					
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"商户信息列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			

			function operFormatter1(cellvalue, options, rowObject){
				var stat = rowObject[5];
				if(stat=="1"){
					return "正常";
				}else{
					return "<div style='color:red'>禁用</div>";
				}
			}
			
			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var id = "'"+rowObject[6]+"'";
				return '[<n:HcAuthA authCode="SHGL_SHZLGL_XG" href="JavaScript:modify('+index+');" value="修改"></n:HcAuthA>]&nbsp&nbsp' + 
				'[<a href="JavaScript:userDetail('+id+');">商户操作员</a>]'+'[<n:HcAuthA authCode="SHGL_SHZLGL_XCDC" href="JavaScript:fieldSurvey('+index+');" value="现场调查记录"></n:HcAuthA>]';
			}
			
			function modify(index){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/merchManager!modifymerch?index="+index;	
		        document.merchMainForm.submit();
			}
			
			function fieldSurvey(index){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/merchManager!fieldSurvey?index="+index;	
		        document.merchMainForm.submit();
			}
			
			function userDetail(index){
				document.merchMainForm.action="${ctx}/backstage/merchmanager/merchUser!userDetail?imerch="+index;	
		        document.merchMainForm.submit();
			}
			
			function storeQuery(){
				var merchName = $("#merchName").val();
				var contract = $("#contract").val();
				var merchNo = $("#merchNo").val();
				var tel=$("#tel").val();
				var provinceCode=$("#provMap").val();
				var region=$("#region").val();
				var stat = $("#stat").val();
				if(isNaN(tel)){
					alert("电话请填写数字!");
					return false;
				}
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/merchmanager/merchManager!list?merchName='+encodeURI(merchName)+"&contract="+encodeURI(contract)+"&merchNo="+merchNo+'&tel='+tel+'&provinceCode='+provinceCode+'&region='+region+'&stat='+stat,page:1}).trigger("reloadGrid");
			}

		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
