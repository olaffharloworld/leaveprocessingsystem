package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.model.LeaveBalance;
@Component
public class LeaveBalanceValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveBalance.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveBalance lb=(LeaveBalance) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "error.leavebalance.typeId.empty");
		ValidationUtils.rejectIfEmpty(errors, "type", "error.leavebalance.type.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "balance", "error.leavebalance.balance.empty");
		System.out.println(lb.toString());

	}

}
