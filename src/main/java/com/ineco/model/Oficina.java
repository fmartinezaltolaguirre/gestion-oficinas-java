package com.ineco.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "oficinas")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre; // Ej: "Delegación Lugo", "Oficina Central"

    @Column(length = 150)
    private String ubicacion; // Dirección física o región

    @Column(length = 50)
    private String codigoOficina; // Código interno de Ineco para auditorías

    // Relación opcional: Una oficina puede tener asignados varios usuarios
    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    // --- CONSTRUCTORES ---
    public Oficina() {}

    public Oficina(String nombre, String ubicacion, String codigoOficina) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.codigoOficina = codigoOficina;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getCodigoOficina() { return codigoOficina; }
    public void setCodigoOficina(String codigoOficina) { this.codigoOficina = codigoOficina; }

    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
}
