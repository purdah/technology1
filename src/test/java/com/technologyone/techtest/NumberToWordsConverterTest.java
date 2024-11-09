package com.technologyone.techtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberToWordsConverterTest {

    @Test
    public void testconvert_SingleDigit() {
        assertThat(NumberToWordsConverter.convert("5"))
                  .isEqualTo("five");
        assertThat(NumberToWordsConverter.convert("0"))
                  .isEqualTo("zero");
    }

    @Test
    public void testconvert_Tens() {
        Assertions.assertThat(NumberToWordsConverter.convert("23"))
                  .isEqualTo("twenty-three");
        Assertions.assertThat(NumberToWordsConverter.convert("99"))
                  .isEqualTo("ninety-nine");
    }

    @Test
    public void testconvert_Hundreds() {
        Assertions.assertThat(NumberToWordsConverter.convert("100"))
                  .isEqualTo("one hundred");
        Assertions.assertThat(NumberToWordsConverter.convert("305"))
                  .isEqualTo("three hundred and five");
        Assertions.assertThat(NumberToWordsConverter.convert("999"))
                  .isEqualTo("nine hundred and ninety-nine");
    }

}