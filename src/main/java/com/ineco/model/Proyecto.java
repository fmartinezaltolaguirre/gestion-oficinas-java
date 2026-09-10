package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigoExpediente; // Ej: EXP-2026-004

    @Column(nullable = false, length = 150)
    private String nombre; // Ej: Ampliación de Tercer Carril AP-9

    @Column(length = 100)
    private String tipoInfraestructura; // Ej: Ferroviaria, Carreteras, Aeropuertos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina; // Oficina técnica adscrita que tutela el proyecto

    // Constructor vacío obligatorio para JPA
    public Proyecto() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoExpediente() { return codigoExpediente; }
    public void setCodigoExpediente(String codigoExpediente) { this.codigoExpediente = codigoExpediente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoInfraestructura() { return tipoInfraestructura; }
    public void setTipoInfraestructura(String tipoInfraestructura) { this.tipoInfraestructura = tipoInfraestructura; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }
}
