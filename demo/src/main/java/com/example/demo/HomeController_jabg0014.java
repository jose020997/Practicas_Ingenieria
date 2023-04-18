package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//Cambiar la parte de root que solo cambia los datos cuando actualizas la pagina
//Si la contraseña no coincide que salta otro error

@Controller
public class HomeController_jabg0014 {
	
	@Autowired
	private ServicioJabg0014Interface dao;
	
	@GetMapping("/registro.html")
	public String mover() {
		return "registro";
	}
	
	@GetMapping("/login")
	//Añadir lo de la cookie
		public String metodoinicio() {
			return "login";
	}

	
	@PostMapping(value = "/login")
	public String logearse(HttpServletRequest req, Model model) {
	    HttpSession sess = req.getSession();
	    String pass = req.getParameter("pass");
	    String name = req.getParameter("name");
	    int userExists=0;
	    
	    Userlogin usuario = dao.checkuser(name, pass);
	    if (usuario != null) {
	    	 if(usuario.getEs_user() == 1) {
	    		 model.addAttribute("lista_usuarios", dao.getAllUsers());
	 	    	 sess.setAttribute("nam_user", name);
	    		 return "admin"; 
	    	 }
	    	 else {
	    		 model.addAttribute("usuario", name);
		         sess.setAttribute("nam_user", name);
	    		 return "articulos";
	    	 }
	    }
	    else { //Comprobar si es la contraseña o el usuario
	    	if (dao.existeusu(name) != null) {
	    		userExists=2;
	    		model.addAttribute("exist",userExists);
		        return "login"; 
	    	}
	    	else {
		    	userExists=1;
		    	model.addAttribute("exist",userExists);
		        return "login"; 
	    	}
	    }
	}
	
	@PostMapping(value = "/registro")
	public String crear(HttpServletRequest req, Model model) {
	    HttpSession sess = req.getSession();
	    String pass = req.getParameter("contraseña");
	    String name = req.getParameter("nombre");
	    boolean existe=false;
	    
	    Userlogin usuario = dao.existeusu(name);
	    if (usuario != null) {
	    	existe=true;
	    	model.addAttribute("exist",existe);
	    	return "registro";
	    }
	    else {
	    	dao.crearUsuario(name,pass);
	    	return "login";
	    }
	   
	
	}
}
