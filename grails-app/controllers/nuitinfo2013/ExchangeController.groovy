package nuitinfo2013

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService;
import groovy.ui.ConsoleTextEditor.UpdateCaretListener;
import nuitinfo2013.User;

class ExchangeController {
	def ratingAlgorithmService
	def springSecurityService

	def currentExchange=[];

	def index() {
	}

	def getExchange(User connectedUser){
		Exchange exc = null;
		def current = UserManager.findByUser(connectedUser);

		if (current.getAvailable()){
			User match = algoMatch(connectedUser);
			

			if (match != null){
				exc = new Exchange(firstUser: connectedUser,secondUser: match,initialTime: new Date())
				// update states
				current.available = false;
				def user2 = UserManager.findByUser(match);
				user2.available = false;

				// save
				exc.save();
				current.save();
				user2.save();
			}
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
					def elo
					if (Rating.findByUserAndProduct(connectedUser,product) == null){
						new Rating(user:connectedUser,product:product).save()
						elo=0;
					}else {
						elo = Rating.findByUserAndProduct(connectedUser,product).elo;
					}
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
		exchange = Exchange.findByFirstUser(answeringUser)
		if(!exchange) {
			exchange = Exchange.findBySecondUser(answeringUser)
			if(!exchange) {
				render(contentType: "text/json") { resultCode = "KO" }
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
				ratingAlgorithmService.update(exchange)
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
				ratingAlgorithmService.update(exchange)
				//keep same object
				answeringUser.save()
				otherUser.save()
			}

		}

		render(contentType: "text/json") { resultCode = "OK" }
	}
	
	def stateExchange = {
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
		if(!exchange || exchange == null) {
			exchange = Exchange.findBySecondUser(answeringUser)
			if(!exchange || exchange == null) {
				render(contentType: "text/json") { resultCode = "KO" }
				return null;
			}
		}

		def dateTmp = new Date();
		def endTime = exchange.initialTime
		endTime.setSeconds(endTime.getSeconds() + 10)
		if(dateTmp < endTime) {
			sleep((endTime.getTime()-dateTmp.getTime()))
		}
		
		// Send exchange
		ratingAlgorithmService.update(exchange)

		if(exchange.firstUserResponse == true && exchange.secondUserResponse == true) {
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
	
	def newExchange = {
		// Récuperation de l'utilisateur
		boolean isFirstUser = false
		def answeringUser = User.get(springSecurityService.authentication.principal.id)

		UserManager.findByUser(answeringUser).available = true

		Exchange exchange = getExchange(answeringUser)
		if (exchange != null){
			isFirstUser = (exchange.firstUser == answeringUser)

			if(isFirstUser) {
				render(contentType: "text/json") {
					resultCode = "OK"
					myProduct = {
						name = exchange.firstUser.currentProduct.name
						descriptif = exchange.firstUser.currentProduct.name
					}
					yourProduct = {
						name = exchange.secondUser.currentProduct.name
						descriptif = exchange.secondUser.currentProduct.name
					}
				}
			}else {
				render(contentType: "text/json") {
					resultCode = "OK"
					yourProduct = {
						name = exchange.firstUser.currentProduct.name
						descriptif = exchange.firstUser.currentProduct.name
					}
					myProduct = {
						name = exchange.secondUser.currentProduct.name
						descriptif = exchange.secondUser.currentProduct.name
					}
				}
			}
		} else {
		render(contentType: "text/json") {
					resultCode = "KO"}
		}


	}

	def testJson = {
		def statusTmp = "ACCEPTED"
		def remainingExchangeTmp = 42
		render(contentType: "text/json") {
			myProduct = {
				name = statusTmp
				descriptif = statusTmp
			}
			yourProduct = {
				name = remainingExchangeTmp
				descriptif = remainingExchangeTmp
			}
		}
	}
	
	
	def listProducts = {
		
		def user = User.get(springSecurityService.authentication.principal.id)
		def rating = Rating.findAllByUser(user)
		
		rating.sort({ e1, e2 ->
			e2.elo - e1.elo
		})
		
		def products = []
		rating.each{
			products << [name: it.product.name, elo: it.elo]
		}

		def res = [
			resultCode: 'OK',
			products: products	
		]	
		
		render res as JSON
	}
}
