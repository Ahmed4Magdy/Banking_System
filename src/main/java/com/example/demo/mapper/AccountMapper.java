package com.example.demo.mapper;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "userId", source = "user.id")
    AccountDto toDto(Account account);

    @Mapping(target = "transaction", ignore = true)
    @Mapping(target = "monthlyStatements", ignore = true)
    Account toEntity(AccountDto dto);



    @Mapping(target = "account_id", ignore = true)
    @Mapping(target = "user",ignore = true)
    void updateAccountFromDto(AccountDto dto, @MappingTarget Account entity);

}
