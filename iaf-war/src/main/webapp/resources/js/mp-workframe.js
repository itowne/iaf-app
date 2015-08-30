function dialog(title,id,width,height,okMethod,cancelMethod){
	$("#"+id).dialog({
		modal: true,
		title: title,
		width: width+30,
		height: height+65,
		close: function(){
			$(this).dialog("destroy");
		},
		buttons: [{
			text: "确定",
			click: function(){
				if(!okMethod){
					$(this).dialog("destroy");
				}else{
					okMethod($(this));
				}
			}
		},{
			text: "取消",
			click: function(){
				if(!cancelMethod){
					$(this).dialog("destroy");
				}else{
					cancelMethod();
				}
			}
		}]
	});
}
var resetGridFlag = true;
function resetGridWidth(){
	//window.setTimeout(stopResetGridWidth,1500);
	//doResetGridWidth();
}
function stopResetGridWidth(){
	resetGridFlag = false;
}
function doResetGridWidth(){
	if(resetGridFlag){
		if($.browser.msie && $.browser.version == "6.0"){
			return;
		}
		var gridWidth = $("#content").width();
		$("#list").setGridWidth(gridWidth,true);
		window.setTimeout(doResetGridWidth,100);
	}
}