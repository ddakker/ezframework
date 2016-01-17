package pe.kr.ddakker.ezframework.web.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import pe.kr.ddakker.ezframework.web.validation.constraints.validator.MobileValidator;


@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
@Size(min = 12, max = 13)
public @interface Mobile {
	String message() default "{validation.constraint.Mobile.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
