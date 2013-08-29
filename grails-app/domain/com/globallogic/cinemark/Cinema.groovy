package com.globallogic.cinemark

import com.globallogic.cinemark.enums.CinemaType

class Cinema {
	
	Integer cinemaNumber
	CinemaType cinemaType
	Theater theater
	
	static hasMany = { seatSections: SeatSection }
	
    static constraints = {
		cinemaNumber unique: ['theater'], nullable: false, blank: false
		cinemaType nullable: false, blank: false
		theater nullable: false, blank: false	
    }
}
