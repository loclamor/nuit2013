import nuitinfo2013.Product
import nuitinfo2013.ProductType
import nuitinfo2013.Rating
import nuitinfo2013.Exchange
import nuitinfo2013.Role
import nuitinfo2013.User
import nuitinfo2013.UserRole

class BootStrap {

    def init = { servletContext ->
        def roles = Role.list()
        def userRole
        def adminRole
		
		def productType1 = ProductType.findByLibelle("Laptop")?:new ProductType(libelle: "Laptop").save()
		def productType2 = ProductType.findByLibelle("Desktop")?:new ProductType(libelle: "Desktop").save()
		def productType3 = ProductType.findByLibelle("HardDrive")?:new ProductType(libelle: "HardDrive").save()
		def productType4 = ProductType.findByLibelle("Lights")?:new ProductType(libelle: "Lights").save()
		def productType5 = ProductType.findByLibelle("Shoes")?:new ProductType(libelle: "Shoes").save()
		def productType6 = ProductType.findByLibelle("WinterClothes")?:new ProductType(libelle: "WinterClothes").save()
		def productType7 = ProductType.findByLibelle("SummerClothes")?:new ProductType(libelle: "SummerClothes").save()

		def product1 = Product.findByName("Asus e-300")?:new Product(name: "Asus e-300",productType: productType1).save()
		def product2 = Product.findByName("Emachines b500")?:new Product(name: "Emachines b500",productType: productType1).save()
		def product3 = Product.findByName("HP-BC9856 + 22 inch screen")?:new Product(name: "HP-BC9856 + 22 inch screen",productType: productType2).save()
		def product4 = Product.findByName("Dell 600")?:new Product(name: "Dell 600",productType: productType2).save()
		def product5 = Product.findByName("iomega 1TB")?:new Product(name: "iomega 1TB",productType: productType3).save()
		def product6 = Product.findByName("Lacie 3TB, 10000rpm")?:new Product(name: "Lacie 3TB, 10000rpm",productType: productType3).save()
		def product7 = Product.findByName("Low consumption bulb D52")?:new Product(name: "Low consumption bulb D52",productType: productType4).save()
		def product8 = Product.findByName("High power bulb H89")?:new Product(name: "High power bulb H89",productType: productType4).save()
		def product9 = Product.findByName("Nika air jordan")?:new Product(name: "Nika air jordan",productType: productType5).save()
		def product10 = Product.findByName("Adidas classic")?:new Product(name: "Adidas classic",productType: productType5).save()
		def product11 = Product.findByName("Reebok origin")?:new Product(name: "Reebok origin",productType: productType5).save()

		
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
            stdUser = new User(username: 'user', password: "123123123", emailAddress: "test@yopmail.com",currentProduct: User.aleat()).save(failOnError: true)
            stdUser2 = new User(username: 'user2', password: "stdPassword2", emailAddress: "test2@yopmail.com",currentProduct: User.aleat()).save(failOnError: true)
            adminUser = new User(username: 'admin', password: "123123123", emailAddress: "kevinanatole@yahoo.fr",currentProduct: User.aleat()).save(failOnError: true)
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
       
        def pref1 = Rating.findByUserAndProduct(stdUser,product1)?:new Rating(user: stdUser,product: product1, elo: 15).save()
        def pref2 = Rating.findByUserAndProduct(stdUser2,product2)?:new Rating(user: stdUser2,product: product2, elo: -3).save()
    }
    def destroy = {
    }
}
