package com.example.neosave.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String pinCode;
    private String stateName;

    public User(String name, String email, String pinCode, String stateName) {
        this.name = name;
        this.email = email;
        this.pinCode = pinCode;
        this.stateName = stateName;
    }
}
