package com.ineco;

import com.ineco.model.Oficina;
import com.ineco.model.Usuario;
import com.ineco.model.Proyecto;
import com.ineco.model.Propietario;
import com.ineco.repository.OficinaRepository;
import com.ineco.repository.UsuarioRepository;
import com.ineco.repository.ProyectoRepository;
import com.ineco.repository.PropietarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.ineco")
public class GestionOficinasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionOficinasApplication.class, args);
    }

    /**
     * Inyecta de forma directa los datos estructurales del ecosistema relacional de Ineco 
     * en la base de datos H2 en memoria RAM al arrancar el servidor web local.
     */
    @Bean
    CommandLineRunner inicializarDatosDePrueba(
            OficinaRepository oficinaRepository, 
            UsuarioRepository usuarioRepository, 
            ProyectoRepository proyectoRepository,
            PropietarioRepository propietarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("****************************************************************");
            System.out.println("[INFO] Iniciando inyección automática de datos en H2...");
            System.out.println("****************************************************************");
            
            // 1. Crear oficinas técnicas de prueba si la base de datos en RAM está vacía
            Oficina oficinaSede = oficinaRepository.findByCodigoOficina("OFI-MDR")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Sede Central Madrid", "Paseo de la Castellana 45", "OFI-MDR")));

            oficinaRepository.findByCodigoOficina("OFI-LUG")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Delegación Galicia - Lugo", "Plaza de la Xunta S/N, Galicia", "OFI-LUG")));

            // 2. Crear credenciales del administrador del sistema
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombreCompleto("Administrador Ineco");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                admin.setOficina(oficinaSede);
                
                usuarioRepository.save(admin);
                System.out.println("[ÉXITO] USUARIO 'admin' CON CLAVE 'admin123' DISPONIBLE EN LOG-IN");
            }

            // 3. Crear proyecto de prueba de infraestructuras
            if (proyectoRepository.count() == 0) {
                Proyecto exp = new Proyecto();
                exp.setCodigoExpediente("EXP-2026-001");
                exp.setNombre("Modernización de Infraestructura de Red - Eje Atlántico");
                exp.setTipoInfraestructura("Ferroviaria");
                exp.setOficina(oficinaSede); // Vinculado a la Sede Central
                
                proyectoRepository.save(exp);
                System.out.println("[ÉXITO] EXPEDIENTE TÉCNICO 'EXP-2026-001' CARGADO CORRECTAMENTE");
            }

            // 4. Crear propietario/afectado inicial para expropiaciones
            if (propietarioRepository.count() == 0) {
                Propietario prop = new Propietario();
                prop.setDniCif("12345678Z");
                prop.setNombreCompleto("Construcciones Territoriales S.A.");
                prop.setDireccionNotificacion("Avenida de la Constitución 12, Planta 4");
                prop.setMunicipio("Madrid");
                prop.setTelefono("915000000");
                
                propietarioRepository.save(prop);
                System.out.println("[ÉXITO] AFECTADO CORPORATIVO DE PRUEBA INYECTADO EN H2 RAM");
            }
            
            System.out.println("****************************************************************");
            System.out.println("[INFO] Todos los módulos inicializados. Servidor listo para operar.");
            System.out.println("****************************************************************");
        };
    }
}
