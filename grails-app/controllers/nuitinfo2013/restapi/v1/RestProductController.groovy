package nuitinfo2013.restapi.v1

import grails.converters.JSON
import grails.converters.XML
import nuitinfo2013.restapi.RestProductService
import static org.springframework.http.HttpStatus.*


class RestProductController {

    static allowedMethods = [retrieve: 'GET']

    RestProductService restProductService

    def retrieve(String scoreMoreThanEquals, String scoreLessThanEquals, String format) {
        Map representation = restProductService.buildRequestRepresentation(scoreMoreThanEquals, scoreLessThanEquals)

        if (representation.error) {
            render(text: representation as JSON, status: BAD_REQUEST)
        }

        switch (format) {
            case 'xml':
                render(representation as XML)
                break;
            default:
                render(representation as JSON)
        }
    }
}
