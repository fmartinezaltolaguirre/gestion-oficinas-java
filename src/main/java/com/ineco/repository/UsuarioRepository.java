package com.ineco.repository;

import com.ineco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Esencial para Spring Security: buscar las credenciales por el nombre de usuario
    Optional<Usuario> findByUsername(String username);

    // Comprobar si un nombre de usuario (login) ya está registrado en el sistema
    boolean existsByUsername(String username);
}
