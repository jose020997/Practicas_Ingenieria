package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

// error en la sintaxis, click izquierdo para las sugerencias (En este caso era el Import)
@Controller
public class HomeController {

	@GetMapping(value="/inicio")
	public String metodoinicio() {
		return "index";
	}
	
	@GetMapping(value="/datos")
	public String devolverdatos() {
		return "datos";
	}
	
	@PostMapping(value="/datos")
	public String metodo(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		String nombre = req.getParameter("nombre");
		System.out.println(email+" "+nombre);
		Usuario usuario = new Usuario(nombre,email);
		model.addAttribute("usuario", usuario);
		return "datos";
		
	}
// Se guardan los cambios
}
