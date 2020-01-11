package com.felipemello.auth.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@Document(collection ="user")
public class User {

    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String nickName;

    public String getId() {
	return id != null ? id.toHexString() : null;
    }

    public void setId(String id) {
	this.id = id != null ? new ObjectId(id) : null;
    }

}

