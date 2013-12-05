package nuitinfo2013

class User {

    transient springSecurityService

    String username
    String password
    String emailAddress
    String zipCode
    String country
    Date birthDate
    Sexe sexe
	
	Product Selected;

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
