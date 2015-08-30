<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="${ctx}/template/head.jsp" />
<html>
	<body>
		<div id="workframe-wrapper" class="clearfix">
			<div id="location">
    <p>当前位置：借款受理&nbsp;&gt;&gt;&nbsp;借贷需求</p>
  </div>
  <div id="content">
    <h3 class="title"><span>查询条件</span></h3>
    <div class="search-bar">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
              <tr>
                <td class="label">申请商户名称</td>
                <td class="data"><s:textfield id="merchName"/></td>
                <td class="label">商户类型</td>
                <td class="data">
				<s:select id="merchType" list="#{'GOLD_SHOPKEEPER':'金掌柜','UNION_PAY':'银联商户','GENERAL':'普通商户'}" listKey="key" listValue="value"  headerKey="" headerValue="请选择商户类型"></s:select>
				</td>
              </tr>
              <tr>
                <td class="label">借款金额</td>
                <td class="data"><s:textfield id="quota"/>&nbsp;(万元)</td>
                <td class="label">利率</td>
                <td class="data"><s:textfield id="yearRate"/>&nbsp;%</td>
              </tr>
              <tr>
                <td class="label">借款期限</td>
                <td class="data"><s:textfield id="term"/>&nbsp;(个月)</td>
                <td class="label">申请日期</td>
                <td class="data"><s:textfield id="startDate"/>&nbsp;至&nbsp;<s:textfield id="endDate"/></td>
              </tr>
            </table></td>
          <td class="search-btn-td"><input type="button" class="dark-btn"  value="查&nbsp;&nbsp;询" onclick="storeQuery();"/></td>
        </tr>
      </table>
    </div>
				<h3 class="title"><span>查询结果</span>
    </h3>
				<div style="padding:0;margin:10px auto;width:100%">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$("#startDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
	       $("#endDate").datepicker("option","minDate",dateText);
	    }});
		$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
	        $("#startDate").datepicker("option","maxDate",dateText);
	    }});
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
					url:'${ctx}/inst/loanord/bidAccept!list',
					colNames:['申请商户名称','商户类型','借款金额','利率','借款期限','申请日期','失效日期','竞投状态','操作'],
					colModel:[
					    //{name:'idebitBid',index:'idebitBid',align:"center",width:70},
					    {name:'merchName',index:'merchName',align:"center", width:100},
					    {name:'merchType.desc',index:'merchType.desc',align:"center", width:70},
						{name:'quota',index:'quota',align:"center", width:100},
						{name:'yearRate',index:'yearRate',align:"center",width:75},
						{name:'term',index:'term',align:"center",width:100},
						{name:'genTime',index:'genTime',align:"center",formatter:"date",formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},width:140},
						{name:'expireDate',index:'expireDate',align:"center",formatter:"date",formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},width:130},
						{name:'bidStat.desc',index:'bidStat.desc',align:"center",width:100},
						{name:'operate',index:'operate',formatter:optionFormatter,align:"center",width:100}
					],
					rownumbers:true,
					height:230,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"竞投申请列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function storeQuery(){
				if(isNaN($("#yearRate").val())){
					alert("利率请填写数字!");
					return false;
				}
				if(isNaN($("#quota").val())){
					alert("借款金额请填写数字!");
					return false;
				}
				if(isNaN($("#term").val())){
					alert("还款期限请填写数字!");
					return false;
				}
				var merchName = $("#merchName").val();
				var yearRate = $("#yearRate").val();
				var quota = $("#quota").val();
				var term = $("#term").val();
				var merchType =$("#merchType").val();
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/loanord/bidAccept!list?yearRate='+yearRate+'&quota='+quota+'&term='+term+"&merchName="+encodeURI(merchName)+"&merchType="+merchType+"&startDate="+startDate+"&endDate="+endDate+"&debitBidId=",page:1}).trigger("reloadGrid");
			}
			
			function optionFormatterRemainTime(cellvalue, options, rowObject){
				return "待定";
			}
			
			function optionFormatterAcceptStute(cellvalue, options, rowObject){
				return "待定";
			}
			function optionFormatter(cellvalue, options, rowObject){
				var index = options.rowId;
				var command = "return invokeQQ('/inst/loanord/bidAccept'," + index + "," + "'debitBidList'" + ",'');";
				return '[<a href="JavaScript:acceptOrderInfo('+index+');">详情</a>]';
			}
			function acceptOrderInfo(no){
				window.location.href="${ctx}/inst/loanord/bidAccept!acceptOrderInfo?index="+no;
			}

		</script>
		<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
	</body>
</html>