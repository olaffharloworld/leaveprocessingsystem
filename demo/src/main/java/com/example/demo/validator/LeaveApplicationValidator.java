package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.LeaveApplication;




@Component
public class LeaveApplicationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveApplication la=(LeaveApplication) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "error.leaveapplication.fromDate.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "error.leaveapplication.toDate.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveType", "error.leaveapplication.leaveType.empty");
		System.out.println(la.toString());
	}

}
