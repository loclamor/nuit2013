package nuitinfo2013.restapi.v1

import grails.converters.JSON
import nuitinfo2013.restapi.RestProductService
import nuitinfo2013.restapi.RestScoreService

class RestScoreController {

    static allowedMethods = [retrieve: 'GET']

    RestScoreService restScoreService

    def retrieve(String product_name) {
        Integer score = restScoreService.findScoreForProduct(product_name)

        // TODO handle format

        render([score: score] as JSON)
    }
}
