package org.example.cerveza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/beer/**").permitAll()  // Permitir acceso a los endpoints de Beer sin autenticación
                .anyRequest().authenticated()  // El resto de las rutas requieren autenticación
                .and()
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))  // Habilitar CSRF para aplicaciones que lo necesiten
                .formLogin(login -> login
                        .loginPage("/login")  // Página de login personalizada (si se usa login con formularios)
                        .permitAll())  // Permitir acceso sin autenticación a la página de login
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Ruta personalizada de logout (si es necesario)
                        .permitAll());

        return http.build();
    }
}
