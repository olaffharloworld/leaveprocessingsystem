package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.User;


@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User us=(User) target;
		ValidationUtils.rejectIfEmpty(errors, "userId", "error.user.userId.empty");
		ValidationUtils.rejectIfEmpty(errors, "name", "error.user.name.empty");
		System.out.println(us.toString());
	}

}
