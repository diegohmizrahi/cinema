package com.globallogic.cinemark

import com.globallogic.cinemark.enums.SeatsSectionType
class SchedulesService {
	
	def getNextSchedules(def theater, def movie) {
		def showTimes = ShowTimes.executeQuery("FROM ShowTimes st WHERE st.cinema.theater = :theater AND st.movie = :movie",[theater:theater, movie: movie])
		def schedules
		List auxList = new ArrayList()
		showTimes?.each { st ->
			def aux = [:]
			schedules = Schedules.findAllByShowTime(st)
			schedules.each { sc ->
				aux = sc.buildDTO()
				st.cinema?.seatSections?.each { sec ->
					aux."${sec.type.section.toLowerCase()}"= sec.buildDTO()
					sc.takenSeats?.each { taken ->
						if (taken?.seatSection.equals(sec.type)) {
							aux."${sec.type.section.toLowerCase()}".takenSeats.add(taken.buildDTO())
							
						}
					}
				}
				auxList.add(aux)
			}
			
			
		}
		auxList
	}
	
}