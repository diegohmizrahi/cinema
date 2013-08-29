package com.globallogic.cinemark.enums

enum CinemaType {
	CINEMA3D('3D'),
	XD('XD'),
	NORMAL('Normal')
	
	String type
	
	CinemaType(String type) {
		this.type = type
	}
}
