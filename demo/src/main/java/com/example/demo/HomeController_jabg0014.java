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
	        //we create a list that keeps the DAO users
	        
	        boolean userExists = false;
	        //we create a variable to see if it exists
	        
	        if(name.equals("root")&& pass.equals("root")) {
	        	//if the name and email is root we go to admin.html	
	        	return "admin";
	        }
	        
	        else {
		        for (Userlogin u : user) {
		            if (u.getPass().equals(pass) && u.getNombre().equals(name)) {
		                userExists = true;
		                break;
		            }
			        if (userExists) {
			              model.addAttribute("name", name);
			                // Aquí deberías agregar el código para mostrar la lista de artículos
			                return "articulos";
			            } else {
			            	
			                return "login";
			            }
		        }
	        }   
		 return "login";
	 }
	
	
}
