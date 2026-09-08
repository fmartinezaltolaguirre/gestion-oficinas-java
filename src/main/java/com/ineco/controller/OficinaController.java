package com.ineco.controller;

import com.ineco.model.Oficina;
import com.ineco.service.OficinaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {

    private final OficinaService oficinaService;

    // Inyección de dependencias del servicio técnico
    public OficinaController(OficinaService oficinaService) {
        this.oficinaService = oficinaService;
    }

    /**
     * Muestra el listado completo de oficinas.
     * Mapea con la plantilla: src/main/resources/templates/oficinas/lista.html
     */
    @GetMapping
    public String listarOficinas(Model model) {
        List<Oficina> lista = oficinaService.listarTodas();
        model.addAttribute("oficinas", lista);
        return "oficinas/lista"; // Retorna la vista HTML
    }

    /**
     * Muestra el formulario para dar de alta una nueva oficina.
     * Mapea con la plantilla: src/main/resources/templates/oficinas/formulario.html
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinas/formulario";
    }

    /**
     * Procesa el envío del formulario para guardar o actualizar una oficina.
     */
    @PostMapping("/guardar")
    public String guardarOficina(@ModelAttribute("oficina") Oficina oficina, Model model) {
        try {
            oficinaService.guardar(oficina);
            return "redirect:/oficinas"; // Redirige al listado general tras guardar con éxito
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "oficinas/formulario"; // Si hay error (ej. nombre duplicado), recarga el formulario
        }
    }

    /**
     * Muestra el formulario precargado con los datos para editar una oficina existente.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Oficina oficina = oficinaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Oficina no encontrada con ID: " + id));
        model.addAttribute("oficina", oficina);
        return "oficinas/formulario";
    }

    /**
     * Procesa la eliminación de una oficina técnica.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarOficina(@PathVariable("id") Long id) {
        oficinaService.eliminar(id);
        return "redirect:/oficinas";
    }
}
