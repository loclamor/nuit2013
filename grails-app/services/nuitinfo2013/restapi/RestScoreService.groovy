package nuitinfo2013.restapi

import nuitinfo2013.Product
import nuitinfo2013.Rating

class RestScoreService {

    def temp(String productName) {
        Map representation

        if (!productName) {
            def productList = Product.findAll()
            representation = [
                    productScoringList:
                    productList.collect { def product ->
                        def score = 0
                        def preferenceList = Rating.findAllByProduct(product)
                        preferenceList.each { score += it.elo }
                        return [
                                productName: product.name,
                                score: score
                        ]
                    }
            ]
        } else {
            def score = 0
            Product product = Product.findByName(productName)

            def preferenceList = Rating.findAllByProduct(product)
            preferenceList.each { score += it.elo }
            representation = [
                    score: score
            ]

        }

        return representation
    }

    /*def findScoreForProduct(String productLibelle) {
        Integer score = 0

        if (!productLibelle) {
            throw new IllegalArgumentException()
        }

        Product product = Product.findByName(productLibelle)

        def preferenceList = Rating.findAllByProduct(product)
        preferenceList.each { score += it.elo }

        return score
    }*/
}
