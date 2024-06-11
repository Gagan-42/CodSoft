package com.example.currency_converter.service;
import com.example.currency_converter.model.ExchangeRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConverterService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public ExchangeRate getExchangeRates(String baseCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + baseCurrency;
        return restTemplate.getForObject(url, ExchangeRate.class);
    }

    public Map<String, Double> getCurrencyRates(String baseCurrency) {
        return getExchangeRates(baseCurrency).getRates();
    }

    public double convertCurrency(double amount, double rate) {
        return amount * rate;
    }
}
