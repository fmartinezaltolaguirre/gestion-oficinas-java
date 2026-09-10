package com.ineco;

import com.ineco.model.Oficina;
import com.ineco.model.Usuario;
import com.ineco.model.Proyecto;
import com.ineco.model.Propietario;
import com.ineco.model.Finca;
import com.ineco.model.Acta;
import com.ineco.repository.OficinaRepository;
import com.ineco.repository.UsuarioRepository;
import com.ineco.repository.ProyectoRepository;
import com.ineco.repository.PropietarioRepository;
import com.ineco.repository.FincaRepository;
import com.ineco.repository.ActaRepository;
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
            } else {
                prop = propietarioRepository.findAll().get(0);
            }

            // 5. Crear parcela afectada vinculada
            Finca f = null;
            if (fincaRepository.count() == 0) {
                f = new Finca();
                f.setNumeroExpedienteFinca("LU-LUG-015");
                f.setPoligono(14);
                f.setParcela(245);
                f.setSuperficieAfectada(1240.50);
                f.setTipoCultivo("Rústico Prado");
                f.setProyecto(exp);
                f.setPropietario(prop);
                f = fincaRepository.save(f);
            } else {
                f = fincaRepository.findAll().get(0);
            }

            // 6. Crear acta jurídica vinculada a la parcela
            if (actaRepository.count() == 0) {
                Acta a = new Acta();
                a.setNumeroActa("ACT-2026-089");
                a.setFechaActaPrevia(LocalDate.of(2026, 3, 15));
                a.setFechaActaOcupacion(LocalDate.of(2026, 6, 20));
                a.setImporteJustiprecio(18500.75);
                a.setEstadoPago("PAGADO");
                a.setFinca(f); // Vinculación OneToOne
                actaRepository.save(a);
                System.out.println("[ÉXITO] HITO JURÍDICO 'ACT-2026-089' INYECTADO CORRECTAMENTE");
            }
            
            System.out.println("****************************************************************");
            System.out.println("[INFO] Datos cargados con éxito. Ecosistema listo.");
            System.out.println("****************************************************************");
        };
    }
}
