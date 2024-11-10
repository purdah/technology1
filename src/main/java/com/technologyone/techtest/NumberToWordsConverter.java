package com.technologyone.techtest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private static String[] thousandsArray = {
            "", " thousand", " million", " billion", " trillion"
    };

    /**
     * Convert a string such as "123.45" into a plain text version "one hundred and twenty-three point four five
     * @param userInput The input string to convert
     * @return A string representation of the input number
     */
    public static String convert(String userInput) {

        String result;
        if (userInput.contains(".")) {
            String[] decimalAndFractional = userInput.split("\\.");
            String decimal = decimalAndFractional[0];
            String fractional = decimalAndFractional[1];

            String decimalResult = convertDecimal(decimal);
            String fractionalResult = convertFractional(fractional);
            result = decimalResult + " point " + fractionalResult;
        } else {
            result = convertDecimal(userInput);
        }

        return result;
    }

    private static String convertDecimal(String userInput) {
        List<String> groups = splitToGroupsOfThreeDigits(userInput);

        // process each group in reverse order to break it down into its component such
        // as 123 would be one hundred and twenty-three, then append a group word such as thousand, million etc
        List<String> threeDigitGroupsAsStrings = new ArrayList<>();
        for (int i = groups.size() - 1; i >= 0; i--) {
            String group = groups.get(i);
            int numberSegment = Integer.parseInt(group);
            // Note that a single group is up to the first 3 digits of the so if that value is 0 then we DO want to print
            // out zero and that is why if there is only one group we always print
            if (numberSegment != 0 || groups.size() == 1) {
                threeDigitGroupsAsStrings.add(convertThreeDigitBlock(numberSegment) + thousandsArray[i]);
            }
        }
        return threeDigitGroupsAsStrings.stream().collect(Collectors.joining(" and "));
    }

    private static List<String> splitToGroupsOfThreeDigits(String userInput) {
        List<String> groups = new ArrayList<>();

        // split to groups of 3 characters so that 2000 would become 2 strings of "2" and "000"
        for (int i = userInput.length(); i > 0; i -= 3) {
            int from = Math.max(i - 3, 0);
            String substring = userInput.substring(from, i);
            groups.add(substring);
        }
        return groups;
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


    /**
     * This method takes a string of numbers such as 1234 and lists those numbers as is: "one two three four"
     * @param fractional
     * @return
     */
    private static String convertFractional(String fractional) {
        List<String> stringBuffer = new ArrayList<>();
        for (int i = 0; i < fractional.length(); i++) {
            char ch = fractional.charAt(i);
            int numericValue = ch - '0';  // This is a little trick to convert a char between 0-9 into its integer equivalent.
            stringBuffer.add(wordsArray[numericValue]);
        }
        return stringBuffer.stream().collect(Collectors.joining(" "));
    }

}
