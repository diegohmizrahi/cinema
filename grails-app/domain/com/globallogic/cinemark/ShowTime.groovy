package com.globallogic.cinemark

class ShowTime {
	Float price
	Movie movie
	Date fromDate
	Date untilDate
	Cinema cinema
	List schedules
	
	static hasMany = [schedules: Schedule]
	
    static constraints = {
		movie nullable: false, blank: false, unique: ['cinema','untilDate','fromDate']
		cinema nullable: false, blank: false
		fromDate nullable: false, blank: false
		untilDate nullable: false, blank: false
		price nullable: false, blank: false
		schedules nullable:true
    }
	
	def buildDTO(){
		def dto = [
			id:this.id,
			seatSections: new ArrayList(),
			movie: this.movie.buildDTO(),
			schedules: Schedule.buildDTOList(this.schedules as List),
			cinemaType: this.cinema.cinemaType.type
		]
		return dto
	}
	
}
