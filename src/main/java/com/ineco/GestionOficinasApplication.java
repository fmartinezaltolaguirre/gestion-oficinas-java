package com.ineco;

import com.ineco.model.Oficina;
import com.ineco.model.Usuario;
import com.ineco.repository.OficinaRepository;
import com.ineco.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionOficinasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionOficinasApplication.class, args);
    }

    /**
     * Inyecta de forma directa los datos en la base de datos H2 en memoria RAM.
     */
    @Bean
    CommandLineRunner inicializarDatosDePrueba(
            OficinaRepository oficinaRepository, 
            UsuarioRepository usuarioRepository, 
            PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("****************************************************************");
            System.out.println("[INFO] Iniciando inyección automática de datos en H2...");
            System.out.println("****************************************************************");
            
            // 1. Crear oficina de prueba si la base de datos en RAM está vacía
            Oficina oficinaSede = oficinaRepository.findByCodigoOficina("OFI-MDR")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Sede Central Madrid", "Paseo de la Castellana 45", "OFI-MDR")));

            // 2. Crear administrador de prueba
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombreCompleto("Administrador Ineco");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                admin.setOficina(oficinaSede);
                
                usuarioRepository.save(admin);
                System.out.println("****************************************************************");
                System.out.println("[ÉXITO] USUARIO 'admin' CON CLAVE 'admin123' DISPONIBLE EN LOG-IN");
                System.out.println("****************************************************************");
            }
        };
    }
}
