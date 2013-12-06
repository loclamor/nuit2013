package nuitinfo2013.restapi.v1

import grails.converters.JSON
import grails.converters.XML
import nuitinfo2013.User
import nuitinfo2013.restapi.RestUserService

class RestUserController {
	
	static allowedMethods = [retrieve: 'GET']
	
	RestUserService restUserService

   def retrieve(String act, String format) {
	   
	   def userProperties = restUserService.buildRequestRepresentation(act)
	   
	   switch (format){
		   case "XML":
			   render(userProperties as XML) 
			   break
		   default:
			   render(userProperties as JSON)
			   break
	   }
    }
}
