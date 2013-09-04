package com.globallogic.cinemark

class CinemaService {
	
	def getCinemas(params) {
		def theater = Theater.get(params.id)
		if (!theater) {
			return []
		}
		
		def showTimes = ShowTimes.executeQuery("FROM ShowTimes st WHERE st.cinema.theater = :theater AND :date between st.fromDate AND st.untilDate",[theater:theater,date:new Date().clearTime()])
		
		if (!showTimes){
			return []
		}
		def movieDTOList = new ArrayList()
		def movie
		showTimes?.each{
			movie = it.movie.buildDTO()
			movie.cinemaType = it.cinema.cinemaType.type
			movieDTOList.add(movie)
		}
		movieDTOList
	}
	
}