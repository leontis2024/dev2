package com.example.leontis.repository;

import com.example.leontis.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
//    mudando o delete para fazer menos requisições no banco
    @Modifying
    @Query("DELETE FROM Usuario e WHERE e.id = ?1")
    void deleteById(String id);

//  assinatura de um método para buscar usuário por parametros especificos
    Usuario findByEmailLikeIgnoreCase(String email);
    Usuario findByTelefone(String tel);



}
