package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    private String account_number;

    private double account_balance;

    @Enumerated(EnumType.STRING)
    private accountType type;

    private LocalDate createdAt = LocalDate.now();




    public enum accountType {
        SAVINGS, CURRENT
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    Set<Transaction> transaction = new HashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    Set<MonthlyStatement> monthlyStatements = new HashSet<>();

}

