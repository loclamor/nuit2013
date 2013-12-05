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
	
	private def isBusyUser(User u){
		for (int i=0; i< currentExchange.size() ; i++ ){
			if (currentExchange[i].A == u || currentExchange[i].B == u)
				return i;
		}
		return null;
	}
	
	def proposeExchange(){
		User connectedUser = User.get(springSecurityService.authentication.principal.id);
        int k = isBusyUser(connectedUser)

            if (k!=null){
            Exchange exc = currentExchange[i]
			if (new Date().compareTo(exc.initial + timeout) < 0)//WARNING to test --> normally thahts OK
                currentExchange.remove(k);
		}
        k = isBusyUser(connectedUser)
		if (k==null){
            int i = Math.floor( Math.random() * connected.length);
			// user to select
            User tmp = connected[i]
            connected.remove(i)
			busy.add(tmp);
            exc = new Exchange(A: connectedUser,B: tmp,initial: new Date())
            currentExchange.add(exc)
		}
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
