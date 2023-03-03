package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		if(user != null) {
			model.addAttribute("usuario",user);
			return "datos";
		}
		else {
			Cookie[ ] cookies = request.getCookies( );
			String emailcoki = "";
			String nombrecoki = "";
			if (cookies != null){
				for (Cookie cookie: cookies){
					if (cookie.getName().equals("userId")) emailcoki = cookie.getValue();
					if (cookie.getName().equals("nombreid")) nombrecoki = cookie.getValue();
					//COmprobar en el datos que si no existe cookie haga una cosa y sino otra
				}
			}
			else {
				return "index";
			}
		}
		return "datos";
	}
	
	@PostMapping(value="/datos")
	public String metodo(@RequestParam("button") String buttonValue, HttpServletRequest req, Model model, HttpServletResponse response) {
		HttpSession sesion = req.getSession();
		//Se hace el request que es el que responde a las sesiones cookies
		String email = req.getParameter("email");
		String nombre = req.getParameter("nombre");
		//Guardamos los datos en un string que rescatamos de la sesion http
			
		Usuario usuario = new Usuario(nombre,email);
		//Creamos un javabean de usuario con los datos rescatados de la sesion
		
		
		if(buttonValue.equals("Cargar")) {
			String userId = email;
			String nomId = nombre;
			Cookie usu = new Cookie("userId", userId);
			Cookie nom = new Cookie("nombreid",nomId);
			usu.setMaxAge(60*60*24*7);
			nom.setMaxAge(60*60*24*7);
			usu.setPath("/");
			nom.setPath("/");
			//Mandamos a todo lo que esté en la raiz
			response.addCookie(usu);
			response.addCookie(nom);
			//Carga la proxima vez que se lancen

			return "datos";
		}
		else {
			
			sesion.setAttribute("usuario", usuario);
			//guardamos en la sesion el datos usuario
			model.addAttribute("usuario",usuario);
			//añadimos al modelo para poder mostrar en el thymeleaf
			
			return "datos";
		}
	}
}


/* Uusario Interface
 * public boolean checkUusario(String usuario,sting passw);
 * para comparar usamos el .equals */

