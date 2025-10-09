package com.example.demo.mapper;

import com.example.demo.dto.MonthlyStatementDto;
import com.example.demo.entity.MonthlyStatement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface MonthlyMapper {

    @Mapping(target = "accountId", source = "account.account_id")
    MonthlyStatementDto toDTO(MonthlyStatement statement);

//    @InheritInverseConfiguration
    @Mapping(target = "account", ignore = true)//هخزنها manual
    MonthlyStatement toEntity(MonthlyStatementDto dto);


}
