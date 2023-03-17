package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

//@Repository
public class DAO_jabg0014 implements ServicioJabg0014Interface {
    private List<Userlogin> users;
    private boolean isAdmin;

    public DAO_jabg0014() {
        // Initialize the list of users in the constructor
        users = new ArrayList<>();
        users.add(new Userlogin("Juan", "password1"));
        users.add(new Userlogin("Maria", "password2"));
        users.add(new Userlogin("Pedro", "password3"));
        users.add(new Userlogin("Ana", "password4"));
        users.add(new Userlogin("Luis", "password5"));
        users.add(new Userlogin("123", "123"));
        isAdmin = false;
    }

    public List<Userlogin> getAllUsers() {
        return users;
    }

    public void addUser(Userlogin user) {
        users.add(user);
    }
    public boolean checkuser(String username,String pass) {
    	
    	if(username.equals("root")&& pass.equals("root")) {
    		isAdmin=true;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
   
}
