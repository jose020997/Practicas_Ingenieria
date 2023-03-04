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
    public String verUsuario(Model model, HttpSession sesion, HttpServletRequest request) {
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailcoki = null;
            String nombrecoki = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        emailcoki = cookie.getValue();
                    }
                    if (cookie.getName().equals("nombreid")) {
                        nombrecoki = cookie.getValue();
                    }
                }
            }
            if (nombrecoki != null && emailcoki != null) {
                user = new Usuario(nombrecoki, emailcoki);
                model.addAttribute("nombrecoki",nombrecoki);
                model.addAttribute("emailcoki",emailcoki);
            } else {
                return "index";
            }
        }
        model.addAttribute("usuario", user);
        return "datos";
    }


    @PostMapping(value = "/datos")
    public String metodo(@RequestParam(required = false) String button, HttpServletRequest req, Model model, HttpServletResponse response) {
        HttpSession sesion = req.getSession();
        String email = req.getParameter("email");
        String nombre = req.getParameter("nombre");

        Usuario usuario = new Usuario(nombre, email);

        if (button != null && button.equals("Cargar")) {
            String userId = email.replaceAll("\\s+","");
            String nomId = nombre.replaceAll("\\s+","");
            Cookie usu = new Cookie("userId", userId);
            Cookie nom = new Cookie("nombreid", nomId);
            usu.setMaxAge(60 * 60 * 24 * 7);
            nom.setMaxAge(60 * 60 * 24 * 7);
            usu.setPath("/");
            nom.setPath("/");
            response.addCookie(usu);
            response.addCookie(nom);

            return "datos";
        } else {
            sesion.setAttribute("usuario", usuario);
            model.addAttribute("usuario", usuario);

            return "datos";
        }
    }
}

/* Uusario Interface
 * public boolean checkUusario(String usuario,sting passw);
 * para comparar usamos el .equals */

