package com.ineco.repository;

import com.ineco.model.TerminoMunicipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminoMunicipalRepository extends JpaRepository<TerminoMunicipal, Long> {
}
