package com.felipemello.auth.models;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class CredentialModel {

    private String email;

    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
