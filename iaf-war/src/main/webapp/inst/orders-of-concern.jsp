<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/nl-tags" prefix="n"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="${ctx}/template/head.jsp" />
<html>

<body>
<div id="workframe-wrapper" class="clearfix">
  <div id="location">
    <p>当前位置：我的关注&nbsp;&gt;&gt;&nbsp;关注的订单</p>
  </div>
  <div id="content">
    <h3 class="title"><span>查询条件</span></h3>
    <div class="search-bar">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="data-table">
              <tr>
                <td class="label">申请商户</td>
                <td class="data"><s:textfield name="merchName" id="merchName"/></td>
                <td class="label">商户类型</td>
                <td class="data">
               		<s:select id="merchType" name="merchType" list="#{'GOLD_SHOPKEEPER':'金掌柜','UNION_PAY':'银联商户','GENERAL':'普通商户'}" listKey="key" listValue="value"  headerKey="" headerValue="请选择商户类型"></s:select>
                </td>
                <td class="label">借款金额</td>
                <td class="data">
					<s:textfield id="quota" name="quota" class="limit"/>&nbsp;(万元)
				</td>
              </tr>
              <tr>
                <td class="label">借款期限</td>
                <td class="data">
                	<s:textfield id="term" name="term" class="limit"/>&nbsp;(个月)
                </td>
				<td class="label">申请日期</td>
                <td class="data" colspan="3">
                	<input type="text" id="startDate" name="startDate" readonly="readonly"/>&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" id="endDate" name="endDate" readonly="readonly"/>
                </td>
              </tr>
            </table>
           </td>
          <td class="search-btn-td"><button class="dark-btn" onclick="storeQuery();">查&nbsp;&nbsp;询</button></td>
        </tr>
      </table>
    </div>
    <h3 class="title"><span>查询结果</span>
      <p class="info">共有<em class="count">&nbsp;&nbsp;</em>条关注记录。</p>
    </h3>
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
				$("#startDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			       $("#endDate").datepicker("option","minDate",dateText);
			    }});
				$("#endDate").datepicker({changeYear:true,changeMonth:true,onSelect:function(dateText,inst){
			        $("#startDate").datepicker("option","maxDate",dateText);
			    }});
			});
			
			$(function(){
				$(window).resize(function(){
					$("#list").setGridWidth($(window).width()*0.95);
				});
			});
			
			function getList(){
				var term = $("#term").val();
				var merchName = $("#merchName").val();
				var merchType = $("#merchType").val();
				var quota = $("#quota").val();
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				
				if("请选择商户类型"==merchType)merchType="";
				jQuery("#list").jqGrid({
					url:'${ctx}/inst/instCollOrd!list?term='+term+'&merchName='+merchName+'&merchType='+merchType+'&quota='+quota+'&startDate='+startDate+'&endDate='+endDate,
					colNames:['申请商户','商户类型','借款金额','借款期限','利率','申请日期','操作'],
					colModel:[
						{name:'merchName',index:'merchName',align:"center", width:70},
						{name:'merchType',index:'merchType',align:"center",formatter:optionFormatter2, width:50},
						{name:'quota',index:'quota',align:"center", width:50},
						{name:'term',index:'term',align:"center", width:50},
						{name:'yearRate',index:'yearRate',align:"center", width:50},
						{name:'genTime',index:'genTime',align:"center", formatter:"date",formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},width:50},
						{name:'option',index:'option',formatter:optionFormatter,align:"center",width:90}
					],
					height:230,
					rowNum:10,
					pager: '#pager',
					repeatitems: false,
					shrinkToFit:true,
					autowidth:true,
					caption:"我关注的订单列表"
				});
				jQuery("#list").jqGrid('navGrid','#pager');
			}
			
			function optionFormatter2(cellvalue, options, rowObject){
				var data = rowObject[1];
				if("GOLD_SHOPKEEPER" == data){
					return "金掌柜";
				}else if("UNION_PAY" == data){
					return "银联商户";
				}else if("GENERAL" == data){
					return "普通商户";
				}
				return data;
			}
			
			function optionFormatter(cellvalue, options, rowObject){
				var id = options.rowId;
				//var id = "'"+rowObject[0]+"'";
				return '[<a href="JavaScript:acceptOrderInfo('+id+');">详情</a>][<n:instAuthA href="javascript:moveInstColl('+id+');" authCode="WDGZ_GZDD_SC" value="撤销" />]';
			}
			
			function acceptOrderInfo(no){
				window.location.href="${ctx}/inst/loanord/bidAccept!acceptOrderInfo?index="+no;
			}
			
			function moveInstColl(no){
				if(confirm("是否确定撤销关注该订单?")){
					window.location.href="${ctx}/inst/instCollOrd!move?index="+no;
				};
			}
			
			function storeQuery(){
				var term = $("#term").val();
				var merchName = $("#merchName").val();
				var merchType = $("#merchType").val();
				var quota = $("#quota").val();
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				
				var r = /^\+?[1-9][0-9]*$/; //正整数
				if(term!="" && !r.test(term)){
					alert("借款期限请填写正整数!");
					return false;
				}
				if(isNaN(quota)){
					alert("借款金额请填写数字!");
					return false;
				}
				
				if("请选择商户类型"==merchType)merchType="";
				$("#list").jqGrid('setGridParam',{url:'${ctx}/inst/instCollOrd!list?term='+term+'&merchName='+encodeURI(merchName)+'&merchType='+merchType+'&quota='+quota+'&startDate='+startDate+'&endDate='+endDate,page:1}).trigger("reloadGrid");
			}
		</script>
<!--[if IE 6]><script type="text/javascript">try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}</script><![endif]-->
</body>
</html>