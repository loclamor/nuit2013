import nuitinfo2013.Exchange
import nuitinfo2013.Product
import nuitinfo2013.Rating
import nuitinfo2013.Role
import nuitinfo2013.User
import nuitinfo2013.UserRole

class BootStrap {

    def init = { servletContext ->
        def roles = Role.list()
        def userRole
        def adminRole
        if(roles.size() == 0){
            userRole = new Role(authority: 'ROLE_USER').save()
            adminRole = new Role(authority: 'ROLE_ADMIN').save()
        }else{
            userRole = Role.findByAuthority('ROLE_USER')
            adminRole = Role.findByAuthority('ROLE_ADMIN')
        }
        def users = User.list()
        def stdUser
        def adminUser
        if(users.size() == 0){
            stdUser = new User(username: 'user', password: "stdPassword", emailAddress: "test@yopmail.com").save()
            adminUser = new User(username: 'admin', password: "passwordProtected", emailAddress: "kevinanatole@yahoo.fr").save()
        }else{
            adminUser = User.findByUsername("admin")
            stdUser = User.findByUsername("stdUser")
        }
        def stdAuth = stdUser.getAuthorities()
        if(stdAuth.size() == 0){
            new UserRole(user: stdUser, role: userRole).save()
        }
        def adminAuth = adminUser.getAuthorities()
        if(stdAuth.size() == 0){
            new UserRole(user: adminUser, role: adminRole).save()
        }
		
		
    }
	
	
    def destroy = {
    }
}
