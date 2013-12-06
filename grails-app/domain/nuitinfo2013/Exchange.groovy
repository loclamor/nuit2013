package nuitinfo2013

/**
 * Created with IntelliJ IDEA.
 * User: ju
 * Date: 05/12/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
class Exchange {

    User firstUser
    Boolean firstUserResponse=null
    User secondUser
    Boolean secondUserResponse=null
    Date initialTime;

	Boolean ended=false
	
	static constraints= {
		firstUserResponse nullable: true
		secondUserResponse nullable: true
	}

}
