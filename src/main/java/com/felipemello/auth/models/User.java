package com.felipemello.auth.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@Document(collection = "user")
public class User {

    @NotNull(message = "Id is mandatory")
    @Id
    private ObjectId id;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotNull(message = "User type is mandatory")
    private UserType userType;

    public static class UserBuilder {
	public UserBuilder id(String id) {
	    this.id = id != null ? new ObjectId(id) : null;
	    return this;
	}

    }

}
