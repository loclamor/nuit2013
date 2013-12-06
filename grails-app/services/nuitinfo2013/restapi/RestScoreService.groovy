package nuitinfo2013.restapi

import nuitinfo2013.Product
import nuitinfo2013.Rating

class RestScoreService {

    def findScoreForProduct(String productLibelle) {
        Integer score = 0

        if (!productLibelle) {
            throw new IllegalArgumentException()
        }

        Product product = Product.findByName(productLibelle)

        def preferenceList = Rating.findAllByProduct(product)
        preferenceList.each { score += it.elo }

        return score
    }
}
