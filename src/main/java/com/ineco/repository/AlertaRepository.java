package com.ineco.repository;

import com.ineco.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByEstadoOrderByFechaVencimientoAsc(String estado);
}
