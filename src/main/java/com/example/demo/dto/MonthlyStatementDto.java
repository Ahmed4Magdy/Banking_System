package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyStatementDto {


    private Long id;

    private String month;

    private Double openingBalance;
    private Double closingBalance;
    private Double interestAdded;
    private Double sumdeposit;
    private Double sumwithdraw;
    private Double sumtransfer;

    private Long accountId;


}
