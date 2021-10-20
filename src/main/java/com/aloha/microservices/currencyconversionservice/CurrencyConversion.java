package com.aloha.microservices.currencyconversionservice;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CurrencyConversion {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiplier;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

    public CurrencyConversion(CurrencyExchange exchg, BigDecimal quantity, String environment) {
        this.id = exchg.getId();
        this.from = exchg.getFrom();
        this.to = exchg.getTo();
        this.conversionMultiplier = exchg.getConversionMultiplier();
        this.quantity = quantity;
        this.totalCalculatedAmount = quantity.multiply(conversionMultiplier);
        this.environment = environment;
    }

}
