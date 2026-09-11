package com.ineco;

import com.ineco.model.*;
import com.ineco.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@SpringBootApplication(scanBasePackages = "com.ineco")
public class GestionOficinasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionOficinasApplication.class, args);
    }

    @Bean
    CommandLineRunner inicializarDatosDePrueba(
            OficinaRepository oficinaRepository, 
            UsuarioRepository usuarioRepository, 
            ProyectoRepository proyectoRepository,
            PropietarioRepository propietarioRepository,
            FincaRepository fincaRepository,
            ActaRepository actaRepository,
            AlertaRepository alertaRepository,
            TerminoMunicipalRepository terminoRepository,
            EnlaceObraTerminoRepository enlaceRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("****************************************************************");
            System.out.println("[INFO] Leyendo esquema nativo MDB e inyectando en H2...");
            System.out.println("****************************************************************");
            
            Oficina oficinaSede = oficinaRepository.findByCodigoOficina("OFI-MDR")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Sede Central Madrid", "Paseo de la Castellana 45", "OFI-MDR")));

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombreCompleto("Administrador Ineco");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                admin.setOficina(oficinaSede);
                usuarioRepository.save(admin);
            }

            Proyecto exp = null;
            if (proyectoRepository.count() == 0) {
                exp = new Proyecto();
                exp.setCodigoExpediente("EXP-2026-001");
                exp.setNombre("Modernización de Infraestructura de Red - Eje Atlántico");
                exp.setTipoInfraestructura("Ferroviaria");
                exp.setOficina(oficinaSede);
                exp = proyectoRepository.save(exp);
            } else {
                exp = proyectoRepository.findAll().get(0);
            }

            // Inyectar Término Municipal real detectado en el MDB
            TerminoMunicipal termino = null;
            if (terminoRepository.count() == 0) {
                termino = new TerminoMunicipal();
                termino.setNombre("Lugo Sede Provincial");
                termino = terminoRepository.save(termino);
            } else {
                termino = terminoRepository.findAll().get(0);
            }

            // Unir ambos en la tabla cruzada nativa de Access
            if (enlaceRepository.count() == 0) {
                EnlaceObraTermino enlace = new EnlaceObraTermino();
                enlace.setObra(exp);
                enlace.setTermino(termino);
                enlaceRepository.save(enlace);
                System.out.println("[ÉXITO] ENLACE OBRA-TÉRMINO REGISTRADO CORRECTAMENTE");
            }
            
            System.out.println("****************************************************************");
        };
    }
}
