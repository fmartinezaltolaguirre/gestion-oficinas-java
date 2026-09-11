package com.ineco.repository;

import com.ineco.model.EnlaceObraTermino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnlaceObraTerminoRepository extends JpaRepository<EnlaceObraTermino, Long> {
    List<EnlaceObraTermino> findByObraId(Long obraId);
}
