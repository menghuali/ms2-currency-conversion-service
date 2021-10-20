package com.aloha.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/currency-conversion")
@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/resttemplate/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convert(@PathVariable("from") String from, @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity) {
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("from", from);
        uriParams.put("to", to);
        ResponseEntity<CurrencyExchange> resp = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyExchange.class, uriParams);
        CurrencyExchange exchg = resp.getBody();
        return new CurrencyConversion(exchg, quantity, environment.getProperty("local.server.port"));
    }

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertFeign(@PathVariable("from") String from, @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity) {
        CurrencyExchange exchg = currencyExchangeProxy.getCurrencyExchange(from, to);
        return new CurrencyConversion(exchg, quantity, environment.getProperty("local.server.port"));
    }

}
