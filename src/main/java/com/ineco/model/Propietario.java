package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "propietarios")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String dniCif; // DNI, NIE o CIF corporativo

    @Column(nullable = false, length = 150)
    private String nombreCompleto; // Nombre y apellidos o Razón Social

    @Column(length = 150)
    private String direccionNotificacion; // Dirección para envíos postales o burofax

    @Column(length = 50)
    private String municipio;

    @Column(length = 20)
    private String telefono;

    // Constructor vacío obligatorio para JPA
    public Propietario() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDniCif() { return dniCif; }
    public void setDniCif(String dniCif) { this.dniCif = dniCif; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDireccionNotificacion() { return direccionNotificacion; }
    public void setDireccionNotificacion(String direccionNotificacion) { this.direccionNotificacion = direccionNotificacion; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
