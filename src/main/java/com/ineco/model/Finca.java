package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fincas")
public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String numeroExpedienteFinca; // Identificador del bien (ej: LU-LUG-015)

    @Column(nullable = false)
    private Integer poligono;

    @Column(nullable = false)
    private Integer parcela;

    @Column(nullable = false)
    private Double superficieAfectada; // Expresada en metros cuadrados (m²)

    @Column(length = 50)
    private String tipoCultivo; // Ej: Rústico Secano, Urbano, Monte Alto, Prado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto; // Proyecto de infraestructura que provoca la afección

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario; // Titular afectado adscrito a la finca

    // Constructor obligatorio
    public Finca() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroExpedienteFinca() { return numeroExpedienteFinca; }
    public void setNumeroExpedienteFinca(String numeroExpedienteFinca) { this.numeroExpedienteFinca = numeroExpedienteFinca; }

    public Integer getPoligono() { return poligono; }
    public void setPoligono(Integer poligono) { this.poligono = poligono; }

    public Integer getParcela() { return parcela; }
    public void setParcela(Integer parcela) { this.parcela = parcela; }

    public Double getSuperficieAfectada() { return superficieAfectada; }
    public void setSuperficieAfectada(Double superficieAfectada) { this.superficieAfectada = superficieAfectada; }

    public String getTipoCultivo() { return tipoCultivo; }
    public void setTipoCultivo(String tipoCultivo) { this.tipoCultivo = tipoCultivo; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }
}
