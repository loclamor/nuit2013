/**
 * Model
 */

var model = {
	myProduct : { name: null, description: null, price: null },
	yourProduct : { name: null, description: null, price: null },
	remainingExchange : null,
	globalCountDown : null
};

var countDownTimeOut;
var countDown;
var getNewExchangeTimeOut;


$(document).ready(function(){
	$(".clickWrapper").addClass("loading");
	//start a new game
	getNewExchange();
	
	//click listener on products
	$("#tradeGame .clickWrapper").click(function(){
		if( !$(this).hasClass("checked") && !$(this).hasClass("notchecked")
				&& !$("#myProduct .clickWrapper").hasClass("loading")
				&& !$("#secondProduct .clickWrapper").hasClass("loading") ) {
			
			$("#tradeGame .clickWrapper").addClass("notchecked");
			$(this).removeClass("notchecked")
			$(this).addClass("checked");
			
			var choice = !$(this).parent(".thumbnail").is("#myProduct")
			
			//send request
			$.getJSON("./Exchange/response/?reponse="+choice, function( data ){
				if( data.resultCode == "OK" ){
					
				}
				else {
					console.log("Error on ./Exchange/response/?reponse="+choice);
				}
			});
		}
		return false;
	});
	
});

/**
 * 
 */
function getNewExchange() {
	$.getJSON("./Exchange/newExchange/", function( data ){
		if ( data.resultCode == "OK") {
			model.myProduct = data.myProduct;
			model.yourProduct = data.yourProduct;
			model.remainingExchange = data.remainingExchange;
			
			refreshGame();
			
			//start time out
			countDownTimeOut = setInterval(function(){
				countDown = countDown - 1;
				$("#exchangeCountDown").html( countDown + "s" );
				if( countDown == 0 ) {
					clearInterval( countDownTimeOut );
					getStateExchange();
				}
			},1000);
		}
		else {
			console.log("getNewExchange KO");
			getNewExchangeTimeOut = setTimeout( function() {
				clearTimeout( getNewExchangeTimeOut );
				getNewExchange();
			}, 5000);
		}
	});
}

/**
 * 
 */
function refreshGame() {
	//remove checked / denied
	$("#secondProduct .clickWrapper").removeClass("loading");
	$("#tradeGame .clickWrapper").removeClass("notchecked");
	$("#tradeGame .clickWrapper").removeClass("checked");
	// refresh products content
	$("#myProduct").html( model.myProduct.name );
	$("#secondProduct").html( model.yourProduct.name );
	//refresh remaining exchange
	$("#exchangeRemaining span").html( model.remainingExchange )
	
	//reset countDown
	$("#exchangeCountDown").html( "10s" );
	countDown = 10;
	
	
}

function getStateExchange() {
	$.getJSON("./Exchange/stateExchange/", function( data ){
		if (data.resultCode == "OK") {
			if( data.status == "validate" ) {
				//switch products
				model.myProduct = model.yourProduct;
			}
			else {
				// ?
			}
			refreshGame();
			//resultCode loading
			$("#secondProduct .clickWrapper").addClass("loading");
			// new exchange
			getNewExchange();
		}
	});
}