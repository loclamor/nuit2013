package nuitinfo2013

class RatingAlgorithmController {

	final static float MUCH_MORE = 50;
	final static float BIT_MORE = 25;

	def index() {
	}


	def update(Exchange e) {
		User u1 = e.u1;
		User u2 = e.u2;

		Product p1 = u1.currentProduct;
		Product p2 = u2.currentProduct;

		updateRatings(u1, p1, p2);
		updateRatings(u2, p2, p1);
	}


	def updateRatings(User u, Product given, Product received) {
		def res = Rating.withCriteria {
			eq('user', u)
			eq('product', given)
		}
		Rating old = res.get(0);
		
		res = Rating.withCriteria {
			eq('user', u)
			eq('product', received)
		}
		Rating winner = res.get(0);
		
		float diff = Math.abs(old.elo - winner.elo);

		if (winner.elo<=old.elo){
			if (diff >= MUCH_MORE){
				winner.elo=winner.elo+5;
			} else if (diff >= BIT_MORE){
				winner.elo=winner.elo+2.5;
			} else {
			winner.elo=winner.elo+1;
			}
		}else if (winner.elo>old.elo){
			if (diff >= MUCH_MORE){
				// winner gets no elo
			} else if (diff >= BIT_MORE){
				winner.elo=winner.elo+0.5;
			} else {
				winner.elo=winner.elo+1;
			}
		}
	}

	def downgradeRatings(User u, Product refused, Product current) {
		Rating looser = Rating.withCriteria {
			eq('user', u)
			eq('product', refused)
		}

		Rating old = Rating.withCriteria {
			eq('user', u)
			eq('product', current)
		}

		float diff = Math.abs(old.elo - looser.elo);

		if (looser.elo<=old.elo){
			if (diff >= MUCH_MORE){
				// no loss
			} else if (diff >= BIT_MORE){
				looser.elo=looser.elo-0.5;
			} else {
				looser.elo=looser.elo-1;
			}
		}else if (looser.elo>old.elo){
			if (diff >= MUCH_MORE){
				looser.elo=looser.elo-5;
			} else if (diff >= BIT_MORE){
				looser.elo=looser.elo-2.5;
			} else {
				looser.elo=looser.elo-1;
			}
		}
	}
}
