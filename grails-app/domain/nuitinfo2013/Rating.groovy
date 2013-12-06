package nuitinfo2013

class Rating {

	float elo = 1500;

    static belongsTo = [user: User, product: Product]
    static constraints = {
        elo nullable: false, blank:false
    }

    static mapping = {
        version(false)
    }
}
