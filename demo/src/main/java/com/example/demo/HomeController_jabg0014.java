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
    public String mostrarlogin() {
		return "login";
    }
	
	@PostMapping(value = "/login")
	public String logearse(HttpServletRequest req, Model model) {
	    HttpSession sess = req.getSession();
	    String pass = req.getParameter("pass");
	    String name = req.getParameter("name");

	    DAO_jabg0014 dao = new DAO_jabg0014();
	    List<Userlogin> user = dao.getAllUsers();

	    boolean userExists = false;
	    if(name.equals("root")&& pass.equals("root")) {
	        return "admin";
	    } else {
	        for (Userlogin users : user) {
	            if (users.getPass().equals(pass) && users.getNombre().equals(name)) {
	                userExists = true;
	                break;
	            }
	        }
	        if (userExists) {
	            model.addAttribute("name", name);
	            return "articulos";
	        } else {
	            return "login";
	        }
	    }
	}
	
}
