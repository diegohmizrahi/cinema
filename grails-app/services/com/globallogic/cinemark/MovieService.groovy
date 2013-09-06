package com.globallogic.cinemark

class MovieService {
	
	def getCurrentMovies() {

		def movies = ShowTime.executeQuery("SELECT st.movie FROM ShowTime st WHERE :date >= st.fromDate AND :date <= st.untilDate",[date:new Date().clearTime()])
		
		if (!movies){
			return []
		}
		
		println movies
		return movies
	}
	
}