package com.example.demo.mapper;


import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface TransactionMapper {


    @Mapping(target = "accountId", source = "account.account_id")
    TransactionDto toDTO(Transaction transaction);

    //    @InheritInverseConfiguration
    @Mapping(target = "account", ignore = true)
    Transaction toEntity(TransactionDto transactionDTO);


}
