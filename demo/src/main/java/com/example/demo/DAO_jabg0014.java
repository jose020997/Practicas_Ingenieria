package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class DAO_jabg0014 {
	private static List<Userlogin> users = new ArrayList<>();
	
	public void UserDao() {
        // Add some example users to the list
        users.add(new Userlogin("Juan", "password1"));
        users.add(new Userlogin("Maria", "password2"));
        users.add(new Userlogin("Pedro", "password3"));
        users.add(new Userlogin("Ana", "password4"));
        users.add(new Userlogin("Luis", "password5"));
    }
	
	public List<Userlogin> getAllUsers() {
        return users;
    }
	
	public void addUser(Userlogin user) {
        users.add(user);
    }
	
	
}
