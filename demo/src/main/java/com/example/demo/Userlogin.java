package com.example.demo;

public class Userlogin {
	private String name;
    private String pass;
    private int es_user;
    
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

	public int getEs_user() {
		return es_user;
	}

	public void setEs_user(int es_user) {
		this.es_user = es_user;
	}
}
