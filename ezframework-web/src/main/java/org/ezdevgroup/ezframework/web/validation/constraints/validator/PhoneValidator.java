package org.ezdevgroup.ezframework.web.validation.constraints.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.ezdevgroup.ezframework.web.validation.constraints.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String>  {
	private String defaultJoin;
    public void initialize(Phone annotation) {
    	System.out.println("annotation.test(): " + annotation.defaultJoin());
    	defaultJoin = annotation.defaultJoin();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d{2,3}" + defaultJoin + "\\d{3,4}-\\d{4}$");
        
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
