package com.technologyone.techtest.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomStringValidatorTest {


    private final CustomStringValidator customStringValidator = new CustomStringValidator();

    @BeforeEach
    void setUp() {
        // Set up the validator with min / max limits that will fail the length tests if changed
        NumberToWordsValidator annotation = mock(NumberToWordsValidator.class);
        when(annotation.min()).thenReturn(1);
        when(annotation.max()).thenReturn(30);
        customStringValidator.initialize(annotation);
    }

    @Test
    void testEmptyInput() {
        assertThat(customStringValidator.isValid("", null))
                .as("input can not be blank")
                .isFalse();
        assertThat(customStringValidator.isValid(null, null))
                .as("input can not be null")
                .isFalse();
    }

    @Test
    void testNonNumericInput() {
        assertThat(customStringValidator.isValid("a", null))
                .as("input should be a number")
                .isFalse();
    }

    @Test
    void testValidInput() {
        assertThat(customStringValidator.isValid("1", null)).isTrue();
        assertThat(customStringValidator.isValid("1234.5678", null)).isTrue();
        assertThat(customStringValidator.isValid("1234567890.1234567890123456789", null)).isTrue();
        assertThat(customStringValidator.isValid("1234567890.12345678901234567890", null))
                .as("number too long").isFalse();
        assertThat(customStringValidator.isValid("12345678901234567.8901234567890", null))
                .as("number too long").isFalse();
    }

    @Test
    void testEdgeCasesWithLeadingZeros() {
        assertThat(customStringValidator.isValid("01", null))
                .as("input can not have a leading zero")
                .isFalse();
        assertThat(customStringValidator.isValid("001", null))
                .as("input can not have a leading zero")
                .isFalse();
        assertThat(customStringValidator.isValid("01.01", null))
                .as("input can not have a leading zero")
                .isFalse();
        assertThat(customStringValidator.isValid("000", null))
                .as("input can not be all zeros")
                .isFalse();
        assertThat(customStringValidator.isValid("0000", null))
                .as("input can not be all zeros")
                .isFalse();
        assertThat(customStringValidator.isValid("0", null))
                .as("input can be a single zero")
                .isTrue();
        assertThat(customStringValidator.isValid("0.", null))
                .as("input can be a single zero")
                .isFalse();
        assertThat(customStringValidator.isValid("0.1", null))
                .isTrue();
        assertThat(customStringValidator.isValid("0.01", null))
                .isTrue();
    }

    @Test
    void testMultiplePoints() {
        assertThat(customStringValidator.isValid("0.1.2", null))
                .as("input should be a number")
                .isFalse();
    }

    @Test
    void testLargestNumber() {
        assertThat(customStringValidator.isValid("1000000000000000", null))
                .as("input should be a number")
                .isFalse();
    }

}