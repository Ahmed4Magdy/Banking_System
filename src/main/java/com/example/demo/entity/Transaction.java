package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double amount;

    private LocalDateTime date;

    public enum Type {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
