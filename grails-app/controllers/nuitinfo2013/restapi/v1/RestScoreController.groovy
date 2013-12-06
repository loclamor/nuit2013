package nuitinfo2013.restapi.v1

import grails.converters.JSON
import grails.converters.XML
import nuitinfo2013.restapi.RestProductService
import nuitinfo2013.restapi.RestScoreService

class RestScoreController {

    static allowedMethods = [retrieve: 'GET']

    RestScoreService restScoreService

    def retrieve(String product_name, String format) {
        Map representation = restScoreService.buildRequestRepresentation(product_name)

		switch(format?.toLowerCase()){
			case "xml":
				render(representation as XML)
				break
			default:
				render(representation as JSON)
				break
		}
		
    }
}
