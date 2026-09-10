package com.ineco.repository;

import com.ineco.model.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {
    // Buscar fincas asociadas a un expediente de obra concreto
    List<Finca> findByProyectoId(Long proyectoId);
}
