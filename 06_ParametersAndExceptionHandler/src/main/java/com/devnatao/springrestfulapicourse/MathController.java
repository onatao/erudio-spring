package com.devnatao.springrestfulapicourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.springrestfulapicourse.exceptions.UnsupportedMathOperationException;
import com.devnatao.springrestfulapicourse.utils.NumericValidator;
import com.devnatao.springrestfulapicourse.utils.ParseDouble;

@RestController
public class MathController {
	
	@Autowired
	ParseDouble toDouble;
	
	@Autowired
	NumericValidator validator;

	@RequestMapping(value = "/sum/{n1}/{n2}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "n1") String numberOne, @PathVariable(value = "n2") String numberTwo) throws Exception {
		// verifying n1 and n2 (must be numeric)
		if (!validator.isNumeric(numberOne) || !validator.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return toDouble.converToDouble(numberOne) + toDouble.converToDouble(numberTwo);
	}


	@RequestMapping(value = "/min/{n1}/{n2}")
	public Double min(@PathVariable(value = "n1") String numberOne, @PathVariable(value = "n2") String numberTwo) {
		if (!validator.isNumeric(numberOne) || !validator.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return  toDouble.converToDouble(numberOne) - toDouble.converToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/times/{n1}/{n2}")
	public Double times(@PathVariable(value = "n1") String numberOne, @PathVariable(value = "n2") String numberTwo) {
		if (!validator.isNumeric(numberOne) || !validator.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return toDouble.converToDouble(numberOne) * toDouble.converToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/divide/{n1}/{n2}")
	public Double divide(@PathVariable(value = "n1") String numberOne, @PathVariable(value = "n2") String numberTwo) {
		if (!validator.isNumeric(numberOne) || !validator.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return toDouble.converToDouble(numberOne) / toDouble.converToDouble(numberTwo);
	}
	
	

	
}
