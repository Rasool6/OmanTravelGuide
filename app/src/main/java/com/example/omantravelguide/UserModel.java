package com.example.omantravelguide;

public class UserModel {
   public String id;
   public String name;
   public String email;
   public String password;

    public UserModel(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
