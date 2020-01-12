package com.felipemello.auth.models;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignInModel {
    
    private String email;

    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
