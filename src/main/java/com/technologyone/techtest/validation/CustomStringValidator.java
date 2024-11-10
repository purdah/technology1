package com.technologyone.techtest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.technologyone.techtest.TechtestApplication.MAX_VALUE;

public class CustomStringValidator implements ConstraintValidator<NumberToWordsValidator, String> {
    private static final Logger logger = LoggerFactory.getLogger(CustomStringValidator.class);
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
            logger.debug("Input value is null");
            return false; // Null is considered invalid
        }

        // Example validation: length check and custom logic (only letters and spaces)
        if (value.length() < min || value.length() > max) {
            logger.debug("Input value [length={}] is too short or long. It must be at least {} characters long, and no more than {} characters long", value.length(), min, max);
            return false;
        }

        if (value.endsWith(".") || value.startsWith(".")) {
            logger.debug("Input value starts or ends with a period character '.'");
            return false;
        }

        if (value.equals("0")) {
            return true;
        }

        if (value.startsWith("0") && value.charAt(1) != '.') {
            logger.debug("Input value has an extra leading zero");
            return false;
        }

        try {
            Float floatValue = Float.parseFloat(value);
            if (floatValue.compareTo(MAX_VALUE) >= 0) {
                logger.debug("Input value will exceed the maximum allowed value of {}", MAX_VALUE - 1);
                return false;
            }
        } catch (NumberFormatException e) {
            logger.debug("Input value is not a valid number");
            return false;
        }

        return true;
    }
}