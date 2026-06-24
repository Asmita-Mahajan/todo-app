package com.todoapp.backend.mapper;


import com.todoapp.backend.dto.UserDto;
import com.todoapp.backend.model.Task;
import com.todoapp.backend.model.User;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    //Entity -> DTO
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate")
    UserDto toDto(User user);

    //DTO-> Entity
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate")
    User toEntity(UserDto userDto);

    //list conversion
    List<UserDto> toDtoList(List<User> entities);
    List<User> toEntityList(List<UserDto> dtos);

    // Example of a business rule after mapping
    @AfterMapping
    default void applyBusinessRules(@MappingTarget User entity) {
        if (entity.getUsername() != null) {
            entity.setUsername(entity.getUsername().trim()); // trim whitespace
        }
    }
    // PATCH-style update (ignore nulls)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate")
    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);
}
