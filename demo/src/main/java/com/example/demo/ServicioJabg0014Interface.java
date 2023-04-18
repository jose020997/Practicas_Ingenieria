package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public interface ServicioJabg0014Interface {
	public List<Userlogin> getAllUsers();
//	public void addUser(Userlogin user);
	public Userlogin checkuser(String username,String pass);
	public Userlogin existeusu(String nombre);
	public void crearUsuario(String nombre, String pass);
	public void crearUsuarioAdmin(String nombre, String pass);
	
}