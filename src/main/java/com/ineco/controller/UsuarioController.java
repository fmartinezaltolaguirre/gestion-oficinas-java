package com.ineco.controller;

import com.ineco.model.Usuario;
import com.ineco.service.OficinaService;
import com.ineco.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final OficinaService oficinaService;

    // Inyección de dependencias de servicios
    public UsuarioController(UsuarioService usuarioService, OficinaService oficinaService) {
        this.usuarioService = usuarioService;
        this.oficinaService = oficinaService;
    }

    /**
     * Muestra el panel de administración de usuarios.
     * Mapea con la plantilla: src/main/resources/templates/usuarios/lista.html
     */
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    /**
     * Muestra el formulario para registrar un nuevo técnico.
     * Mapea con la plantilla: src/main/resources/templates/usuarios/formulario.html
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        // Pasamos las oficinas disponibles para poder asociar al usuario a una de ellas
        model.addAttribute("oficinas", oficinaService.listarTodas());
        return "usuarios/formulario";
    }

    /**
     * Procesa la creación y el cifrado de seguridad del nuevo usuario corporativo.
     */
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);
            return "redirect:/usuarios";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("oficinas", oficinaService.listarTodas());
            return "usuarios/formulario"; // Si hay error (ej: login duplicado), recargamos
        }
    }
}
