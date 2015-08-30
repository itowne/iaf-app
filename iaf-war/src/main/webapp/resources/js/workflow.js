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