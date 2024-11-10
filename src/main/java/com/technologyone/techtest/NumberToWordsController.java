package com.technologyone.techtest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NumberToWordsController {


    @GetMapping("/input")
    public String showInputForm() {
        return "numberToWords";
    }

    @PostMapping("/submit")
    public String handleInput(@RequestParam("userInput") String userInput, Model model) {
        String response = NumberToWordsConverter.convert(userInput);
        // Pass the input to the model to display it back on the result page
        model.addAttribute("userInput", userInput);
        model.addAttribute("result", response);
        return "numberToWords";
    }

}
