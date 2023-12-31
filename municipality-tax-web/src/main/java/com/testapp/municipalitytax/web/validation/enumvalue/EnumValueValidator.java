package com.testapp.municipalitytax.web.validation.enumvalue;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
  private String[] subset;

  @Override
  public void initialize(EnumValue constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value.toUpperCase());
  }
}
