package com.example.demo.mapper;

import com.example.demo.dto.SignupDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupMapper {


//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "role", ignore = true)
//    @Mapping(target = "account",ignore = true)
    User toEntity(SignupDto dto);


    @Mapping(target = "password", ignore = true)
    SignupDto toDto(User user);


}
