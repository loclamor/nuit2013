import nuitinfo2013.Product
import nuitinfo2013.ProductType
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
        def stdUser2
        def adminUser
        if(users.size() == 0){
            stdUser = new User(username: 'user', password: "stdPassword", emailAddress: "test@yopmail.com").save(failOnError: true)
            stdUser2 = new User(username: 'user2', password: "stdPassword2", emailAddress: "test2@yopmail.com").save(failOnError: true)
            adminUser = new User(username: 'admin', password: "passwordProtected", emailAddress: "kevinanatole@yahoo.fr").save(failOnError: true)
        }else{
            adminUser = User.findByUsername("admin")
            stdUser = User.findByUsername("user")
            stdUser2 = User.findByUsername("user2")
        }
        def stdAuth = stdUser.getAuthorities()
        if(stdAuth.size() == 0){
            new UserRole(user: stdUser, role: userRole).save()
        }
        def stdAuth2 = stdUser2.getAuthorities()
        if(stdAuth2.size() == 0){
            new UserRole(user: stdUser2, role: userRole).save()
        }
        def adminAuth = adminUser.getAuthorities()
        if(adminAuth.size() == 0){
            new UserRole(user: adminUser, role: adminRole).save()
        }

        def productType1 = new ProductType(libelle: "Laptop").save()
        def productType2 = new ProductType(libelle: "Desktop").save()
        def productType3 = new ProductType(libelle: "HardDrive").save()
        def productType4 = new ProductType(libelle: "Lights").save()
        def productType5 = new ProductType(libelle: "Shoes").save()
        def productType6 = new ProductType(libelle: "WinterClothes").save()
        def productType7 = new ProductType(libelle: "SummerClothes").save()

        def product1 = new Product(libelle: "Asus e-300",productType: productType1).save()
        def product2 = new Product(libelle: "Emachines b500",productType: productType1).save()
        def product3 = new Product(libelle: "HP-BC9856 + 22 inch screen",productType: productType2).save()
        def product4 = new Product(libelle: "Dell 600",productType: productType2).save()
        def product5 = new Product(libelle: "iomega 1TB",productType: productType3).save()
        def product6 = new Product(libelle: "Lacie 3TB, 10000rpm",productType: productType3).save()
        def product7 = new Product(libelle: "Low consumption bulb D52",productType: productType4).save()
        def product8 = new Product(libelle: "High power bulb H89",productType: productType4).save()
        def product9 = new Product(libelle: "Nika air jordan",productType: productType5).save()
        def product10 = new Product(libelle: "Adidas classic",productType: productType5).save()
        def product11 = new Product(libelle: "Reebok origin",productType: productType5).save()

        def pref1 = new Rating(user: stdUser,product: product1, elo: 15).save()
        def pref2 = new Rating(user: stdUser2,product: product2, elo: -3).save()


    }
    def destroy = {
    }
}
