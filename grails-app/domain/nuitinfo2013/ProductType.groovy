package nuitinfo2013

class ProductType {

    String libelle

    static hasMany = [products : Product]

    static constraints = {
        libelle nullable: false, blank: false, unique: true
    }

    static mapping = {
        version (false)
    }
}
