package com.globallogic.cinemark.utils


import java.text.ParseException
import java.text.SimpleDateFormat

import org.apache.commons.lang.StringUtils

import com.globallogic.cinemark.constants.Codes
import com.globallogic.cinemark.exceptions.CinemarkException

class ValidatorUtils {

	public static void validateAllNullOrEmpty(Object... obj){
		validateNull(obj);

		for (Object o : obj){
			if (o != null) {
				if (o instanceof String){
					if (!StringUtils.isEmpty((String)o))
					return
				}	else
				return
			}
		}
		throw new CinemarkException(Codes.INPUT_DATA_EXCEPTION_ERROR);
	}

public static String validateDefault(String value, String defaultValue){
	if (StringUtils.isEmpty(value))
	return defaultValue;
	else
	return value;
}



public static Number validateDefault(Number value,Number defaultValue){
	if (value == null)
	return defaultValue;
	else
	return value;
}


public static void validateEmptyCollection(Collection coll){
	if (coll == null || coll.size() == 0)
	throw new CinemarkException(Codes.INPUT_DATA_EXCEPTION_ERROR);
}


public static void validateNullOrEmpty(Object... obj){
	validateNull(obj);

	for (Object o : obj){
		validateNull(o);
		if (o instanceof String && StringUtils.isEmpty((String)o))
		throw new CinemarkException(Codes.INPUT_DATA_EXCEPTION_ERROR);
	}
}


public static void validateNull(Object obj){
	if (obj == null)
	throw new CinemarkException(Codes.INVALID_POSITION);
	}

public static String validateStringPatternForLike(String tolike){
	validateNullOrEmpty(tolike)

	tolike = StringUtils.replace(tolike, "\\", "<<to-be-replaced>>")
	tolike = StringUtils.replace(tolike, "%", "\\%")
	tolike = StringUtils.replace(tolike, "_", "\\_")
	tolike = StringUtils.replace(tolike, "<<to-be-replaced>>", "\\")

	return tolike;
}


public static void validateDate(String date, String format){
	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	Date aDate
	try{
		aDate = dateFormat.parse(date);
	}
	catch (ParseException e){
		throw new CinemarkException(Codes.INVALID_DATE);
		}
	if (!dateFormat.format(aDate).equals(date)) {
		throw new CinemarkException(Codes.INVALID_DATE);
		}
}

}