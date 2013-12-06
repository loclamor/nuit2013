package nuitinfo2013

import grails.plugin.springsecurity.annotation.Secured

@Secured(value = ["isAuthenticated()"])
class IndexController {

	def springSecurityService
    def index() {
        render(view: "/index")
    }
	
	def logout(){
		def currentUser = User.get(springSecurityService.authentication.principal.id)
		def activeUser = UserManager.findByUser(currentUser)
		if(activeUser){
			activeUser.delete()
		}
		session.invalidate();
		redirect(action:"index")
	}
}
