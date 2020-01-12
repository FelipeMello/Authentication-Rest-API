package com.felipemello.auth.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class SignUpModel {

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "User type is mandatory")
    private UserType userType;
}
