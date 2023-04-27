package com.devnatao.springrestfulapicourse.utils;

import org.springframework.stereotype.Component;

@Component
public class NumericValidator {
	
	/**
	 * Verify if param is one numeric value
	 * @param number
	 * @return true if number is numeric, false if isnt
	 */
	public boolean isNumeric(String number) {
		if (number == null) return false;
		// Replace all " , " to " . " - global pattern 
		String n = number.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");		
	}
}
