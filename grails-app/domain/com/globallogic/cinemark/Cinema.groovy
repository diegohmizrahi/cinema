package com.globallogic.cinemark

import java.util.List;

import com.globallogic.cinemark.enums.CinemaType

class Cinema {
	
	Integer cinemaNumber
	CinemaType cinemaType
	Theater theater
	
	static hasMany = [ seatSections: SeatSection ]
	
    static constraints = {
		cinemaNumber unique: ['theater'], nullable: false, blank: false
		cinemaType nullable: false, blank: false
		theater nullable: false, blank: false	
    }
	
	def buildDTO(){
		def dto = [
			number: this.cinemaNumber,
			type: this.cinemaType.type,
		]
		return dto
	}
	
}
