package com.khanhdv.spring.jwt.common.annotion;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Required Not Blank Validate
 *
 */
public class RequiredNotBlankValidator implements ConstraintValidator<Required, String> {

	public void initialize(Required parameters) {
		validateParameters();
	}

	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isNotBlank(value)) {
			return true;
		} else {
			return false;
		}
	}

	private void validateParameters() {
		// Do nothing because don't have value need initialize
	}
}
