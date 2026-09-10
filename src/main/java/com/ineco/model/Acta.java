package com.ineco.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "actas")
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroActa; // Código oficial (ej: ACT-2026-089)

    private LocalDate fechaActaPrevia;
    private LocalDate fechaActaOcupacion;

    @Column(nullable = false)
    private Double importeJustiprecio; // Cuantía económica acordada

    @Column(length = 30)
    private String estadoPago; // Ej: PENDIENTE, PAGADO, FISCALIZACIÓN

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finca_id", nullable = false, unique = true)
    private Finca finca; // Finca afectada a la que se asocia este hito jurídico

    // Constructor obligatorio
    public Acta() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroActa() { return numeroActa; }
    public void setNumeroActa(String numeroActa) { this.numeroActa = numeroActa; }

    public LocalDate getFechaActaPrevia() { return fechaActaPrevia; }
    public void setFechaActaPrevia(LocalDate fechaActaPrevia) { this.fechaActaPrevia = fechaActaPrevia; }

    public LocalDate getFechaActaOcupacion() { return fechaActaOcupacion; }
    public void setFechaActaOcupacion(LocalDate fechaActaOcupacion) { this.fechaActaOcupacion = fechaActaOcupacion; }

    public Double getImporteJustiprecio() { return importeJustiprecio; }
    public void setImporteJustiprecio(Double importeJustiprecio) { this.importeJustiprecio = importeJustiprecio; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public Finca getFinca() { return finca; }
    public void setFinca(Finca finca) { this.finca = finca; }
}
