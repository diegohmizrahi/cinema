package com.globallogic.cinemark

import com.globallogic.cinemark.enums.SeatsSectionType

class SeatSection {
	
	SeatsSectionType type
	Integer rows
	Integer colums
	Cinema cinema
	
	static belongsTo = {cinema: Cinema}
	
    static constraints = {
		type nullable: false, blank: false, unique: ['cinema']
		rows nullable: false, max: 1..100, blank: false
		colums nullable: false, max: 1..100, blank: false
    }
}
