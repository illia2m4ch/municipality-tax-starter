package com.testapp.municipalitytax.web.validation.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DatePatternValidator.class)
@Documented
public @interface DatePattern {
    String message() default "Invalid date";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String pattern();
}