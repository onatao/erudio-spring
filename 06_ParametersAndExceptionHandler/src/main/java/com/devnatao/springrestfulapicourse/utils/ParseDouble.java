package com.devnatao.springrestfulapicourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ParseDouble {
	
	@Autowired
	NumericValidator validator;
	
	/**
	 *  Will convert String number to Double
	 * @param strNumber
	 * @return double value
	 */
	public Double converToDouble(String strNumber) {
		// Replace all " , " to " . " - global pattern 
		String number = strNumber.replaceAll(",", ".");
		
		if (strNumber == null) return 0d;	
		if (validator.isNumeric(strNumber) == true) return Double.parseDouble(strNumber);
		return 0D;
	}

}
