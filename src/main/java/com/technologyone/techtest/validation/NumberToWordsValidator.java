package com.technologyone.techtest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomStringValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberToWordsValidator {
    String message() default "Invalid input";  // Default error message

    Class<?>[] groups() default {}; // Required by validation API

    Class<? extends Payload>[] payload() default {}; // Required by validation API

    // Add custom parameters if needed, e.g., length limits
    int min() default 1;

    int max() default 30;
}