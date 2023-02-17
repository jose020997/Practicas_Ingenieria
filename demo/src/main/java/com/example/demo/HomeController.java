package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// error en la sintaxis, click izquierdo para las sugerencias (En este caso era el Import)
@Controller
public class HomeController {

	@GetMapping(value="/inicio")
	public String metodoinicio() {
		return "index";
	}
	
	@GetMapping(value="/usuario")
	public String devolverdatos() {
		return "datos";
	}
// Se guardan los cambios
}
