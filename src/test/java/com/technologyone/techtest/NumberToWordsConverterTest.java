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

    @Test
    public void testconvert_thousands() {
        Assertions.assertThat(NumberToWordsConverter.convert("2000"))
                  .isEqualTo("two thousand");
        Assertions.assertThat(NumberToWordsConverter.convert("3050"))
                  .isEqualTo("three thousand and fifty");
        // TODO: how should this be 'nine thousand nine hundred and ninety-nine'
        Assertions.assertThat(NumberToWordsConverter.convert("9999"))
                  .isEqualTo("nine thousand and nine hundred and ninety-nine");
    }

    @Test
    public void testconvert_TenThousands() {
        Assertions.assertThat(NumberToWordsConverter.convert("20000"))
                  .isEqualTo("twenty thousand");
        Assertions.assertThat(NumberToWordsConverter.convert("30050"))
                  .isEqualTo("thirty thousand and fifty");
        Assertions.assertThat(NumberToWordsConverter.convert("30400"))
                  .isEqualTo("thirty thousand and four hundred");
        Assertions.assertThat(NumberToWordsConverter.convert("99999"))
                  .isEqualTo("ninety-nine thousand and nine hundred and ninety-nine");
    }

    @Test
    public void testconvert_HundredThousands() {
        Assertions.assertThat(NumberToWordsConverter.convert("200000"))
                  .isEqualTo("two hundred thousand");
        Assertions.assertThat(NumberToWordsConverter.convert("300050"))
                  .isEqualTo("three hundred thousand and fifty");
        Assertions.assertThat(NumberToWordsConverter.convert("300400"))
                  .isEqualTo("three hundred thousand and four hundred");
        Assertions.assertThat(NumberToWordsConverter.convert("999999"))
                  .isEqualTo("nine hundred and ninety-nine thousand and nine hundred and ninety-nine");
    }
    @Test
    public void testconvert_Millions() {
        Assertions.assertThat(NumberToWordsConverter.convert("2000000"))
                  .isEqualTo("two million");
        Assertions.assertThat(NumberToWordsConverter.convert("3000050"))
                  .isEqualTo("three million and fifty");
        Assertions.assertThat(NumberToWordsConverter.convert("3000400"))
                  .isEqualTo("three million and four hundred");
        Assertions.assertThat(NumberToWordsConverter.convert("9999999"))
                  .isEqualTo("nine million and nine hundred and ninety-nine thousand and nine hundred and ninety-nine");
    }

}