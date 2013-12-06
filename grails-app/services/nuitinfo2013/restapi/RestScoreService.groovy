package nuitinfo2013.restapi

import nuitinfo2013.Product
import nuitinfo2013.Rating

class RestScoreService {

    def buildRequestRepresentation(String productName) {
        Map representation

        if (!productName) {
            def productList = Product.findAll()
            representation = [
                    productScoringList:
                    productList.collect { def product ->
                        def score = 0
                        def ratingList = Rating.findAllByProduct(product)
                        ratingList.each { score += it.elo }
                        return [
                                productName: product.name,
                                score: score
                        ]
                    }
            ]
        } else {
            def score = 0
            Product product = Product.findByName(productName)

            def ratingList = Rating.findAllByProduct(product)
            ratingList.each { score += it.elo }
            representation = [
                    score: score
            ]

        }

        return representation
    }
}
