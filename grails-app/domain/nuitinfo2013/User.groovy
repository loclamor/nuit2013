package nuitinfo2013

class User {

    transient springSecurityService

    String username
    String password
    String emailAddress
<<<<<<< HEAD
	
	Product selected;
	
	/* personnal informations for openData*/
	String FirstName;
	String LastName;
	String adress;
	
	String zipCode;
	String city;
	
	String[] hobbies;
	String job;
	/*end of personnal informations*/
	
	
=======

    String zipCode
    String country
    Date birthDate
    Sexe sexe

>>>>>>> e9e639efc1af5c53be5f1a8ee87c286f12dec315
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true, nullable: false, minSize: 3
        password blank: false, nullable: false, minSize: 8
        emailAddress email: true, unique: true, nullable: false
    }

    static mapping = {
        password column: '`password`'
        version (false)
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}

enum Sexe {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}
