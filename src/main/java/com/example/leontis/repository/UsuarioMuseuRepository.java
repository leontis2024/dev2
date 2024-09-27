package com.example.leontis.repository;

import com.example.leontis.models.UsuarioMuseu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioMuseuRepository extends JpaRepository<UsuarioMuseu,Long> {

    //  assinatura de um método para buscar usuário por parametros especificos
    List<UsuarioMuseu> findByIdUsuario(Long idUsuario);
    UsuarioMuseu findUsuarioMuseuByIdUsuarioAndIdMuseu(Long idUsuario, Long idMuseu);
}
