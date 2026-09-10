package com.ineco.controller;

import com.ineco.service.BusquedaNaturalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class BusquedaController {

    private final BusquedaNaturalService busquedaService;

    public BusquedaController(BusquedaNaturalService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @GetMapping("/buscar")
    public String realizarBusquedaNatural(@RequestParam(value = "q", required = false) String consulta, Model model) {
        model.addAttribute("query", consulta);
        
        if (consulta != null && !consulta.trim().isEmpty()) {
            Map<String, Object> resultados = busquedaService.procesarBusqueda(consulta);
            // CORREGIDO: Usamos el método oficial de Spring MVC para inyectar el mapa de resultados
            model.addAllAttributes(resultados);
        }
        
        return "busqueda/resultados";
    }
}
