class UrlMappings {

	static mappings = {
        // API REST OpenData mapping
        "/api/1.0/score"(controller: 'restScore') {
            action = [GET: "retrieve"]
        }
        "/api/1.0/product"(controller: 'restProduct') {
            action = [GET: "retrieve"]
        }

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "index",action: "index")
		"500"(view:'/error')
	}
}
