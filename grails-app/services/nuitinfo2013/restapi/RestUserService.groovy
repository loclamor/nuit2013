package nuitinfo2013.restapi

import nuitinfo2013.Sexe;
import nuitinfo2013.User

class RestUserService {

	def buildRequestRepresentation(String action){
		
		if(action == "avgage"){
				Map render = new HashMap<String,?>()
				List<User> users = User.findAll()
				def sum = 0
				def number = 0
				def years
				
				users.each{
					def birthDate = it.getBirthDate()
					
					years = Calendar.getInstance().get(Calendar.YEAR) - birthDate.getYear() 

					sum += years
					number++
					
				}
				def avg 
				if(number != 0){
					avg = sum/number
				}else{
					avg = -1
				}
				render.put("average", avg)
				return render
				
		}else if (action == "sex"){
			Map render = new HashMap<String,?>()
			List<User> users = User.findAll()
			def female = 0
			def male = 0
			
			users.each{
				if(it.getSexe().equals(Sexe.FEMALE))
					female++
				else
					male++
			}
			female>male?render.put("Female", female):render.put("Male", male)
			return render
				
		}else{
				ArrayList render = new ArrayList<>()
				def temp
		
					List<User> users = User.findAll()
					
					users.each {
						List usersZipCode = User.findAllByZipCode(it.getProperty("zipCode"))
						usersZipCode.remove(it)
						List usersBirthDate = User.findAllByBirthDate(it.getProperty("birthDate"))
						usersBirthDate.remove(it)
						List usersSexe = User.findAllBySexe( it.getProperty("sexe"))		
						usersSexe.remove(it)
						
						temp = usersBirthDate.intersect(usersZipCode) 
						temp = temp.intersect(usersSexe)
						
						if (temp!=null){
							temp.each{
								if(!render.contains(it))
									render.add(it)
							}
						}
				}
				return render
		}
		
	}
}
