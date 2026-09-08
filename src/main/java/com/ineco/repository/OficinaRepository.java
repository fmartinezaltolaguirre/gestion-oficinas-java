package com.ineco.repository;

import com.ineco.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    Optional<Oficina> findByCodigoOficina(String codigoOficina);
    boolean existsByNombre(String nombre);
}
