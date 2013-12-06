package nuitinfo2013



import grails.test.mixin.*
import org.junit.*
import nuitinfo2013.Ra

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class RatingAlgorithmControllerTests extends GroovyTestCase {
    def ratingAlgorithmService
    void testSomething() {
       
    }
	
	void testUpdateRatings() {
		// tests
		
		// data
		Product p1 = new Product().save(failOnError : true);
		Product p2 = new Product().save(failOnError : true);
		
		User u1 = new User(username: 'user3', password: "stdPassword", 
			emailAddress: "test1@yopmail.com", currentProduct:p1).save(failOnError : true)
		User u2 = new User(username: 'user2', password: "stdPassword", 
			emailAddress: "test2@yopmail.com", currentProduct:p2).save(failOnError : true)
		
		
		
		Rating r1 = new Rating(u:u1, p:p1, elo:1600).save(failOnError : true);
		Rating r2 = new Rating(u:u2, p:p2, elo:1600).save(failOnError : true);
		Rating r3 = new Rating(u:u1, p:p2, elo:1500).save(failOnError : true);
		Rating r4 = new Rating(u:u2, p:p1, elo:1500).save(failOnError : true);
		
		Exchange e = new Exchange(u1:u1, u2:u2);
		e.id = 1;
		e.save(failOnError : true);
		
		// Print old ratings
		println ("Old ratings : owned products")
		println ("Old rating User1, Product1 : " + r1.elo);
		println ("Old rating User2, Product2 : " + r2.elo);
		println ("Old ratings : non-owned products")
		println ("Old rating User1, Product2 : " + r3.elo);
		println ("Old rating User2, Product1 : " + r4.elo);



        ratingAlgorithmService.update(e);
		
		// Print new ratings
		println ("New ratings : owned products")
		println ("New rating User1, Product2 : " + r3.elo);
		println ("New rating User2, Product1 : " + r4.elo);
		println ("New ratings : non-owned products")
		println ("New rating User1, Product1 : " + r1.elo);
		println ("New rating User2, Product2 : " + r2.elo);
	}
}
