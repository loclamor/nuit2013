package nuitinfo2013

import grails.plugin.springsecurity.annotation.Secured

@Secured(value = ["isAuthenticated()"])
class IndexController {

    def index() {
        render(view: "/index")
    }
}
