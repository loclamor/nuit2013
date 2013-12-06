/**
 * Model
 */

var model = {
	myProduct : { nom: null, desc: null, prix: null },
	yourProduct : { nom: null, desc: null, prix: null },
	remainingExchange : null,
	globalCountDown : null
};


$(document).ready(function(){
	
	//start a new game
	getNewExchange();
	
	//click listener on products
	$("#tradeGame .clickWrapper").click(function(){
		if( !$(this).hasClass("checked") && !$(this).hasClass("notchecked") ) {
			$("#tradeGame .clickWrapper").addClass("notchecked");
			$(this).removeClass("notchecked")
			$(this).addClass("checked");
			
			//TODO : send request
		}
	});
	
});

/**
 * 
 */
function getNewExchange() {
	$.getJSON("./Exchange/newExchange/", function( data ){
		if (data.state == "OK") {
			model.myProduct = data.myProduct;
			model.yourProduct = data.yourProduct;
			model.remainingExchange = data.remainingExchange;
			
			refreshGame();
		}
	});
}

/**
 * 
 */
function refreshGame() {
	//remove checked / denied
	$("#tradeGame .clickWrapper").removeClass("notchecked");
	$("#tradeGame .clickWrapper").removeClass("checked");
	// refresh products content
	$("#myProduct").html( model.myProduct.nom );
	$("#secondProduct").html( model.yourProduct.nom );
	
	$("#exchangeRemaining span").html( model.remainingExchange )
	
}