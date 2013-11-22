package nuitinfo2013

class User {

    String username
    String emailAddress
    String password


    def isAdmin(){
        return this.role == Role.administrator
    }

    def isMember(){
        return this.role == Role.member
    }

    static hasOne = [role:Role]
    static mapping = {
        version(false)
    }
    static constraints = {
        username nullable: false, blank: false, minSize: 5, maxSize: 20
        emailAddress email: true, nullable: false , blank: false, maxSize: 255
        password nullable: false, blank: false, minSize: 8, maxSize: 30
    }
}

enum Role{
        visitor(1), member(2), administrator(3)
        Role(int value) { this.value = value }
        private final int value
        public int value() { return value }
}
