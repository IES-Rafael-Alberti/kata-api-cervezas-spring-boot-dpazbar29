package org.example.cerveza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de autorización de solicitudes
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()  // Permitir acceso a la consola H2
                        .anyRequest().authenticated()                    // Requiere autenticación para el resto de las rutas
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")       // Ignorar CSRF en la consola H2
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable())  // Configuración de encabezado de seguridad para permitir frames
                );

        return http.build();
    }
}
