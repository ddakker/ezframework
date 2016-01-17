package pe.kr.ddakker.ezframework.web.validation.constraints.validator;

import java.util.regex.Matcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pe.kr.ddakker.ezframework.web.validation.constraints.Mobile;

public class MobileValidator implements ConstraintValidator<Mobile, String>  {
	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");

    public void initialize(Mobile annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
