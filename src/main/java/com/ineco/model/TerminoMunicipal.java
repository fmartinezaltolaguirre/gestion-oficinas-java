package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "terminos_municipales")
public class TerminoMunicipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre; // Nombre del municipio (ej: Lugo, Monforte)

    public TerminoMunicipal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
