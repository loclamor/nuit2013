package nuitinfo2013

import grails.plugin.springsecurity.SpringSecurityService;
import nuitinfo2013.User;

class ExchangeController {
	class ExchangeStruct{
		User A;
		Boolean AResponse=null;
		User B;
		Boolean BResponse=null;
		Date initial;
	}
	def springSecurityService
	def timeout = 60*3;
	
	def connected=[];
	def busy=[];

	def currentExchange=[];
	
    def index() { }
	
	private def isBusyUser(User u){
		for (int i=0; i< currentExchange.length ; i++ ){
			if (currentExchange[i].A == u || currentExchange[i].B == u)
				return i;
		}
		return null;
	}
	
	def proposeExchange(){
		User u = User.get(springSecurityService.authentication.principal.id);
        int k = isBusyUser(u)

        if (k!=null){
            ExchangeStruct exc = currentExchange[i]
			if (new Date().compareTo(exc.initial + timeout) < 0)//WARNING to test --> normally thahts OK
                currentExchange.remove(i);
		}
        k = isBusyUser(u)
		if (k==null){
            int i = Math.floor( Math.random() * connected.length);
			User tmp = connected[i]
            connected.remove(i)
			busy.add(tmp);
            exc = new ExchangeStruct(A: u,B: tmp,initial: new Date())
            currentExchange.add(exc)
		}
	}
	
	
	def Reponse(){
		
	}
	
}
