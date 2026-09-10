package com.ineco.controller;

import com.ineco.model.Acta;
import com.ineco.repository.ActaRepository;
import com.ineco.repository.FincaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actas")
public class ActaController {

    private final ActaRepository actaRepository;
    private final FincaRepository fincaRepository;

    public ActaController(ActaRepository actaRepository, FincaRepository fincaRepository) {
        this.actaRepository = actaRepository;
        this.fincaRepository = fincaRepository;
    }

    // Listado general de hitos jurídicos
    @GetMapping
    public String listarActas(Model model) {
        model.addAttribute("actas", actaRepository.findAll());
        return "actas/lista";
    }

    // Formulario de alta
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("acta", new Acta());
        model.addAttribute("fincas", fincaRepository.findAll()); // Para vincular la parcela
        return "actas/formulario";
    }

    // Guardar registro
    @PostMapping("/guardar")
    public String guardarActa(@ModelAttribute Acta acta) {
        actaRepository.save(acta);
        return "redirect:/actas";
    }
}
