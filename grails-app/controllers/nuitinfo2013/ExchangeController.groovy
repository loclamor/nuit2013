package nuitinfo2013

import org.apache.tomcat.jni.Time
import nuitinfo2013.User;

class ExchangeStruct{
	User A;
	Boolean AResponse=null;
	User B
	Boolean BResponse=null;
	Time initial;
}

class ExchangeController {
	static User[] connected;
	static User[] busy;

	static ExchangeStruct[] currentExchange;
	
    def index() { }
	
	def proposeExchange(){
		User u = Sprin
		if (u is in current exchange){
			if (is timout exchange(u))
				re
		}
		
		if (user is in current exchange){
			
		}else{
			co -1
			busy +1
			creer Exchange struct +currentExchange
		}
				
	}
	
	
	def Reponse(){
		
	}
	
}
