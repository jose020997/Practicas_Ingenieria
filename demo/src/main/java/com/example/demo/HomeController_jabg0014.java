package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController_jabg0014 {
	
	@GetMapping("/login")
    public String mostrarlogin() {
		return "login";
    }
}
