package com.technologyone.techtest;

public class NumberToWordsConverter {

    // List of words for numbers 0 to 99
    private static String[] wordsArray = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two",
            "twenty-three", "twenty-four", "twenty-five", "twenty-six", "twenty-seven",
            "twenty-eight", "twenty-nine", "thirty", "thirty-one", "thirty-two", "thirty-three",
            "thirty-four", "thirty-five", "thirty-six", "thirty-seven", "thirty-eight",
            "thirty-nine", "forty", "forty-one", "forty-two", "forty-three", "forty-four",
            "forty-five", "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty",
            "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five", "fifty-six",
            "fifty-seven", "fifty-eight", "fifty-nine", "sixty", "sixty-one", "sixty-two",
            "sixty-three", "sixty-four", "sixty-five", "sixty-six", "sixty-seven", "sixty-eight",
            "sixty-nine", "seventy", "seventy-one", "seventy-two", "seventy-three",
            "seventy-four", "seventy-five", "seventy-six", "seventy-seven", "seventy-eight",
            "seventy-nine", "eighty", "eighty-one", "eighty-two", "eighty-three", "eighty-four",
            "eighty-five", "eighty-six", "eighty-seven", "eighty-eight", "eighty-nine", "ninety",
            "ninety-one", "ninety-two", "ninety-three", "ninety-four", "ninety-five",
            "ninety-six", "ninety-seven", "ninety-eight", "ninety-nine"
    };

    public static String convert(String userInput) {

        int number = Integer.parseInt(userInput);
        return convertThreeDigitBlock(number);

    }

    private static String convertThreeDigitBlock(int number) {
        assert number >= 0 && number < 1000;
        if (number < 100) {
            return wordsArray[number];
        }
        int hundreds = number / 100;
        int units = number % 100;
        if (units == 0) {
            return wordsArray[hundreds] + " hundred";
        } else {
            return wordsArray[hundreds] + " hundred and " + wordsArray[units];
        }
    }

}
