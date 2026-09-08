package com.ineco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    @Column(nullable = false, unique = true, length = 50)
    private String username; // Usuario de red / Login

    @Column(nullable = false)
    private String password; // Contraseña cifrada para SecurityConfig

    @Column(nullable = false, length = 30)
    private String rol; // Ej: "ROLE_ADMIN", "ROLE_CONSULTOR"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id")
    private Oficina oficina; // Oficina técnica a la que está adscrito

    // --- CONSTRUCTORES ---
    public Usuario() {}

    public Usuario(String nombreCompleto, String username, String password, String rol, Oficina oficina) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.oficina = oficina;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }
}
