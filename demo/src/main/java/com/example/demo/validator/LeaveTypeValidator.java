package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.LeaveType;


@Component
public class LeaveTypeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveType lt=(LeaveType) target;
		ValidationUtils.rejectIfEmpty(errors, "typeId", "error.leavetype.typeId.empty");
		ValidationUtils.rejectIfEmpty(errors, "type", "error.leavetype.type.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "error.leavetype.description.empty");
		System.out.println(lt.toString());
	}

}
