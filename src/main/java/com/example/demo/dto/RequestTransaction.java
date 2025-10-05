package com.example.demo.dto;


import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RequestTransaction {

    private Long id;

    private Transaction.Type type;

    private double amount;

    private LocalDateTime date;

    private Long accountId;

    private Long targetAccountId;

}
