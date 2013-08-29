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
}
