package com.ineco.service;

import com.ineco.model.Usuario;
import com.ineco.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección de dependencias por constructor
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método requerido por Spring Security para verificar las credenciales en el Login.
     * Añade la validación forzada del prefijo ROLE_ exigido por el framework.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en el sistema corporativo: " + username));

        // Nos aseguramos de que el rol tenga el formato 'ROLE_NOMBRE' exigido por Spring Security
        String nombreRol = usuario.getRol();
        if (nombreRol != null && !nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        }

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(new SimpleGrantedAuthority(nombreRol))
                .build();
    }

    /**
     * Registra un nuevo usuario en el sistema técnico cifrando su contraseña.
     */
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario corporativo '" + usuario.getUsername() + "' ya está registrado.");
        }
        
        // Cifrado obligatorio de seguridad para bases de datos (BCrypt)
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Listar todos los usuarios asignados a la oficina técnica.
     */
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
