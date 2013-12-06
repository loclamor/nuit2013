package nuitinfo2013



class UpdateJob {
    static triggers = {
      simple repeatInterval: 120000l // execute job once in an hour in normal usage: 3600000l 
    }

    def execute() {
        def users = User.findAll();
		users.each{
			it.addLife();
		}
		
    }
}
