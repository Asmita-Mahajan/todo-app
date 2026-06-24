//package com.todoapp.backend.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                // Disable CSRF for APIs
////                .csrf(csrf -> csrf.disable())
////                // Configure authorization rules
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/tasks/**").permitAll() // allow your task endpoints
////                        .anyRequest().authenticated()                 // everything else requires login
////                );              // everything else requires login
//
//        http
//                .csrf(csrf -> csrf.disable()) // disable CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // allow ALL endpoints
//                );
//        return http.build();
//    }
//}
