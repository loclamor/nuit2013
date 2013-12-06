package nuitinfo2013

class UserManager {
	// if a user is connected he is in the map
	// if he is available then the boolean is true
	User user;
	boolean available = true
	
    static constraints = {
		user unique: true, nullable: false
    }
}
