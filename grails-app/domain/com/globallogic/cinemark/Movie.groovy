package com.globallogic.cinemark

class Movie {
	
	String title
	String imdbId
	String summary
	
    static constraints = {
		title nullable: false, blank: false
    }
}
