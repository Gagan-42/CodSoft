package com.example.currency_converter.controller;
import com.example.currency_converter.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @GetMapping("/")
    public String index(Model model) {
        Map<String, Double> currencyRates = currencyConverterService.getCurrencyRates("USD");
        model.addAttribute("currencies", currencyRates.keySet());
        return "index";
    }

    @GetMapping("/convert")
    public String convertCurrency(@RequestParam String base,
                                  @RequestParam String target,
                                  @RequestParam double amount,
                                  Model model) {
        Map<String, Double> currencyRates = currencyConverterService.getCurrencyRates(base);
        Double targetRate = currencyRates.get(target.toUpperCase());
        if (targetRate != null) {
            double convertedAmount = currencyConverterService.convertCurrency(amount, targetRate);
            String result = String.format("%.2f %s", convertedAmount, target.toUpperCase());
            model.addAttribute("result", result);
        } else {
            model.addAttribute("result", "Invalid target currency!");
        }
        model.addAttribute("currencies", currencyRates.keySet());
        return "index";
    }
}
