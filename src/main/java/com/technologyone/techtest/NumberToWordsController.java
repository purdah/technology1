package com.technologyone.techtest;

import com.technologyone.techtest.validation.NumberToWordsValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;


@Controller
@Validated
public class NumberToWordsController {


    @GetMapping("/input")
    public String showInputForm() {
        return "numberToWords";
    }

    @PostMapping("/submit")
    public String handleInput(@RequestParam("userInput") @NumberToWordsValidator(min = 1, max = 30, message = "Input must be 1-30 characters and be a valid number.") String userInput, RedirectAttributes redirectAttributes) {
        String response = NumberToWordsConverter.convert(userInput);

        // Pass the input to the model to display it back on the result page
        redirectAttributes.addFlashAttribute("userInput", userInput);
        redirectAttributes.addFlashAttribute("result", response);
        return "redirect:/input"; // Redirect back to the /input URL
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationException(ConstraintViolationException ex, RedirectAttributes redirectAttributes) {
        Iterator<ConstraintViolation<?>> errorIterator = ex.getConstraintViolations().iterator();
        String message;
        if (errorIterator.hasNext()) {
            message = errorIterator.next().getMessage();
        } else {
            message = "Invalid Input, please confirm the input is a valid number";
        }
        redirectAttributes.addFlashAttribute("error", message);
        return "redirect:/input";
    }
}
