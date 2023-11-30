package com.testapp.municipalitytax.web.validation.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DatePatternValidator implements ConstraintValidator<DatePattern, String> {

  private DateFormat dateFormat;

  @Override
  public void initialize(DatePattern constraintAnnotation) {
    var pattern = constraintAnnotation.pattern();
    this.dateFormat = new SimpleDateFormat(pattern);
  }

  @Override
  public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    if (object == null) {
      return true;
    }

    try {
      dateFormat.parse(object);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
