package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// error en la sintaxis, click izquierdo para las sugerencias (En este caso era el Import)
@Controller
public class HomeController {

	@GetMapping("/inicio")
	public String metodoinicio() {
		return "redirect:/";
	}
	
	@GetMapping("/datos")
	public String verUsuario(Model model,HttpSession sesion,HttpServletRequest request) {
		//Comprueba si tiene una cookie permamente o sino la tiene el codigo de abajo
		HttpSession session = request.getSession(true);
		boolean mensaje;
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		System.out.println("nombre"+ user.getNombre());
		/*if(user != null) {
			model.addAttribute("usuario", user);
		 	System.out.println(model);
		 	mensaje=false;
		}
		else {
			mensaje=true;
		}*/
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
		
		sesion.setAttribute("usuario", usuario);
		//guardamos en la sesion el datos usuario
		return "datos";
		
	}
	/*@PostMapping(value="/Guardar_datos")
	public String guardar(HttpServletResponse response) {
		Cookie c = new Cookie("userIdCookie", user);
		c.setMaxAge(60*60*24*7); //7dias
		c.setPath("/");
		response.addCookie(c);
		
		
		return "datos";
	}*/
	
// Se guardan los cambios
}
