package com.globallogic.cinemark.utils

import java.text.ParseException
import java.text.SimpleDateFormat

import org.apache.commons.lang.StringUtils

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException



/**
 * Utils functions to work with dates.
 * Defines a custom format for nirvana application: MM/dd/yyyy HH:mm:ss
 * 
 * @author Tomas de Priede
 * @see ar.com.nirvana.utils.ValidatorUtils
 * @version 0.4.1, 03/05/2011
 * @since 0.4.1
 */
class DateUtils {

	private static String WILDCARD_WORD_RECENT="RECENT"
	private static String WILDCARD_WORD_YESTERDAY="YESTERDAY"
	private static String WILDCARD_WORD_LAST_WEEK="LAST_WEEK"
	private static String WILDCARD_WORD_LAST_MONTH="LAST_MONTH"
	private static String WILDCARD_WORD_LAST_YEAR="LAST_YEAR"

	private static String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss"
	private static String DATE_FORMAT_WITHOUT_HOUR = "MM/dd/yyyy"
	
	private static long ONE_DAY_MILLIS = 60*60*1000L*24
	/**
	 * Formats a date into a String using the following format
	 * MM/dd/yyyy HH:mm:ss
	 * @param date the date to convert
	 * @author Tomas de Priede
	 * @return the date formatted into a String
	 * @see ar.com.nirvana.utils.ValidatorUtils
	 * @version 0.4.1, 03/05/2011
	 * @since 0.4.1
	 */
	public static String dateToString_nirvanaFormat(Date date){
		ValidatorUtils.validateNullOrEmpty(date);
		String pattern = DATE_FORMAT;
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		return format.format(date)
	}

	/**
	 * Formats a String into a Date using the following format
	 * MM/dd/yyyy HH:mm:ss
	 * @param date the date to convert
	 * @author Tomas de Priede
	 * @return the string formatted into a Date
	 * @see ar.com.nirvana.utils.ValidatorUtils
	 * @version 0.4.1, 03/05/2011
	 * @since 0.4.1
	 */
	public static Date stringToDate_nirvanaFormat(String date){
		ValidatorUtils.validateNullOrEmpty(date);

		String pattern = DATE_FORMAT;
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		try{
			return format.parse(date)
		}catch(ParseException e){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}
	
	public static Date stringToDate_nirvanaFormat_withoutNullValidation(String date){
		if (StringUtils.isEmpty(date))
			return null

		String pattern = DATE_FORMAT;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try{
			return format.parse(date)
		}catch(ParseException e){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}

	
	public static Date stringToDate_nirvanaFormatNoHour_withoutNullValidation(String date){
		if (StringUtils.isEmpty(date))
			return null

		String pattern = DATE_FORMAT_WITHOUT_HOUR;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try{
			return format.parse(date)
		}catch(ParseException e){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}

	/**
	 * Formats a String into a Date using the following format
	 * MM/dd/yyyy HH:mm:ss
	 * 
	 * Accepts the following wildchars as argument:
	 * RECENT - Current date
	 * YESTERDAY - current date - 1 day
	 * LAST_WEEK - current date - 7 days
	 * LAST_MONTH - current date - 1 month
	 * LAST_YEAR - current date - 1 year
	 * 
	 * @param date the date to convert
	 * @author Tomas de Priede
	 * @return the string formatted into a Date
	 * @see ar.com.nirvana.utils.ValidatorUtils
	 * @version 0.4.1, 03/05/2011
	 * @since 0.4.1
	 */
	public static Date stringToData_nirvanaFormat_withWildcards(String date){
		ValidatorUtils.validateNullOrEmpty(date);
		//First check for wildchars
		Date time = new Date();
		switch (StringUtils.upperCase(date)){
			case WILDCARD_WORD_LAST_YEAR:
				time = org.apache.commons.lang.time.DateUtils.addYears(time,-1)
				return time;
			case WILDCARD_WORD_LAST_MONTH:
				time = org.apache.commons.lang.time.DateUtils.addMonths(time,-1)
				return time;
			case WILDCARD_WORD_LAST_WEEK:
				time = org.apache.commons.lang.time.DateUtils.addDays(time,-6)
			case WILDCARD_WORD_YESTERDAY:
				time = org.apache.commons.lang.time.DateUtils.addDays(time,-1)
			case WILDCARD_WORD_RECENT:
				return time;
		}

		return stringToDate_nirvanaFormat(date);
	}


	/**
	* add or remove X hours to the specific dates.
	* @param date the date to validate
	* @author Diego Arean
	* @see ar.com.nirvana.utils.ValidatorUtils
	* @version 0.7.7.0, 14/07/2011
	* @since 0.7.7.0
	*/
	public static Date addHours (Date date , Long hours ){
		ValidatorUtils.validateNullOrEmpty(date,hours);
		Calendar fecha = Calendar.getInstance()
		fecha.setTime (date)
		fecha.add(Calendar.HOUR, hours)
		String pattern = DATE_FORMAT;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		date = fecha.getTime()
		return format.parse(date)
	}
	
	
	/**
	 * The date must not be greater than the current date.
	 * @param date the date to validate
	 * @author Tomas de Priede
	 * @see ar.com.nirvana.utils.ValidatorUtils
	 * @version 0.7.4.1, 17/06/2011
	 * @since 0.7.4.1
	 */
	public static void validateFutureDate(Date date){
		ValidatorUtils.validateNullOrEmpty(date);
		Date currentDate = new Date();
		if (date > currentDate){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}
	
	

	public static Map getDiferencia(Date fecha1,Date fecha2){
		Date fechaMayor = null;
		Date fechaMenor = null;
		Map resultadoMap = new HashMap();
	  
		/* Verificamos cual es la mayor de las dos fechas, para no tener sorpresas al momento
		 * de realizar la resta.
		 */
		if (fecha1.compareTo(fecha2) > 0){
		 fechaMayor = fecha1;
		 fechaMenor = fecha2;
		}else{
		 fechaMayor = fecha2;
		 fechaMenor = fecha1;
		}
	  
	   //los milisegundos
		long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
	  
		//obtenemos los segundos
		long segundos = diferenciaMils / 1000;
	  
		//obtenemos las horas
		long horas = segundos / 3600;
	  
		//restamos las horas para continuar con minutos
		segundos -= horas*3600;
	  
		//igual que el paso anterior
		long minutos = segundos /60;
		segundos -= minutos*60;
	  
		//ponemos los resultados en un mapa :-)
		resultadoMap.put("horas",horas);
		resultadoMap.put("minutos",minutos);
		resultadoMap.put("segundos",segundos);
		return resultadoMap;
	 }
	
	public static String dateToString_WithOutHour(Date date){
		
		ValidatorUtils.validateNullOrEmpty(date);
		
		String pattern = DATE_FORMAT_WITHOUT_HOUR;
		
		SimpleDateFormat format =
		new SimpleDateFormat(pattern);
		
		return format.format(date)
		
		}
	
	
	public static Integer getAge(Date birthday){

		Calendar bd = Calendar.getInstance();
		bd.clear();
		bd.setTime (birthday);
		//fecha actual
		Calendar actualDate = Calendar.getInstance();
		actualDate.setTime (new Date());
		//ahora tenemos que saber si dejamos la resta como esta o le restamos uno
		//puede ser que el usuario aun no halla cumplido los aï¿½os
		Integer age = actualDate.get(Calendar.YEAR) - bd.get(Calendar.YEAR)
		bd.set (Calendar.YEAR,actualDate.get(Calendar.YEAR))
		if(actualDate.before(bd)){
			age-=1
		}
		return age
	}
	
	public static void dateValid (String fecha , String dateFormat ){
		ValidatorUtils.validateNullOrEmpty(fecha, dateFormat);
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Date date
		println fecha
		try{
			date = df.parse(fecha);
		}catch(ParseException e){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
		
		if (!df.format(date).equals(fecha)){
			throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}
	
	

	
	public static int compareDateDifferenceDays (Date firstDate , Date secondDate){
		def firstDateMilis = firstDate.getTime()
		def secondDateMilis = secondDate.getTime()
		
		def differenceMilis = secondDateMilis - firstDateMilis
		
		return differenceMilis / ONE_DAY_MILLIS
	}
	
	public static def verifyDate(def date){
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
		}
		catch (Exception e) {
		  throw new CinemarkException(Codes.INVALIDATE_DATE);
		}
	}
}
