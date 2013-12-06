package nuitinfo2013



class UpdateJob {
    static triggers = {
      simple repeatInterval: 3600000l // execute job once in 5 seconds
    }

    def execute() {
        res users = User.findAll();
		users.each{
			
		}
		
    }
}
