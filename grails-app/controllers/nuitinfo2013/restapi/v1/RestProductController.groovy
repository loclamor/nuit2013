package nuitinfo2013.restapi.v1

import grails.converters.JSON
import nuitinfo2013.restapi.RestProductService


class RestProductController {

    static allowedMethods = [retrieve: 'GET']

    RestProductService restProductService

    def retrieve(String scoreMoreThan, String scoreLessThan, String format) {
        Map representation = restProductService.buildRequestRepresentation(scoreMoreThan, scoreLessThan)

        render(representation as JSON)
    }
}
