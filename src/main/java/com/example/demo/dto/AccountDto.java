package com.example.demo.dto;

import com.example.demo.entity.Account;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDto {

    private Long account_id;
    private String account_number;
    private double account_balance;
    private Account.accountType type;
    private Long userId;


}
