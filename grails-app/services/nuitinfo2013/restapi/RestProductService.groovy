package nuitinfo2013.restapi

import nuitinfo2013.Preference
import nuitinfo2013.Product

class RestProductService {

    def findScoreForProduct(String productLibelle) {
        Integer score

        if (!productLibelle) {
            throw new IllegalArgumentException()
        }

        Product product = Product.findByLibelle(productLibelle)
        def preferenceList = Preference.findAllByProduct(product)
        /*def criteria = Preference.createCriteria()
        def results = criteria.list {
            eq 'product.libelle', product.libelle
        }*/
        preferenceList.each { score += it.score }
        return score
    }
}
