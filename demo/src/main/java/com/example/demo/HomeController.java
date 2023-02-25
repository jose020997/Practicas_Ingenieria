package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// error en la sintaxis, click izquierdo para las sugerencias (En este caso era el Import)
@Controller
public class HomeController {

	@GetMapping(value="/inicio")
	public String metodoinicio() {
		return "index";
	}
	
	@GetMapping(value="/datos")
	public String verUsuario(Model model,HttpSession sesion,HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		boolean mensaje;
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		 
		if(user != null) {
			model.addAttribute("usuario", user);
		 	System.out.println(model);
		 	mensaje=false;
		}
		else {
			mensaje=true;
		}
		return "datos";
	}
	
	@PostMapping(value="/datos")
	public String metodo(HttpServletRequest req, Model model) {
		HttpSession sesion = req.getSession();
		//Se hace el request que es el que responde a las sesiones cookies
		
			String email = req.getParameter("email");
			String nombre = req.getParameter("nombre");
			//Guardamos los datos en un string que rescatamos de la sesion http
			
		Usuario usuario = new Usuario(nombre,email);
		//Creamos un javabean de usuario con los datos rescatados de la sesion
		System.out.println(nombre);
		System.out.println(email);
		System.out.println(usuario);
		
		sesion.setAttribute("usuario", usuario);
		//guardamos en la sesion el datos usuario
		System.out.println(sesion);
		return "datos";
		
	}
// Se guardan los cambios
}
