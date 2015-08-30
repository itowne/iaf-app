function reinitIframe(){
	var iframe = document.getElementById("workframe");
	try{
		var height = iframe.contentWindow.document.body.scrollHeight;
		if(/webkit/.test(navigator.userAgent.toLowerCase())){
			height = $(iframe.contentWindow.document.body).height();
		}
		iframe.height = height - 10;
	}catch(e){}
	iframe=null;
	window.setTimeout(reinitIframe,300);
}
function openDialog(title,url,width,height,scrollable){
	var scrolling = (!scrollable) ? "no" : "yes";
	var myDialog = document.createElement("div"),id = new Date().getTime();
	$(myDialog).attr("id",id).css({display:"none"}).html('<iframe src="'+url+'" width="'+width+'" height="'+height+'" scrolling="'+scrolling+'" frameborder="0"></iframe>');
	$("body").append(myDialog);
	$(myDialog).dialog({
		modal: true,
		title: title,
		width: width+30,
		height: height+65,
		close: function(){
			$(this).dialog("destroy");
		}
	}).mask("载入中...");
	var iframe = $(myDialog).find("iframe")[0];
	$(iframe).bind("load",function(){
		$(iframe.contentWindow.document.body).attr("id",id);
		$(myDialog).unmask();
	});
}
function closeDialog(id){
	$("#"+id).dialog("destroy");
}