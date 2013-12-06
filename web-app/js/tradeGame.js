$(document).ready(function(){
	
	$("#tradeGame .clickWrapper").click(function(){
		if( !$(this).hasClass("checked") && !$(this).hasClass("notchecked") ) {
			$("#tradeGame .clickWrapper").addClass("notchecked");
			$(this).removeClass("notchecked")
			$(this).addClass("checked");
			
			//TODO : send request
		}
	});
	
});