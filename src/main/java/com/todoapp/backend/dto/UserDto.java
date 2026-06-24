package com.todoapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("created_date")
    private String createdDate;

    @JsonProperty("modified_date")
    private String modifiedDate;

}
