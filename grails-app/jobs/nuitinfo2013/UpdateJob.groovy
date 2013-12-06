package nuitinfo2013



class UpdateJob {
    static triggers = {
      simple repeatInterval: 3600000l // execute job once in an hour
    }

    def execute() {
        def users = User.findAll();
		users.each{
			it.addLife();
		}
		
    }
}
