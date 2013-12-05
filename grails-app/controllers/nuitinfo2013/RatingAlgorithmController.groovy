package nuitinfo2013

class RatingAlgorithmController {

    def index() { }
	
	def update(Exchange e) {
		User u1 = e.p1.u;
		User u2 = e.p2.u;
		
		Product p1 = e.p1.p;
		Product p2 = e.p2.p;
		
		updateRatings(u1, p1, p2);
		updateRatings(u2, p2, p1);
	}
	
	
	def updateRatings(User u, Product given, Product received) {
		Rating old = Rating.withCriteria {
			eq('u', u)
			eq('p', given)
		}
		
		Rating current = Rating.withCriteria {
			eq('u', u)
			eq('p', received)
		}
		
		current.elo = Math.max(old.elo, current.elo)+1;
	}
	
	def downgradeRatings(User u, Product refused, Product current) {
		Rating old = Rating.withCriteria {
			eq('u', u)
			eq('p', refused)
		}
		
		// used for elo
		Rating unchanged = Rating.withCriteria {
			eq('u', u)
			eq('p', current)
		}
		
		old.elo--;
	}
}
