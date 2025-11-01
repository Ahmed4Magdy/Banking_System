package com.example.demo.dto;


import com.example.demo.entity.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class TransactionDto {

    private Long id;


    @NotNull(message = "Transaction type is required")
    private Transaction.Type type;

    @Positive(message = "Amount must be positive")
    private double amount;

//    @NotNull(message = "Transaction date is required")
    private LocalDateTime date;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    private Long targetAccountId;

}
