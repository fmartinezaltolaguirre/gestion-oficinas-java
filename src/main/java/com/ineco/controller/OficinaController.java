package com.ineco.controller;

import com.ineco.model.Oficina;
import com.ineco.repository.OficinaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {

    private final OficinaRepository oficinaRepository;

    public OficinaController(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }

    // Listado general de sedes
    @GetMapping
    public String listarOficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "oficinas/lista";
    }

    // Cargar formulario para nueva sede
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinas/formulario";
    }

    // Guardar registro
    @PostMapping("/guardar")
    public String guardarOficina(@ModelAttribute Oficina oficina) {
        oficinaRepository.save(oficina);
        return "redirect:/oficinas";
    }

    // Formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Oficina oficina = oficinaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de oficina inválido: " + id));
        model.addAttribute("oficina", oficina);
        return "oficinas/formulario";
    }

    // Eliminar registro
    @GetMapping("/eliminar/{id}")
    public String eliminarOficina(@PathVariable("id") Long id) {
        Oficina oficina = oficinaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de oficina inválido: " + id));
        oficinaRepository.delete(oficina);
        return "redirect:/oficinas";
    }

    // NUEVA RUTA: Ficha de detalle técnico y geográfico unificado
    @GetMapping("/detalle/{id}")
    public String verDetalleOficina(@PathVariable("id") Long id, Model model) {
        Oficina oficina = oficinaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de oficina inválido: " + id));
        model.addAttribute("oficina", oficina);
        return "oficinas/detalle";
    }
}
