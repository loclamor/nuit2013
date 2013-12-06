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

var canClick = false;


$(document).ready(function(){
	$(".clickWrapper").addClass("loading");
	//start a new game
	getNewExchange();
	
	//click listener on products
	$("#tradeGame .clickWrapper").click(function(){
		if( canClick ) {
			canClick = false;
			$("#tradeGame .clickWrapper").addClass("notchecked");
			$(this).removeClass("notchecked")
			$(this).addClass("checked");
			
			var choice = !$(this).parent(".thumbnail").is("#myProduct")
			
			//send request
			$.getJSON("./Exchange/reponse/?reponse="+choice, function( data ){
				if( data.resultCode == "OK" ){
					
				}
				else {
					console.log("Error on ./Exchange/response/?reponse="+choice);
					canClick = true;
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
	$("#myProduct .clickWrapper").removeClass("loading");
	$("#tradeGame .clickWrapper").removeClass("notchecked");
	$("#tradeGame .clickWrapper").removeClass("checked");
	// refresh products content
	$("#myProduct span").html( model.myProduct.name );
	$("#secondProduct span").html( model.yourProduct.name );
	//refresh remaining exchange
	$("#exchangeRemaining span").html( model.remainingExchange )
	
	//reset countDown
	$("#exchangeCountDown").html( "10s" );
	countDown = 10;
	canClick = true;
	
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
			canClick = false;
			//resultCode loading
			$("#secondProduct .clickWrapper").addClass("loading");
			// new exchange
			getNewExchange();
		}
		else {
			console.log("error on ./Exchange/stateExchange/");
			refreshGame();
			canClick = false;
			//resultCode loading
			$("#secondProduct .clickWrapper").addClass("loading");
			// new exchange
			getNewExchange();
		}
	});
}