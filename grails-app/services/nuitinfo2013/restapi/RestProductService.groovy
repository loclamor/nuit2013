package nuitinfo2013.restapi

import nuitinfo2013.Product
import nuitinfo2013.Rating


class RestProductService {

    Map buildRequestRepresentation(String scoreMoreThan,
                                   String scoreLessThan) {
        if (!scoreMoreThan && !scoreLessThan) {
            return buildRepresentationFromList(Product.findAll())
        }
        if (scoreMoreThan && scoreLessThan) {
            throw new IllegalArgumentException()
        }

        def productListTmp = Product.findAll()
        def productListFinal = []

        productListTmp.each { def product ->
            def scoreProduct = 0
            def ratingList = Rating.findAllByProduct(product)
            ratingList.each { scoreProduct += it.elo }

            if (scoreMoreThan) {
                if (scoreProduct >= (scoreMoreThan as Integer)) {
                    productListFinal.add(product)
                }
            } else if (scoreLessThan) {
                if (scoreProduct <= (scoreLessThan as Integer)) {
                    productListFinal.add(product)
                }
            }
        }

        return buildRepresentationFromList(productListFinal)
    }

    private Map buildRepresentationFromList(List<Product> productList) {
        return [
                productRepresentationList:
                        productList.collect { def product ->
                            return [
                                    productName: product.name,
                                    description: product.description,
                                    price: product.price,
                                    type: product.productType.libelle
                            ]
                        }
        ]
    }
}
