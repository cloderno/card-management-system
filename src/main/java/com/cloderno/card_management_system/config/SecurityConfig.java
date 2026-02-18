package com.cloderno.card_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // если REST API
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/users/**").permitAll() // регистрация, список пользователей можно открыть
                    .anyRequest().authenticated() // всё остальное защищено
            );

        return http.build();
    }
}
