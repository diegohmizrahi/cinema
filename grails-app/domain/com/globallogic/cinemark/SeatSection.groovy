package com.globallogic.cinemark

import com.globallogic.cinemark.enums.SeatsSectionType

class SeatSection {
	
	SeatsSectionType type
	Integer rows
	Integer columns
	Cinema cinema
	
	static belongsTo = [cinema: Cinema]
	
    static constraints = {
		type nullable: false, blank: false, unique: ['cinema']
		rows nullable: false, min: 1, max: 100, blank: false
		columns nullable: false, min: 1, max:100, blank: false
    }
	
	def buildDTO() {
		def dto = [
			rows: this.rows,
			cols: this.columns,
			takenSeats: new ArrayList()
		]
		return dto
	}
	
	public static def buildDTOList(List<SeatSection> seatSection){
		List seatSectionsDTOList = new ArrayList();
		for (ss in seatSection) {
			seatSectionsDTOList.add(ss.buildDTO());
		}
		return seatSectionsDTOList;
	}
}
