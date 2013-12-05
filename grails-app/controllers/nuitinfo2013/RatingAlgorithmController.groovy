package nuitinfo2013

class RatingAlgorithmController {

    def index() { }
	
	def update(Exchange e) {
		User u1 = e.getPart1().getUser();
		User u2 = e.getPart2().getUser();
		
		Product p1 = e.getPart1().getProduct();
		Product p2 = e.getPart2().getProduct();
		
		updateRatings(u1, p1, p2);
		updateRatings(u2, p2, p1);
	}
	
	
	def updateRatings(User u, Product given, Product received) {
		
	}
	
}
