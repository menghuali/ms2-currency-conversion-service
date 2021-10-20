package com.aloha.microservices.currencyconversionservice;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
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

}
