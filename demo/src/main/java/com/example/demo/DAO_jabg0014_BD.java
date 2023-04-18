package com.example.demo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DAO_jabg0014_BD implements ServicioJabg0014Interface
//implements ServicioJabg0014Interface
{
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource) { 
		this.jdbctemplate =  new JdbcTemplate(datasource);
	}
	
	private final RowMapper<Userlogin> mapper = (rs,numRow) -> {
		Userlogin usuario = new Userlogin();
		usuario.setNombre(rs.getString("nombre"));
		usuario.setPass(rs.getString("correo"));
		usuario.setEs_user(rs.getInt("es_user"));
		return usuario;
	};
		//Lectura del mapper para que recorra cada uno de las filas
	 public List<Userlogin> getAllUsers(){
		String sql = "select * from user";
		List<Userlogin> usuarios = this.jdbctemplate.query(sql, mapper);
		return usuarios;
	}

			
	public Userlogin checkuser(String nombre,String correo) {
		String sql = "select * from user where nombre = ? and correo=?";
		List<Userlogin> usuarios = this.jdbctemplate.query(sql, mapper, nombre,correo);
		if (usuarios.isEmpty()) return null;
		else return usuarios.get(0);
	}
	public Userlogin existeusu(String nombre) {
		String sql = "select * from user where nombre = ?";
		List<Userlogin> usuarios = this.jdbctemplate.query(sql, mapper, nombre);
		if (usuarios.isEmpty()) return null;
		else return usuarios.get(0);
	}
	public void crearUsuario(String nombre, String pass) {
	    String sql = "INSERT INTO user (nombre, correo, es_user) VALUES (?, ?, 0)";
	    jdbctemplate.update(sql, nombre, pass);
	}
	
	
}
