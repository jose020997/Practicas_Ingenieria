package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class DAO_jabg0014 {
    private List<Userlogin> users;

    public DAO_jabg0014() {
        // Initialize the list of users in the constructor
        users = new ArrayList<>();
        users.add(new Userlogin("Juan", "password1"));
        users.add(new Userlogin("Maria", "password2"));
        users.add(new Userlogin("Pedro", "password3"));
        users.add(new Userlogin("Ana", "password4"));
        users.add(new Userlogin("Luis", "password5"));
        users.add(new Userlogin("123", "123"));
    }

    public List<Userlogin> getAllUsers() {
        return users;
    }

    public void addUser(Userlogin user) {
        users.add(user);
    }
}
