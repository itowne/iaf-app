function doPlaceholder(){
	var input = document.createElement("input");
	var htmlSupport = "placeholder" in input;
	input = null;
	if(!htmlSupport){
		$('[placeholder]').focus(function(){
			var input = $(this);
			if (input.val() == input.attr('placeholder')){
				input.val('');
				input.removeClass('placeholder');
			}
		}).blur(function(){
			var input = $(this);
			if(input.val() == '' || input.val() == input.attr('placeholder')){
				input.addClass('placeholder');
				input.val(input.attr('placeholder'));
			}
		}).blur();
		$('[placeholder]').parents('form').submit(function(){
			$(this).find('[placeholder]').each(function(){
				var input = $(this);
				if(input.val() == input.attr('placeholder')){
					input.val('');
				}
			})
		});
	}
}