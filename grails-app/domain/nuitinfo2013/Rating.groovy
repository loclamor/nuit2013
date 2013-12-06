package nuitinfo2013

class Rating {

	float elo = 0;

    static belongsTo = [user: User, product: Product]
    static constraints = {
        elo nullable: false, blank:false
    }

    static mapping = {
        version(false)
    }
}
