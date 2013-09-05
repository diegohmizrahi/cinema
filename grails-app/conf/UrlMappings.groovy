class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/resources/theaters" (controller:"theater" , action:"getTheaters")
		"/resources/theaters/$id?/movies" (controller:"cinema" , action:"moviesByTheater")
		"/resources/showTimes" (controller:"schedule" , action:"movieSchedules")
		"/resources/movies/$id" (controller:"movie" , action:"getMovieData")
		"/resources/payments" (controller:"seat", action:"bookSeats")
		
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
