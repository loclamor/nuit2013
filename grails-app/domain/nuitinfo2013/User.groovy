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

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
	Product currentProduct;

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true, nullable: false, minSize: 3
        password blank: false, nullable: false, minSize: 8
        emailAddress email: true, unique: true, nullable: false
        zipCode nullable: true, blank:true
        country nullable: true, blank: true
        birthDate nullable: true, blank:true
        sexe nullable: true, blank: true
        currentProduct nullable: true, blank: true
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
    FEMALE,MALE
}
