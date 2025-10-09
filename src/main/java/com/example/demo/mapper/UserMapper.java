package com.example.demo.mapper;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password" ,ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "account", ignore = true)
    User toEntity(UserDto dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);


}
