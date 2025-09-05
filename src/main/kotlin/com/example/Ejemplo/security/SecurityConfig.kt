//package com.example.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//class SecurityConfig {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() } // desactiva CSRF
//            .authorizeHttpRequests {
//                it.anyRequest().permitAll() // permite todo
//            }
//            .formLogin { it.disable() }
//            .httpBasic { it.disable() }
//
//        return http.build()
//    }
//}
