package com.technologyone.techtest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomStringValidator implements ConstraintValidator<NumberToWordsValidator, String> {

    private int min;
    private int max;

    @Override
    public void initialize(NumberToWordsValidator constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null is considered invalid
        }

        // Example validation: length check and custom logic (only letters and spaces)
        if (value.length() < min || value.length() > max) {
            return false;
        }

        // Check for allowed characters such that the input can only represent a number
        return value.matches("[0-9.]+");
    }
}