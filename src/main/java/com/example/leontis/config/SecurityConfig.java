package com.example.leontis.config;
import com.example.leontis.services.CustomUserDetailsService;
import com.example.leontis.JwtAuthenticatorFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers("/swagger-ui/**",  "/v3/api-docs/**","/api/usuario/inserir","/favicon.ico/**").permitAll()
                        .requestMatchers("/api/auth/login","/api/genero/selecionarTodosGenerosParcial","/api/usuario/atualizar/**","/api/usuarioGenero/inserir" ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthenticatorFilter(userDetailsService, secretKey()), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService);
////                .exceptionHandling(exceptions -> exceptions
////                        .authenticationEntryPoint((request, response, authException) -> {
////                            response.setContentType("application/json");
////                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                            response.getWriter().write("{\"error\": \"Unauthorized: " +
////                                    authException.getMessage() + "\"}");
////                        })
////                        .accessDeniedHandler((request, response, accessDeniedException) -> {
////                            response.setContentType("application/json");
////                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////                            response.getWriter().write("{\"error\": \"Access Denied: " +
////                                    accessDeniedException.getMessage() + "\"}");
////                        })
//                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecretKey secretKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
