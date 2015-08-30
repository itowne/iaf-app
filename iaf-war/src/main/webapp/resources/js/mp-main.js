function reinitIframe(){
	var iframe = document.getElementById("workframe");
	try{
		var height = iframe.contentWindow.document.body.scrollHeight;
		if(/webkit/.test(navigator.userAgent.toLowerCase())){
			height = $(iframe.contentWindow.document.body).height();
		}
		iframe.height = height;
	}catch(e){}
	iframe=null;
	window.setTimeout(reinitIframe,300);
}
function jDialog(title,url,width,height,scrollable, callback){
	var scrolling = (!scrollable) ? "no" : "yes",id = new Date().getTime();
	var myDialog = document.createElement("div");
	$(myDialog).css({display:"none"}).attr("id",id).html('<iframe src="'+url+'" width="'+width+'" height="'+height+'" scrolling="'+scrolling+'" frameborder="0"></iframe>');
	$("body").append(myDialog);
	$(myDialog).dialog({
		modal: true,
		title: title,
		width: width+30,
		height: height+65,
		close: function(){
			jDialogClose(id);
	    	if ($.isFunction(callback)){
				callback();
			}
	    }
	}).mask("载入中...");
	$(myDialog).find("iframe").bind("load",function(){
		$(myDialog).unmask();
		$(this)[0].contentWindow.document.body.id = id;
	});
}
function jDialogClose(id){
	$("#"+id).dialog("close");
	$("#"+id).dialog("destroy").remove();
}

(function($) {
    /**
     * 操作步骤控件脚本，基于jQuery
     * @Author Fuym
     * @param steps Array<String> 步骤
     * @param stepNo int 当前步骤
     */
    $.fn.flow = function(steps,stepNo){
        $(this).addClass("flowbox");
        var size = steps.length;
        var eachWidth = 100/size - 0.2;
        var $ul = $('<ul class="flow"></ul>');
        for(var i=0; i<size; i++){
            var theStep = i + 1;
            var stepNo_str = theStep + ".&nbsp;";
            var $li = $('<li class="page_item" style="width:'+ eachWidth +'%">'+ stepNo_str + steps[i] +'</li>');
            if(theStep == 1){
                $li.addClass("first");
            }else if(theStep == size){
                $li.addClass("last");
            }
            if(theStep == stepNo-1){
                $li.addClass("before_current");
            }else if(theStep == stepNo){
                if(theStep == size){
                    $li.addClass("current_last");
                }else{
                    $li.addClass("current");
                }
            }
            $ul.append($li);
        }
        $(this).append($ul);
    };
})(jQuery);
