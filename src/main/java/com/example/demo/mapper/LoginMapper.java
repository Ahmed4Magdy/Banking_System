package com.example.demo.mapper;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {


    LoginResponseDto toLoginResponseDto(User user);


}
