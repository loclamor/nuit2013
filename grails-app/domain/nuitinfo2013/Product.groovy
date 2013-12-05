package nuitinfo2013

class Product {

    String libelle

    static belongsTo = [productType : ProductType]

    static constraints = {
        libelle nullable: false, blank: false, unique: true
    }

    static mapping = {
        version (false)
    }
}
