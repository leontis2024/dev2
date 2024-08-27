package com.example.leontis.repository;

import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.models.UsuarioMuseu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioGeneroRepository extends JpaRepository<UsuarioGenero, Long> {

    //  assinatura de um método para buscar usuário por parametros especificos
    List<UsuarioGenero> findByIdUsuario(Long idUsuario);
    UsuarioGenero findUsuarioMuseuByIdUsuarioAndIdGenero(Long idUsuario, Long idGenero);
}
