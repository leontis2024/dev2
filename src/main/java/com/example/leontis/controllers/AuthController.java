package com.example.leontis.controllers;

import com.example.leontis.models.LoginRequest;
import com.example.leontis.models.Usuario;
import com.example.leontis.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.regex.Pattern.matches;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    public final SecretKey secretKey;
    public final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, SecretKey secretKey, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.secretKey = secretKey;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/auth/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        Usuario user = usuarioRepository.findByEmailLikeIgnoreCase(loginRequest.getUsername());

        if (user != null && matches(loginRequest.getPassword(), user.getSenha())) {
            try {
                // Gerar access token (duração curta, ex.: 1 minuto)
                String accessToken = Jwts.builder()
                        .setSubject(loginRequest.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() +  3_600_000)) // 1 hora
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();

                // Gerar refresh token (duração longa, ex.: 7 dias)
                String refreshToken = Jwts.builder()
                        .setSubject(loginRequest.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() + 7 * 86_400_000)) // 7 dias
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();

                logger.info("Generated Access Token: {}", accessToken);
                logger.info("Generated Refresh Token: {}", refreshToken);

                // Retornar ambos os tokens
                return Map.of("accessToken", "Bearer " + accessToken, "refreshToken", refreshToken);
            } catch (Exception e) {
                logger.error("Erro ao gerar os tokens JWT", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar os tokens JWT", e);
            }
        } else {
            logger.error("Credenciais inválidas para username: {}", loginRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }
    }

    @PostMapping("/auth/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> tokens) {
        String refreshToken = tokens.get("refreshToken");
        try {
            // Verificar se o refresh token é válido
            String username = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();

            // Gerar novo access token
            String newAccessToken = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + 3_600_000)) // 1 hora
                    .signWith(secretKey, SignatureAlgorithm.HS512)
                    .compact();

            logger.info("Generated New Access Token: {}", newAccessToken);

            // Retorna o novo access token
            return Map.of("accessToken", "Bearer " + newAccessToken);
        } catch (Exception e) {
            logger.error("Invalid refresh token", e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token", e);
        }
    }


}