package com.ineco.controller;

import com.ineco.model.Proyecto;
import com.ineco.repository.OficinaRepository;
import com.ineco.repository.ProyectoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoRepository proyectoRepository;
    private final OficinaRepository oficinaRepository;

    public ProyectoController(ProyectoRepository proyectoRepository, OficinaRepository oficinaRepository) {
        this.proyectoRepository = proyectoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    // Listado general de todos los proyectos de infraestructura
    @GetMapping
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "proyectos/lista";
    }

    // Cargar formulario para abrir un nuevo expediente técnico
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("oficinas", oficinaRepository.findAll()); // Para el selector de sedes
        return "proyectos/formulario";
    }

    // Guardar el registro en la base de datos en memoria
    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoRepository.save(proyecto);
        return "redirect:/proyectos";
    }
}
