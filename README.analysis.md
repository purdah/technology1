# Analysis of the original problem definition.

The initial reading of the specification has found some inconsistencies that should be clarified and would normally
require a discussion with an appropriate project owner.

1. The description of the problem mentions 'numerical input' but the example output describes currency.
2. For currencies there is no mention of how to deal with fractional currencies, should 50 be 50 cents or 'half a
   dollar'
3. Is entering 0.5 valid? Should it be converted to imply 50 cents in the case of a currency
4. Some currencies are metric such as Pounds Shilling and Pence, would this app be expected to deal with such?
5. How should fractional currencies be treated. Should 1.2345 be 'one dollar and twenty-three point four five cents'
   or should it be rounded or just chopped.
6. What are the maximum and minimum values and how many decimal places are supported.
7. Should currency symbols be allowed and used during parsing
8. How to deal with zero values, ie 0 is 'zero', but is 0.0 the same or 'zero dollars and zero cents' 
9. 0.01 could be just 'one cent' or 'zero dollars and one cent' which is the preferred choice. 
10. No mention of negative values with negative zero being a potential for special handling. Should negative
    currencies be named as 'debit' amounts? 
11. There is no mention of internationalization which can dramatically affect how numbers are written. 
12. If currencies are to be supported should American or British definition of 'Trillion' be used. 
13. Should the app support mathematical notation such as 10^3 being reported as 'one thousand' 
14. Should the input be limited to base 10 numbers and not support other bases such as hexidecimal 
15. Numbers should not start with a zero unless it is the only digit before the decimal point, ie 0 or 0.0 is valid but
    values such as 00 or 00.0, or 01.0 are not valid.
16. Should we allow for comma ',' charaters to be used, what happens if the commas are in the incorrect place

# Assumptions

No internationalization considerations are required

Supporting multiple currencies is out of scope.

Assuming Dollars/Cents should be optional as the minimum viable product is simply converting numbers rather 
than assuming currencies.

The short scale for displaying numbers will be used, ie:

1. 10^0 = units
2. 10^2 = hundreds
3. 10^3 = thousands
4. 10^6 = millions
5. 10^9 = billions
6. 10^12 = trillion

Error reporting / observability requirements are not mentioned. In the initial implementation a simple logging framework
that logs to the console will be assumed as the minimum required as long as the logging mechanism supports logging to
an environment compatible framework such as Datadog or a cloud native implementation such as AWS Cloudwatch.

There is no mention of scaling the application. Scaling can dramatically affect the application, no one wants to spend
$100,000 a month just to store log messages that may never be read.

As negative numbers are not mentioned they will not be supported in this implementation.

Fractional parts will not be limited and can be up to the maximum allowable length of user input.

If the last digits of the fractional part of the number are 0, they will be reported as is, ie 0.100 is reported as
`zero point one zero zero` and not `zero point one`

# Test Plan

To test this application the following tests should be added:

1. Unit Tests to ensure the basic functionality 
2. Create a black box acceptance test that should be ideally automated using the toolset that the testing team is
   skilled with. 
3. Test all numbers from zero to 100 so that the correct names for 11 is eleven and not 'ten and one' 
4. Test all prefixes such as thousand, million, billion etc 
5. Test that fractions do not use prefixes, instead they should be reported as individual figures, ie 0.452 should
   `zero point four five two` 
6. Test invalid numbers such as 01, -01, -0 or 00, or values that exceed the input limits both positive and negative. 
7. Test combinations around boundaries, eg 1123 is one thousand one hundred and twenty-three and not `eleven hundred
   and twenty-three`
8. Test skipped boundaries, 1000023 is 'one million and twenty-three' and not 'one million zero thousands and
   zero hundreds and twenty-three'. Also test unreported boundaries such as 1000000 is just 'one million'

# Initial Tasks

1. Create a basic template for a web application using Spring Boot to serve a non interactive html page
2. Add the ability to input a number and have it displayed back to the user
3. Add error handling such that errors go back to the original page with an error message indicating the input
4. Add integration tests that allow the testing that ensure the web page wiring returns to the correct web page.
5. Create size limitations on the input box, do not report excessively large inputs back on the input page.
   It would be great to have the nature of the error reported against the input text box with the box highlighted in red
   with a red text message indicating the nature of the error.
6. Create the ability to report on the first 100 numbers
7. Create the ability to report on fractional numbers.


# Problem analysis

There are many ways in which a problem such as this can be solved. The main choices for solving this are via
recursive or iterative. For OO Languages such as Java and C# there is a preference for iterative approaches.

Both languages have stack size limitations that potentially limit the number of recursions although in practice the
limit can be adjusted and that should not be an issue to solve this problem given that the stack starts in the many
thousands of entries. This would mean on a practical level that user inputs would need to exceed 10000 digits or more
and likely out of scope for such an application.

To stay as idiomatic to C# and Java languages I have chosen to use the imperative approach as recursion is not
as common a way to solve problems.

Initial support is for values up to Trillions, values that can not be expressed as whole values of Trillions will be
reported as too large, ie the app will deal with 999 trillion, but not one thousand trillion or more.

The currency part of the problem space will be ignored mostly due to there being some requirements around currency
handling that need more clarification. However, digging down into the handling of fractions of cents shows that
the more generic number handling is still required, ie 0.045 would be 'four point five cents', so the minimum value
that will be delivered will start with just breaking down numbers to fractions.

Reconfiguring the application to support currencies can then be overlaid at a later date once the requirements have
been clarified.

When processing generic numbers there is a need to report on whole numbers and fractions such as 1000 and
0.001, this requires that there is a split required on the left and right sides of the decimal point with both sides
of the decimal point being treated differently.

When looking at the problem the first 100 numbers have some complexities in forming correct syntax. For most numbers
such as 21, 31 and 41 follow a pattern of hyphenating but 11 does not follow the same pattern. Given the generous
memory limits on most modern hardware I have chosen to go with a hard coded list of the first 100 numbers starting
from Zero.

This is a good compromise between memory usage and more complex code that deals with trying to add hyphenation. The map
can be reused for numbers within boundaries, such as 23000 which is reported as `twenty-three thousand`.

This is an example of the flyweight pattern where the values are pre-computed and reused. In this case there is little
saved in terms of compute power, but the reduction in code complexity is far more valuable in development hours in
terms of support and debugging activities.

The basic approach is to split on the decimal point if it exists.

The right hand side of the decimal point will be reported as just a stream of numbers, ie 0.1234 will be `zero point
one two three four`

The left hand side of the split, or the whole number if there is no decimal point, will be broken down into 3 digit
segments starting from the right hand part of the string.

EG, the number `1234567` would be split into `1`, `234` and `567` and the same algorithm applied to each segment
to build up an interim result: `one`, `two hundred and thirty-four`, `five hundred and sixty-seven`.

That list would then have the thousand boundary name appended: `one million`, `two hundred and thirty-four thousand`,
`five hundred and sixty-seven`

Finally, the 3 strings are joined together with ` and ` as the separator leading to a result of:

`one million and two hundred and thirty-four thousand and five hundred and sixty-seven`

This leads to the following high level algorithm:

1. Split the number on decimal point if it exists
2. break down the number string to the left of the decimal into groups of units, thousands, millions,
   billions and trillions.
3. test each boundary to confirm that there is a non-zero number in that boundary, skip reporting on the boundary
   if it is zero. Note the special case of just `0` as the input which can not be skipped.
4. Build up an output string based on the groups starting from the most significant value group to least.
5. Each group can use a single method to deal with breaking down to hundreds and units without the group
   identifier, ie 99 is "ninety-nine"
6. Append a thousand identifier such as ' thousand' or ' million'
7. concatenate the values together to form the first part of the output.
8. If there is a decimal point append the fractional digits one at a time left to right.

## Validation

As the application is not initially to be used as a library, the input validation is performed as part of the web
request. If the validation fails then an error message with details of the error is displayed on the web page so the
conversion code should not need extensive error validation and handling of input. If errors are thrown inside the
conversion then the web application will go to the default error page with details in the logs of what happened.

By keeping the error handling in one place it is possible to keep separation of concerns so that all errors can be
tested before the need to handle them in the low level converter code.

If the number converter was to be separated into its own library then specific number conversion exceptions should
be added and handled in a more generic fashion.
