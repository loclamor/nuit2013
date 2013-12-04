import org.modl.SecRole
import org.modl.SecUser

class BootStrap {

    def init = { servletContext ->

        def userRole = SecRole.findByAuthority('ROLE_USER')?:new SecRole(authority: 'ROLE_USER').save()
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN')?:new SecRole(authority: 'ROLE_ADMIN').save()

        def users = SecUser.list()?:[]
        if(!users){
            def standardUser = new SecUser(username: "stdUser", password: "stdPassword").save(flush: true)
            def adminUser = new SecUser(username: "admin", password: "adminPassword").save(flush: true)
        }
    }
    def destroy = {
    }
}
