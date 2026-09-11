package com.ineco.controller;

import com.ineco.model.EnlaceObraTermino;
import com.ineco.repository.EnlaceObraTerminoRepository;
import com.ineco.repository.ProyectoRepository;
import com.ineco.repository.TerminoMunicipalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enlaces-obras")
public class EnlaceObraTerminoController {

    private final EnlaceObraTerminoRepository enlaceRepository;
    private final ProyectoRepository proyectoRepository;
    private final TerminoMunicipalRepository terminoRepository;

    public EnlaceObraTerminoController(EnlaceObraTerminoRepository enlaceRepository, ProyectoRepository proyectoRepository, TerminoMunicipalRepository terminoRepository) {
        this.enlaceRepository = enlaceRepository;
        this.proyectoRepository = proyectoRepository;
        this.terminoRepository = terminoRepository;
    }

    @GetMapping
    public String listarEnlaces(Model model) {
        model.addAttribute("enlaces", enlaceRepository.findAll());
        return "enlaces/lista";
    }
}
