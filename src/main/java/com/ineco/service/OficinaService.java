package com.ineco.service;

import com.ineco.model.Oficina;
import com.ineco.repository.OficinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OficinaService {

    private final OficinaRepository oficinaRepository;

    // Inyección de dependencias por constructor (Buena práctica recomendada)
    public OficinaService(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }

    // Listar todas las oficinas para mostrarlas en las vistas de Thymeleaf
    @Transactional(readOnly = true)
    public List<Oficina> listarTodas() {
        return oficinaRepository.findAll();
    }

    // Buscar una oficina específica por su ID único
    @Transactional(readOnly = true)
    public Optional<Oficina> buscarPorId(Long id) {
        return oficinaRepository.findById(id);
    }

    // Buscar oficina utilizando el código interno corporativo
    @Transactional(readOnly = true)
    public Optional<Oficina> buscarPorCodigo(String codigoOficina) {
        return oficinaRepository.findByCodigoOficina(codigoOficina);
    }

    // Registrar o actualizar una oficina controlando que no haya duplicados
    @Transactional
    public Oficina guardar(Oficina oficina) {
        // Regla de negocio: Validar que el nombre no esté repetido si es una oficina nueva
        if (oficina.getId() == null && oficinaRepository.existsByNombre(oficina.getNombre())) {
            throw new IllegalArgumentException("Ya existe una oficina registrada con el nombre: " + oficina.getNombre());
        }
        return oficinaRepository.save(oficina);
    }

    // Eliminar una oficina del sistema técnico
    @Transactional
    public void eliminar(Long id) {
        if (!oficinaRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: La oficina con ID " + id + " no existe.");
        }
        oficinaRepository.deleteById(id);
    }
}
