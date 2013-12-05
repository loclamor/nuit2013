package nuitinfo2013

import org.apache.tomcat.jni.Time

class ExchangeStruct{
	User A;
	boolean AResponse;
	User B
	boolean BResponse;
	Time initial;
}

class ExchangeController {
	static User[] connected;
	static User[] busy;

	static ExchangeStruct[] currentExchange;
	
    def index() {
		if (user isnot in current exchange){
			
		}
	}
	
	def proposeExchange(){
		co -1
		busy +1
		creer Exchange struct +currentExchange	
	}
	
	
	def Reponse(){
		
	}
	
	def (){
		
	}
	
}
