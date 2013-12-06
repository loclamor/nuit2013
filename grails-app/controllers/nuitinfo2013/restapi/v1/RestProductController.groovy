package nuitinfo2013.restapi.v1

import grails.converters.JSON
import nuitinfo2013.Product
import nuitinfo2013.restapi.RestProductService

class RestProductController {

    static allowedMethods = [retrieve: 'GET']

    RestProductService restProductService

    def retrieve(String product_id, String format) {
        Integer score = restProductService.findScoreForProduct(product_id)

        // TODO handle format

        render([score: score] as JSON)
    }
}
