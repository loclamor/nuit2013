package nuitinfo2013

class Product {
	String name;
	String description;
	double price = 0.0;

    static belongsTo = [productType: ProductType]
	
    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: true
        price nullable:false, blank: true
    }
}
