package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.PublicHoliday;


@Component
public class PublicHolidayValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PublicHoliday.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PublicHoliday ph=(PublicHoliday) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "error.publicholiday.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "error.publicholiday.description.empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "error.publicholiday.date.empty");
		 
		System.out.println(ph.toString());
	}

}
