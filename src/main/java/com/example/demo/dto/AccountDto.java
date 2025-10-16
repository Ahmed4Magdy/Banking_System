package com.example.demo.dto;

import com.example.demo.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDto {

    private Long account_id;
    @NotBlank(message = "account_number  cannot be blank")
    private String account_number;
    @Positive(message = "Balance must be greater than zero")
    private double account_balance;
    @NotNull(message = "Account type is required") //Because type is an enum field, not a String or List.
    private Account.accountType type;
    @NotNull(message = "User ID is required")
    private Long userId;


}
