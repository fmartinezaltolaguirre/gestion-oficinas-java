package com.ineco.repository;

import com.ineco.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Consulta personalizada para listar proyectos de una sede específica
    List<Proyecto> findByOficinaId(Long oficinaId);
}
