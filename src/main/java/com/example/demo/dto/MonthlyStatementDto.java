package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyStatementDto {


    private Long id;

    @NotBlank(message = "Month is required")
    private String month;

    @NotEmpty(message = "opening balance is required ")
    @PositiveOrZero(message = "opening balance cannot be negative")
    private Double openingBalance;
    @NotEmpty(message = "closing balance is required ")
    @PositiveOrZero(message = "closing balance cannot be negative")
    private Double closingBalance;
    @NotEmpty(message = "interestAdded  is required ")
    @PositiveOrZero(message = "interest cannot be negative")
    private Double interestAdded;
    @NotEmpty(message = "sumdeposit  is required ")
    @PositiveOrZero(message = "sumdeposit cannot be negative")
    private Double sumdeposit;
    @NotEmpty(message = "sumwithdraw  is required ")
    @PositiveOrZero(message = "sumwithdraw cannot be negative")
    private Double sumwithdraw;
    @NotEmpty(message = "sumtransfer  is required ")
    @PositiveOrZero(message = "sumtransfer cannot be negative")
    private Double sumtransfer;
    @NotNull(message = "Account ID is required")
    private Long accountId;


}
