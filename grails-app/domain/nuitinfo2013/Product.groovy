package nuitinfo2013

class Product {

<<<<<<< HEAD
    static constraints = {
=======
    String libelle

    static belongsTo = [productType : ProductType]

    static constraints = {
        libelle nullable: false, blank: false, unique: true
    }

    static mapping = {
        version (false)
>>>>>>> e9e639efc1af5c53be5f1a8ee87c286f12dec315
    }
}
