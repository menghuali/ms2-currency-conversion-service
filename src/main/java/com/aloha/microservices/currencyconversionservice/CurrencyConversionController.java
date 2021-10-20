package com.aloha.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convert(@PathVariable("from") String from, @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity) {
        BigDecimal conversionMultiplier = BigDecimal.valueOf(75.0f);
        return CurrencyConversion.builder().id(1001l).from(from).to(to).conversionMultiplier(conversionMultiplier)
                .quantity(quantity).totalCalculatedAmount(quantity.multiply(conversionMultiplier))
                .environment(environment.getProperty("local.server.port")).build();
    }

}
