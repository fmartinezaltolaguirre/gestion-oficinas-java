package com.ineco.controller;

import com.ineco.model.Alerta;
import com.ineco.repository.AlertaRepository;
import com.ineco.repository.ProyectoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaRepository alertaRepository;
    private final ProyectoRepository proyectoRepository;

    public AlertaController(AlertaRepository alertaRepository, ProyectoRepository proyectoRepository) {
        this.alertaRepository = alertaRepository;
        this.proyectoRepository = proyectoRepository;
    }

    // Listado general de alertas y plazos
    @GetMapping
    public String listarAlertas(Model model) {
        model.addAttribute("alertas", alertaRepository.findAll());
        return "alertas/lista";
    }

    // Cargar formulario de alta de hito
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("alerta", new Alerta());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "alertas/formulario";
    }

    // Guardar en la base de datos H2
    @PostMapping("/guardar")
    public String guardarAlerta(@ModelAttribute Alerta alerta) {
        alerta.setEstado("ACTIVA"); // Estado inicial por defecto
        alertaRepository.save(alerta);
        return "redirect:/alertas";
    }
}
