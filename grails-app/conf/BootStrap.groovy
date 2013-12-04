import org.modl.SecRole
import org.modl.SecUser

class BootStrap {

    def init = { servletContext ->

        def userRole = SecRole.findByAuthority('ROLE_USER')?:new SecRole(authority: 'ROLE_USER').save()
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN')?:new SecRole(authority: 'ROLE_ADMIN').save()

        def users = SecUser.list()
        if(users.size() == 0){
            def standardUser = new SecUser(username: "stdUser", password: "stdPassword", email: "kevinanatole@yopmail.com").save(failOnError: true)
            def adminUser = new SecUser(username: "admin", password: "adminPassword", email: "kevinanatole@yahoo.fr").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
