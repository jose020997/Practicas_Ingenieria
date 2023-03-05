package com.example.demo;

public class Userlogin {
	private String name;
    private String pass;
    
    // Constructor
    public Userlogin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public Userlogin() {
        this.name = "";
        this.pass = "";
    }
    
    // Getters y setters
    public String getNombre() {
        return name;
    }
    
    public void setNombre(String name) {
        this.name = name;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
}
