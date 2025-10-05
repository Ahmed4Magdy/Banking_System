package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestMonthly {


    private Long id;

    private String month;

    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
    private BigDecimal interestAdded;

    private Long accountId;


}
