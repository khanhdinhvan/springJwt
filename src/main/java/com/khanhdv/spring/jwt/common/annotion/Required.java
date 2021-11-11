package com.khanhdv.spring.jwt.common.annotion;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
@NotNull
@Size(min = 1)
public @interface Required
{

	String name() default "";

	String message() default "{name} {error.message.common.notEmpty}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@code @NotEmpty} annotations on the same element.
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public @interface List
	{
		Required[] value();
	}

}
