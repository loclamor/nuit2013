package nuitinfo2013

class Preference {

    Integer score = 0

    static belongsTo = [user: User, product: Product]
    static constraints = {
        score nullable: false, blank:false
    }

    static mapping = {
        version(false)
    }
}
