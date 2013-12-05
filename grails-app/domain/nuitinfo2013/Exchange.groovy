package nuitinfo2013

class Exchange {
	
	User u1;
	User u2;

    static constraints = { 
		u1 nullable: false
        u2 nullable: false
    }

	def exchangeValidate(User userA, User userB, Product productA, Product productB) {
		
	}
	
	def exchangeDenied(User userA, User userB, Product productA, Product productB) {
		
	}
}
