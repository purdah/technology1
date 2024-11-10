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

        if (value.endsWith(".") || value.startsWith(".")) {
            return false;
        }

        if (value.equals("0")) {
            return true;
        }

        if (value.startsWith("0") && value.charAt(1) != '.') {
            return false;
        }

        try {
            Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}