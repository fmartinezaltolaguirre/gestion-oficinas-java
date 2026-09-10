package com.ineco.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo; // Ej: Vencimiento de alegaciones

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false, length = 20)
    private String criticidad; // Ej: ALTA, MEDIA, BAJA

    @Column(nullable = false, length = 20)
    private String estado; // Ej: ACTIVA, ATENDIDA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto; // Vinculación opcional a un proyecto

    // Constructor obligatorio
    public Alerta() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getCriticidad() { return criticidad; }
    public void setCriticidad(String criticidad) { this.criticidad = criticidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
}
