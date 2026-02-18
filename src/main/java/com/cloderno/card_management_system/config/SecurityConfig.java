package com.cloderno.card_management_system.config;

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
            .csrf(csrf -> csrf.disable()) // если REST API
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/users/**").permitAll() // регистрация, список пользователей можно открыть
                    .anyRequest().authenticated() // всё остальное защищено
            );

        return http.build();
    }
}
