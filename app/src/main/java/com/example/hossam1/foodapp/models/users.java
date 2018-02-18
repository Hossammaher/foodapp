package com.example.hossam1.foodapp.models;

/**
 * Created by hossam1 on 2/16/18.
 */

public class users {

    String id ;
    String email ;
    String name ;
    String password ;

    public users() {
    }

    public users(String id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
