package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enlace_obra_termino")
public class EnlaceObraTermino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "obra_id", nullable = false)
    private Proyecto obra; // Mapeado contra la entidad de Proyectos/Obras

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "termino_id", nullable = false)
    private TerminoMunicipal termino; // El término municipal afectado

    public EnlaceObraTermino() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Proyecto getObra() { return obra; }
    public void setObra(Proyecto obra) { this.obra = obra; }

    public TerminoMunicipal getTermino() { return termino; }
    public void setTermino(TerminoMunicipal termino) { this.termino = termino; }
}
