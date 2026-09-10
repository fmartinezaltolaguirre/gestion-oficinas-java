package com.ineco.controller;

import com.ineco.model.Propietario;
import com.ineco.repository.PropietarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propietarios")
public class PropietarioController {

    private final PropietarioRepository propietarioRepository;

    public PropietarioController(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    // Listado general de afectados
    @GetMapping
    public String listarPropietarios(Model model) {
        model.addAttribute("propietarios", propietarioRepository.findAll());
        return "propietarios/lista";
    }

    // Formulario para añadir un nuevo afectado
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("propietario", new Propietario());
        return "propietarios/formulario";
    }

    // Guardar registro
    @PostMapping("/guardar")
    public String guardarPropietario(@ModelAttribute Propietario propietario) {
        propietarioRepository.save(propietario);
        return "redirect:/propietarios";
    }
}
