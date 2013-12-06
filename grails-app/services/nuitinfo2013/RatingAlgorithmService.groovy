package nuitinfo2013

class RatingAlgorithmService {

	final static float MUCH_MORE = 50;
	final static float BIT_MORE = 25;

	def index() {
	}


	def update(Exchange e) {
		if (e != null){
			User u1 = e.firstUser;
			User u2 = e.secondUser;
	
			Product p1 = u1.currentProduct;
			Product p2 = u2.currentProduct;
			
			if (e.firstUserResponse == true){
				updateRatings(u1, p1, p2);
			}else{
				downgradeRatings(u1,p2,p1)
			}
			
			if (e.secondUserResponse == true){
				updateRatings(u2, p2, p1);
			}else{
				downgradeRatings(u2,p1,p2)
			}
		}
		
	}


	private def updateRatings(User u, Product given, Product received) {
		def old  = Rating.findByUserAndProduct(u,given);
		def winner = Rating.findByUserAndProduct(u,recieved);
		
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

	private def downgradeRatings(User u, Product refused, Product current) {
		Rating looser = Rating.findByUserAndProduct(u,refused);

		Rating old = Rating.findByUserAndProduct(u,current);

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
