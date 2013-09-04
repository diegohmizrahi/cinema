package com.globallogic.cinemark

class Movie {
	
	String title
	String imdbId
	String summary
	String actors
	String picUrl
	String trailerUrl
	String genre
	String director
	Integer year
	
    static constraints = {
		title nullable: false, blank: false
    }
	
	def buildDTO() {
		def dto = [
			id: this.id,
			title: this.title,
			imdbId: this.imdbId,
			summary: this.summary,
			actors: this.actors,
			picUrl: this.picUrl,
			trailerUrl: this.trailerUrl,
			genre: this.genre,
			director: this.director,
			year: this.year,
			cinemaType:""	
		]
		return dto
	}
}
