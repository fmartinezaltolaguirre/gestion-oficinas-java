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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // PERMITIR LIBRE ACCESO A RECURSOS LOCALES Y SERVIDORES EXTERNOS DE BOOTSTRAP
                .requestMatchers("/css/**", "/js/**", "/images/**", "/error").permitAll()
                .requestMatchers("https://jsdelivr.net**").permitAll() // <-- OBLIGATORIO PARA LOGRAR EL DISEÑO ELEGANTE
                
                // La gestión de usuarios requiere rol ADMIN
                .requestMatchers("/usuarios/**").hasRole("ADMIN")
                // El resto de la aplicación requiere autenticación
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // Fuerza la ruta de procesamiento estándar
                .defaultSuccessUrl("/oficinas", true)
                .failureUrl("/login?error") // Asegura el parámetro de error
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            // Desactivamos temporalmente CSRF solo para facilitar las pruebas en Docker local
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
