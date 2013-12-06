package nuitinfo2013.restapi.v1

import grails.converters.JSON
import nuitinfo2013.restapi.RestProductService
import nuitinfo2013.restapi.RestScoreService

class RestScoreController {

    static allowedMethods = [retrieve: 'GET']

    RestScoreService restScoreService

    def retrieve(String product_name) {
        Map representation = restScoreService.temp(product_name)

        // TODO handle format

        render(representation as JSON)
    }
}
