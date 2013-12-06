


$(document).ready(function(){
	
	reload();
	
	window.setInterval(function(){ reload(); }, 2000);
	
});

function reload(){
	
	$.getJSON('./Exchange/listProducts', function(data){
		
		if(data.resultCode == "OK"){
			$('.tableRow').remove();
			$('.tableEmpty').remove();
			
			if(data.products.length > 0){
				for(var i=0;i<data.products.length;i++){
					//alert(data[i])
					$('.table-rank').append('<tr class="tableRow"><td>'+data.products[i].name+'</td><td>'+data.products[i].elo+'</tr>');
				}
			} else {
				$('.table-rank').after('<div class="tableEmpty">Nous n\'avons pas encore de suggestions pour vous');
			}
		}
		
	});
	
}