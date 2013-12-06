package nuitinfo2013

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService;
import groovy.ui.ConsoleTextEditor.UpdateCaretListener;
import nuitinfo2013.User;

class ExchangeController {
	def springSecurityService

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
	
	
	def reponse = {
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
				render(contentType: "text/json") {
					resultCode = "KO"
				}
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
		
		if(exchange.firstUserResponse != null && exchange.secondUserResponse != null){
			if(exchange.firstUserResponse != true && exchange.secondUserResponse != true){
				//points
				RatingAlgorithmController r 
				r.update(exchange)
				//exchange products
				Product tmp
				tmp =answeringUser.currentProduct
				answeringUser.currentProduct = otherUser.currentProduct
				otherUser.currentProduct = tmp
				//save
				answeringUser.save()
				otherUser.save()
			}else{
				//update points
				RatingAlgorithmController r
				r.update(exchange)
				//keep same object
				answeringUser.save()
				otherUser.save()
			}
			
		}
		
		render(contentType: "text/json") {
				resultCode = "OK"
		}
	}
	
	def state = {
		// Récuperation de l'utilisateur
		boolean isUserOne = false
		User otherUser
		Exchange exchange
		def answeringUser = User.get(springSecurityService.authentication.principal.id)
		// Wait end timer
		
		
		// Récuperation de l'échange
		String statusTmp
		int remainingExchangeTmp
		exchange = Exchange.findByFirstUser(answeringUser)
		if(!exchange) {
			exchange = Exchange.findBySecondUser(answeringUser)
			if(!exchange) {
				render(contentType: "text/json") {
					resultCode = "KO"
				}
			}
		}
		// Send exchange
		RatingAlgorithmController rating
		rating.update(exchange)		
		
		if(exchange.firstUserResponse() == true && exchange.secondUserResponse() == true) {
			statusTmp = "VALIDATE"
		}
		else {
			statusTmp = "DENIED"
		}
		remainingExchangeTmp = answeringUser.exchangeRemaining
		// TODO REMOVE
		exchange.remove()
		
		render(contentType: "text/json") {
				resultCode = "OK"
				status = statusTmp
				remainingExchange = remainingExchangeTmp
		}
	}
	
	def getNewExchange () {
		// Récuperation de l'utilisateur
		def answeringUser = User.get(springSecurityService.authentication.principal.id)
		
		UserManager.findByUser(answeringUser).available = true
		
		getExchange(answeringUser)
		def newExchange = {
			def myProduct = {
				def name = answeringUser.currentProduct.name
				def descriptif = answeringUser.currentProduct.description
			}
			def yourProduct = {
				def name = answeringUser.currentProduct.name
				def descriptif = answeringUser.currentProduct.description
			}
		}
		
		return newExchange as JSON	
	}
	
	def testJson = {
		def statusTmp = "ACCEPTED"
		def remainingExchangeTmp = 42
		render(contentType: "text/json") {
				resultCode = "OK"
				status = statusTmp
				remainingExchange = remainingExchangeTmp
		}
	}
}
