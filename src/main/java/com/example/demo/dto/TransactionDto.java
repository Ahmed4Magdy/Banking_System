package com.example.demo.dto;


import com.example.demo.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class TransactionDto {

    private Long id;

    private Transaction.Type type;

    private double amount;

    private LocalDateTime date;

    private Long accountId;

    private Long targetAccountId;

}
