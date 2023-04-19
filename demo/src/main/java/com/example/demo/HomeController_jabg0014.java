package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//Añadir por las cookies

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
		public String metodoinicio(HttpSession sesion, Model model) {
		Userlogin user = (Userlogin) sesion.getAttribute("user");
		String name = (String) sesion.getAttribute("name");
		if(user == null) {
			return "login";
		}
		else {
			if(user.getEs_user() == 1) {
				model.addAttribute("lista_usuarios", dao.getAllUsers());
				return "admin";
			}
			else {
				model.addAttribute("usuario", name);
				return "articulos";
			}
		}
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
	 	    	 sess.setAttribute("user", usuario);
	    		 return "admin"; 
	    	 }
	    	 else {
	    		 model.addAttribute("usuario", name);
	    		 sess.setAttribute("name", name);
		         sess.setAttribute("user", usuario);
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
