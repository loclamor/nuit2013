package nuitinfo2013

import grails.plugin.springsecurity.SpringSecurityService;
import nuitinfo2013.User;

class ExchangeController {
	def springSecurityService
	def timeout = 60*3;
	
	def connected=[];
	def busy=[];

	def currentExchange=[];
	
    def index() { }
	
	def getExchange(User connectedUser){
		def exc = null;
		def res = UserManager.findByUser(connectedUser);
		UserManager current = res.get(0);
		
		if (current.getAvailable()){
			User match = algoMatch(connectedUser);
			exc = new Exchange(A: connectedUser,B: match,initial: new Date())
			
			// update states
			current.available = false;
			def res2 = UserManager.findByUser(match);
			UserManager user2 = res2.get(0);
			user2.available = false;
			
			// save
			exc.save();
			current.save();
			user2.save();
		}
		return exc;
		/* check time
        Exchange exc
		if (k!=null){
			exc = currentExchange[k]
			if (new Date().compareTo(exc.initialTime + timeout) < 0)//WARNING to test --> normally thahts OK
				currentExchange.remove(k);
		}*/
	}
	
	def User algoMatch(User connectedUser){
		def exc;
		def res = UserManager.getAll();
		def bestMatch = null;
		def bestElo = -1;
		res.each {
			if (it.getAvailable()){
				def user = it.getUser();
				def product  = user.currentProduct;
				if (product.id != connectedUser.currentProduct.id){
					def elo = Rating.findByUserAndProduct(connectedUser,product).elo;
					if (elo>bestElo) bestMatch=user;
				}
			}
		}
		return bestMatch;
	}
	
	
	def reponse(){
		// Récuperation de l'utilisateur
		boolean isUserOne = false
		User otherUser
		Exchange exchange
		def answeringUser = User.get(springSecurityService.authentication.principal.id)
		// Récuperation de l'échange
		exchange = Exchange.findByFirstUserAndEnded(answeringUser, false)
		if(!exchange) {
			exchange = Exchange.findBySecondUserAndEnded(answeringUser, false)
			// TODO ERROR
			if(!exchange) {
				return false
			}
			else {
				otherUser = exchange.getFirstUser()
				isUserOne = false
			}
		}
		else {
			otherUser = exchange.getSecondUser()
			isUserOne = true
		}
		
		boolean reponse = params?.reponse
		if(isUserOne) {
			exchange.setFirstUserResponse(reponse)
		}
		else {
			exchange.setSecondUserResponse(reponse)
		}
		
	}
	
}
