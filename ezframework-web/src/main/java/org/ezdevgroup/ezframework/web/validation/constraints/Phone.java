package org.ezdevgroup.ezframework.web.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import org.ezdevgroup.ezframework.web.validation.constraints.validator.PhoneValidator;


@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Size(min = 11, max = 13)
public @interface Phone {
	String message() default "{validation.constraint.Phone.message}";
	String defaultJoin() default "-";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
