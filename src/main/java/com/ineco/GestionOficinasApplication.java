package com.ineco;

import com.ineco.model.Oficina;
import com.ineco.model.Usuario;
import com.ineco.model.Proyecto;
import com.ineco.model.Propietario;
import com.ineco.model.Finca;
import com.ineco.repository.OficinaRepository;
import com.ineco.repository.UsuarioRepository;
import com.ineco.repository.ProyectoRepository;
import com.ineco.repository.PropietarioRepository;
import com.ineco.repository.FincaRepository;
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

    @Bean
    CommandLineRunner inicializarDatosDePrueba(
            OficinaRepository oficinaRepository, 
            UsuarioRepository usuarioRepository, 
            ProyectoRepository proyectoRepository,
            PropietarioRepository propietarioRepository,
            FincaRepository fincaRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("****************************************************************");
            System.out.println("[INFO] Iniciando inyección automática de datos en H2...");
            System.out.println("****************************************************************");
            
            // 1. Crear oficina de prueba
            Oficina oficinaSede = oficinaRepository.findByCodigoOficina("OFI-MDR")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Sede Central Madrid", "Paseo de la Castellana 45", "OFI-MDR")));

            oficinaRepository.findByCodigoOficina("OFI-LUG")
                .orElseGet(() -> oficinaRepository.save(new Oficina("Delegación Galicia - Lugo", "Plaza de la Xunta S/N, Galicia", "OFI-LUG")));

            // 2. Crear administrador de prueba
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombreCompleto("Administrador Ineco");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                admin.setOficina(oficinaSede);
                usuarioRepository.save(admin);
                System.out.println("[ÉXITO] USUARIO 'admin' LISTO");
            }

            // 3. Crear proyecto de prueba
            Proyecto exp = null;
            if (proyectoRepository.count() == 0) {
                exp = new Proyecto();
                exp.setCodigoExpediente("EXP-2026-001");
                exp.setNombre("Modernización de Infraestructura de Red - Eje Atlántico");
                exp.setTipoInfraestructura("Ferroviaria");
                exp.setOficina(oficinaSede);
                exp = proyectoRepository.save(exp);
                System.out.println("[ÉXITO] EXPEDIENTE 'EXP-2026-001' CARGADO");
            } else {
                exp = proyectoRepository.findAll().get(0);
            }

            // 4. Crear propietario de prueba
            Propietario prop = null;
            if (propietarioRepository.count() == 0) {
                prop = new Propietario();
                prop.setDniCif("12345678Z");
                prop.setNombreCompleto("Construcciones Territoriales S.A.");
                prop.setDireccionNotificacion("Avenida de la Constitución 12, Planta 4");
                prop.setMunicipio("Madrid");
                prop.setTelefono("915000000");
                prop = propietarioRepository.save(prop);
                System.out.println("[ÉXITO] PROPIETARIO AFECTADO CARGADO");
            } else {
                prop = propietarioRepository.findAll().get(0);
            }

            // 5. Crear predio / finca afectada vinculada a ambos
            if (fincaRepository.count() == 0) {
                Finca f = new Finca();
                f.setNumeroExpedienteFinca("LU-LUG-015");
                f.setPoligono(14);
                f.setParcela(245);
                f.setSuperficieAfectada(1240.50);
                f.setTipoCultivo("Rústico Prado");
                f.setProyecto(exp);
                f.setPropietario(prop);
                fincaRepository.save(f);
                System.out.println("[ÉXITO] PREDIO / PARCELA AFECTADA 'LU-LUG-015' INYECTADO CON ÉXITO");
            }
            
            System.out.println("****************************************************************");
            System.out.println("[INFO] Modificaciones procesadas con éxito.");
            System.out.println("****************************************************************");
        };
    }
}
