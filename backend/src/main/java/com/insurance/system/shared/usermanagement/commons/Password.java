package com.insurance.system.shared.usermanagement.commons;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Repeatable(commons.usermanagement.shared.com.insurance.system.Password.List.class)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {
  String message() default "A valid password should be provided";
  
  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
}
