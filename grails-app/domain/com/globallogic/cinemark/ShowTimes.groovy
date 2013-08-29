package com.globallogic.cinemark

class ShowTimes {
	Float price
	Movie movie
	Date fromDate
	Date untilDate
	Cinema cinema
	List schedules
	
	static hasMany = [takenSeats: Seat, schedules: Schedules]
	
    static constraints = {
		movie nullable: false, blank: false, unique: ['cinema','untilDate','fromDate']
		cinema nullable: false, blank: false
		fromDate nullable: false, blank: false
		untilDate nullable: false, blank: false
		price nullable: false, blank: false
    }
}
