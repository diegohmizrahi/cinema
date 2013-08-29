package com.globallogic.cinemark.exceptions



class CinemarkException extends RuntimeException{
	
			def code
			public CinemarkException(def code){
				super((String)code.message)
				this.code = code
			}
}
