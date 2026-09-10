package com.ineco.service;

import com.ineco.model.*;
import com.ineco.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusquedaNaturalService {

    private final FincaRepository fincaRepository;
    private final ProyectoRepository proyectoRepository;
    private final PropietarioRepository propietarioRepository;

    public BusquedaNaturalService(FincaRepository fincaRepository, ProyectoRepository proyectoRepository, PropietarioRepository propietarioRepository) {
        this.fincaRepository = fincaRepository;
        this.proyectoRepository = proyectoRepository;
        this.propietarioRepository = propietarioRepository;
    }

    /**
     * Mapea y procesa una consulta en texto libre para devolver los registros que mejor encajen.
     */
    public Map<String, Object> procesarBusqueda(String consulta) {
        Map<String, Object> resultados = new HashMap<>();
        if (consulta == null || consulta.trim().isEmpty()) {
            return resultados;
        }

        String texto = consulta.toLowerCase();
        
        // 1. Detectar Intención Principal de navegación/búsqueda
        boolean buscaFincas = texto.contains("finca") || texto.contains("parcela") || texto.contains("metro") || texto.contains("m²");
        boolean buscaProyectos = texto.contains("proyecto") || texto.contains("obra") || texto.contains("expediente");
        boolean buscaPropietarios = texto.contains("propietario") || texto.contains("afectado") || texto.contains("titular") || texto.contains("dni") || texto.contains("cif");

        // 2. Ejecutar búsquedas elásticas basadas en coincidencias semánticas detectadas
        if (buscaFincas || (!buscaProyectos && !buscaPropietarios)) {
            List<Finca> fincas = fincaRepository.findAll().stream()
                .filter(f -> evaluarFinca(f, texto))
                .collect(Collectors.toList());
            resultados.put("fincas", fincas);
        }

        if (buscaProyectos || (!buscaFincas && !buscaPropietarios)) {
            List<Proyecto> proyectos = proyectoRepository.findAll().stream()
                .filter(p -> evaluarProyecto(p, texto))
                .collect(Collectors.toList());
            resultados.put("proyectos", proyectos);
        }

        if (buscaPropietarios || (!buscaFincas && !buscaProyectos)) {
            List<Propietario> propietarios = propietarioRepository.findAll().stream()
                .filter(p -> evaluarPropietario(p, texto))
                .collect(Collectors.toList());
            resultados.put("propietarios", propietarios);
        }

        return resultados;
    }

    private boolean evaluarFinca(Finca f, String texto) {
        // Filtrado inteligente por superficie
        if (texto.contains("más de") || texto.contains("mayor de") || texto.contains(">")) {
            double valor = extraerNumero(texto);
            if (valor > 0 && f.getSuperficieAfectada() <= valor) return false;
        }
        if (texto.contains("menos de") || texto.contains("menor de") || texto.contains("<")) {
            double valor = extraerNumero(texto);
            if (valor > 0 && f.getSuperficieAfectada() >= valor) return false;
        }
        
        // Filtrado por palabras clave geográficas o cultivos
        return f.getNumeroExpedienteFinca().toLowerCase().contains(texto) ||
               (f.getTipoCultivo() != null && f.getTipoCultivo().toLowerCase().contains(texto)) ||
               (f.getProyecto() != null && f.getProyecto().getNombre().toLowerCase().contains(texto)) ||
               (f.getPropietario() != null && f.getPropietario().getNombreCompleto().toLowerCase().contains(texto));
    }

    private boolean evaluarProyecto(Proyecto p, String texto) {
        return p.getCodigoExpediente().toLowerCase().contains(texto) ||
               p.getNombre().toLowerCase().contains(texto) ||
               p.getTipoInfraestructura().toLowerCase().contains(texto);
    }

    private boolean evaluarPropietario(Propietario p, String texto) {
        return p.getDniCif().toLowerCase().contains(texto) ||
               p.getNombreCompleto().toLowerCase().contains(texto) ||
               (p.getMunicipio() != null && p.getMunicipio().toLowerCase().contains(texto));
    }

    private double extraerNumero(String texto) {
        try {
            String limpio = texto.replaceAll("[^0-9]", " ").trim();
            String[] partes = limpio.split("\\s+");
            for (String parte : partes) {
                if (!parte.isEmpty()) return Double.parseDouble(parte);
            }
        } catch (Exception e) {
            // Ignorar fallos de parseo en el texto libre
        }
        return 0;
    }
}
