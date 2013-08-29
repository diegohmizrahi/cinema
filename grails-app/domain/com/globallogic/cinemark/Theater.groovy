package com.globallogic.cinemark

class Theater {
	
	String name
	String address
	String phone
	
	static hasMany = [cinemas: Cinema, movies: Movie]
	
	static constraints = {
		name unique: true, nullable: false
		address nullable: false
		phone nullable: false
    }
}
