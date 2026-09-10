package com.ineco.controller;

import com.ineco.model.Finca;
import com.ineco.repository.FincaRepository;
import com.ineco.repository.ProyectoRepository;
import com.ineco.repository.PropietarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fincas")
public class FincaController {

    private final FincaRepository fincaRepository;
    private final ProyectoRepository proyectoRepository;
    private final PropietarioRepository propietarioRepository;

    public FincaController(FincaRepository fincaRepository, ProyectoRepository proyectoRepository, PropietarioRepository propietarioRepository) {
        this.fincaRepository = fincaRepository;
        this.proyectoRepository = proyectoRepository;
        this.propietarioRepository = propietarioRepository;
    }

    // Listado general de predios
    @GetMapping
    public String listarFincas(Model model) {
        model.addAttribute("fincas", fincaRepository.findAll());
        return "fincas/lista";
    }

    // Formulario de alta
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("finca", new Finca());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        model.addAttribute("propietarios", propietarioRepository.findAll());
        return "fincas/formulario";
    }

    // Guardar registro
    @PostMapping("/guardar")
    public String guardarFinca(@ModelAttribute Finca finca) {
        fincaRepository.save(finca);
        return "redirect:/fincas";
    }
}
