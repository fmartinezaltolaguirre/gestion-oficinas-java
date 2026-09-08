package com.ineco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define el algoritmo de cifrado para las contraseñas de los usuarios.
     * BCrypt aplica un hash seguro e irreversible en la base de datos.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración principal del cortafuegos de la aplicación web.
     * Define qué páginas son públicas y cuáles requieren login o roles específicos.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Reglas de autorización en las rutas URL
            .authorizeHttpRequests(auth -> auth
                // Permitir la carga de recursos estáticos (CSS, JS, Imágenes) sin autenticación
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // Restringir la gestión de usuarios exclusivamente al rol Administrador
                .requestMatchers("/usuarios/**").hasRole("ADMIN")
                // El resto de la aplicación requiere que el usuario esté autenticado
                .anyRequest().authenticated()
            )
            // 2. Configuración del formulario de Login integrado con las plantillas HTML
            .formLogin(form -> form
                .loginPage("/login") // Ruta de tu controlador que sirve la vista HTML de login
                .defaultSuccessUrl("/oficinas", true) // Redirección tras iniciar sesión con éxito
                .permitAll()
            )
            // 3. Configuración del proceso de Logout (Cierre de sesión)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redirección tras salir del sistema
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }
}
