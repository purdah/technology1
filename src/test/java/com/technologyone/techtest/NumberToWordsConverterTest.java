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


}