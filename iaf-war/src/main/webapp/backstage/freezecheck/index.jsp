<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/nl-tags" prefix="n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
				<p>当前位置：冻结管理&nbsp;</p>
			</div>
			<div id="content">
				<h3 class="title"><span>查询条件</span></h3>
				<div class="search-bar">
				<s:form id="form" action="freezeCheck" namespace="backstage">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
									<tr>
										<td class="label">冻结申请机构名称：</td>
										<td class="data"><s:select style="width:120px" id="instName" name="instName" list="instList"  listValue="instName " listKey="iinst " headerKey="" headerValue="请选择机构"></s:select></td>
										<td class="label">申请类型</td>
										<td class="data"><select name="type" id="type">
											<option value="">请选择类型</option>
											<option value="FREEZE">冻结请求</option>
											<option value="THAW">解冻请求</option>
										</select></td>
										<td class="label">被冻结商户名称：</td>
										<td class="data"><input type="text" name="merchName" id="merchName"/></td>
									</tr>
									<tr>
										<td class="label">被冻结商户汇卡商户号：</td>
										<td class="data"><input type="text" name="merchNo" id="merchNo"/></td>
										<td class="label">申请冻结金额(元)：</td>
										<td class="data"><input type="text" name="amount" id="amount"/></td>
										<td class="label">申请日期</td>
										<td class="data">
											<s:textfield style="width:80px" name="beginDate" id="beginDate" ></s:textfield>
											-
											<s:textfield name="endDate" style="width:80px" id="endDate" readonly="true"></s:textfield>
										</td>
									</tr>
									<tr>
										<td class="label">受理日期</td>
										<td class="data">
											<s:textfield style="width:80px" name="acceptbeginDate" id="acceptbeginDate" ></s:textfield>
											-
											<s:textfield name="acceptendDate" style="width:80px" id="acceptendDate" ></s:textfield>
										</td>
										<td class="label">状态</td>
										<td class="data"><s:select list="#request.status" listKey="name()" listValue="desc" id="ordStat" headerKey="" headerValue="请选择" cssClass="data" name="ordStat"></s:select></td>
										<td class="data">&nbsp;</td>
										<td class="data">&nbsp;</td>
									</tr>
								</table>
							</td>
							<td><input type="button" class="dark-btn" value="查询" onclick="storeQuery();"/>
							</td>
							</tr>
					</table>
					</s:form>
				</div>
				<div style="float:right;padding: 0"><n:HcAuthButton cssClass="dark-btn" value="下载冻结请求文件" onclick="dow()" authCode="DJGL_XZ"/></div>
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
							
				$("#beginDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				       $("#endDate").datepicker("option","minDate",dateText);
				    }});
					$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
				        $("#beginDate").datepicker("option","maxDate",dateText);
				    }});
					$("#acceptbeginDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
					       $("#acceptendDate").datepicker("option","minDate",dateText);
					    }});
						$("#acceptendDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
					        $("#acceptbeginDate").datepicker("option","maxDate",dateText);
					    }});
			});
			function maxDateRange(){ 
				var endDate = $("#endDate").val();
				if(endDate!=""){
					mxd = new Date(endDate);
					return { maxDate: mxd };
				}
				return null;
			}
			function minDateRange(){ 
				var startDate = $("#beginDate").val();
				if(startDate!=""){
					mid = new Date(startDate);
					return { minDate: mid };
				}
				return null;
			}
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			function getList(){
				jQuery("#list").jqGrid({
					url:'${ctx}/backstage/freezeCheck!list',
					colNames:['冻结申请机构名称','申请类型','被冻结商户名称','被冻结商户汇卡商户号','申请冻结金额','申请日期','受理日期','状态','操作选项'],
					colModel:[
						{name:'instName',index:'instName',align:"center", width:50},
						{name:'applyType',index:'applyType',align:"center", width:30},
						{name:'merchName',index:'merchName',align:"center", width:70},
						{name:'merchno',index:'merchno',align:"center", width:60},
						{name:'amount',index:'amount',align:"center",formatter:operFormatter1, width:50},
						{name:'date',index:'date',align:"center", width:60},
						{name:'acceptdate',index:'acceptdate',align:"center", width:60},
						{name:'stat',index:'stat',align:"center", width:40,id:'stat'},
						{name:'oper',index:'oper',align:"center", width:45, formatter:operFormatter},
					],
					height:240,
					rowNum:10,
					pager: '#pager',
					repeatitems: false, 
					reload:true,
					shrinkToFit:true,
					autowidth:true,
					caption:"请求列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}

			function operFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				return '[<a href="JavaScript:proc('+index+');">详情</a>]&nbsp&nbsp';
			}
			
			function operFormatter1(cellvalue, options, rowObject){
				var data = rowObject[4];
				return data+'元';
			}
			function proc(index){
				parent.jDialog("冻结明细","${ctx}/backstage/freezeCheck!viewDetail.action?method:viewDetail&index=" + index,860,600,false,function(){
					jQuery("#list").trigger('reloadGrid');
				});
			}
			function download(rowId){
				window.location = "${ctx}/backstage/freezeCheck!downloadOrd?index=" + rowId;
			}

			function dow(){
				document.form.action="${ctx}/backstage/freezeCheck!download";	
		        document.form.submit();
			}
			function storeQuery(){
				var merchName = $("#merchName").val();
				var instName = $("#instName").val();
				var beginDate=$('#beginDate').val();
				var endDate=$('#endDate').val();
				var merchNo=$("#merchNo").val();
				var amount=$("#amount").val();
				var acceptbeginDate=$('#acceptbeginDate').val();
				var acceptendDate=$('#acceptendDate').val();
				var type=$("#type").val();
				var ordStat =$("#ordStat").val(); 
				$("#list").jqGrid('setGridParam',{url:'${ctx}/backstage/freezeCheck!list?merchName='+encodeURI(merchName)+'&instName='+encodeURI(instName)+'&endDate='+endDate+"&beginDate="+beginDate+'&merchNo='+merchNo+'&amount='+amount+'&acceptbeginDate='+acceptbeginDate+'&acceptendDate='+acceptendDate+'&type='+type+'&ordStat='+ordStat,page:1}).trigger("reloadGrid");
			}
		</script>
		
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
