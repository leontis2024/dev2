package com.example.leontis.repository;

import com.example.leontis.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//  assinatura de um método para buscar usuário por parametros especificos
    Usuario findByEmailLikeIgnoreCase(String email);
    Usuario findByTelefone(String tel);



}
