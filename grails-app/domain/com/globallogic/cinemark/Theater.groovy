package com.globallogic.cinemark

class Theater {
	
	String name
	String address
	String phone
	
	static hasMany = [cinemas: Cinema]
	
	static constraints = {
		name unique: true, nullable: false
		address nullable: false
		phone nullable: false
    }
	
	def buildDTO() {
		def dto = [
			id: this.id,
			name: this.name, 
			address: this.address,
			phone: this.phone,
		]
		return dto
	}
	
}
