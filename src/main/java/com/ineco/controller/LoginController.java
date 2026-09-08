package com.ineco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * Sirve la pantalla de acceso al sistema técnico.
     * Mapea con la plantilla: src/main/resources/templates/login.html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Redirección por defecto si el usuario accede a la raíz del proyecto.
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/oficinas";
    }
}
