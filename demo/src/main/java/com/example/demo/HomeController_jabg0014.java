package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController_jabg0014 {
	
	@GetMapping("/login")
    public String mostrarlogin(Model model, HttpServletRequest req,HttpSession sess) {
		String user = (String) sess.getAttribute("nam_user");
		System.out.println(user);
		//falta hacer que si las sesion tiene root o carga la variable pues se hace de otra manera
		if(user != null) {
			//hacemos que si el user que recatamos del lado anterior sea distinto de nulo por lo cual ya estaba registrado ponga el nombre
			if(user.equals("root")) {
				//si es root pues accedemos a la interfaz de root
				return "admin";
			}
			else {
				model.addAttribute("usuario", user);
				//hacemos el model para que cuando carque la pagina ponga el nombre
					return "articulos";
			}
		}
		else {
			return "login";
		}
	}
	
	@PostMapping(value = "/login")
	public String logearse(HttpServletRequest req, Model model) {
	    HttpSession sess = req.getSession();
	    String pass = req.getParameter("pass");
	    String name = req.getParameter("name");
	    boolean isAdmin = false;

	    DAO_jabg0014 dao = new DAO_jabg0014();
	    List<Userlogin> user = dao.getAllUsers();

	    boolean userExists = false;
	    
	    if(dao.checkuser(name,pass)) {
	    	//Verificamos si dao.checkuser con los datos es root root que es el usuario que esta cargado en el DAO para ser root
	    	//Cargamos la lista de usuarios y ademas nos vamos a admin
	    	model.addAttribute("lista_usuarios", dao.getAllUsers());
	    	sess.setAttribute("nam_user", name);
	    	return "admin";
	       
	    } else {
	        for (Userlogin users : user) {
	        	//recorremos todos los usuarios y vemos si alguno coincide
	            if (users.getPass().equals(pass) && users.getNombre().equals(name)) {
	                userExists = true;
	                break;
	            }
	        }
	        if (userExists) {
	            model.addAttribute("usuario", name);
	            sess.setAttribute("nam_user", name);
	            return "articulos";
	        } else {
	    	    model.addAttribute("exist",userExists);
	            return "login";
	        }
	    }
	}
	
}
