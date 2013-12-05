package nuitinfo2013

import grails.plugin.springsecurity.annotation.Secured


class IndexController {

    def index() {
        render(view: "/index")
    }
}
