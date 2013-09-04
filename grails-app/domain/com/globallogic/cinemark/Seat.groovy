package com.globallogic.cinemark

import java.util.List;

import com.globallogic.cinemark.enums.SeatsSectionType

class Seat {
	
	String row
	Integer column
	SeatsSectionType seatSection
	String email
	String confirmationCode
	Integer identificationNumber
	
    static constraints = {
		email nullable: true,email: true
		confirmationCode nullable: true
		identificationNumber nullable: true
		row nullable: false, blank: false, unique: ['column','seatSection']
		column nullable: false, blank: false
    }
	
	def buildDTO(){
		def dto = [
			row: this.row,
			column: this.column,
		]
		return dto
	}
	
	public static def buildDTOList(List<SeatSection> seats){
		List seatDTOList = new ArrayList();
		for (s in seats) {
			seatDTOList.add(s.buildDTO());
		}
		return seatDTOList;
	}
	
}
