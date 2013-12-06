package nuitinfo2013.restapi

import nuitinfo2013.Product
import nuitinfo2013.Rating


class RestProductService {

    Map buildRequestRepresentation(String scoreMoreThanEquals,
                                   String scoreLessThanEquals) {

        if (!scoreMoreThanEquals && !scoreLessThanEquals) {
            return buildRepresentationFromList(Product.findAll())
        }
        if (scoreMoreThanEquals && scoreLessThanEquals) {
            if (scoreMoreThanEquals > scoreLessThanEquals) {
                return badRequest()
            }
        }

        def productListTmp = Product.findAll()
        def productListSup = []
        def productListInf = []
        def productListFinal

        productListTmp.each { def product ->
            def scoreProduct = 0
            def ratingList = Rating.findAllByProduct(product)
            ratingList.each { scoreProduct += it.elo }

            if (scoreMoreThanEquals) {
                if (scoreProduct >= (scoreMoreThanEquals as Integer)) {
                    productListSup.add(product)
                }
            }
            if (scoreLessThanEquals) {
                if (scoreProduct <= (scoreLessThanEquals as Integer)) {
                    productListInf.add(product)
                }
            }
        }

        if (scoreMoreThanEquals && scoreLessThanEquals) {
            productListFinal = productListInf.intersect(productListSup)
        } else if (scoreMoreThanEquals) {
            productListFinal = productListSup
        }
        else {
            productListFinal = productListInf
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

    private Map badRequest() {
        return [
                error: 'Incorrect URL parameters'
        ]
    }
}
