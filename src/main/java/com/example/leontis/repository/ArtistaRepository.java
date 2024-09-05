package com.example.leontis.repository;

import com.example.leontis.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findAll();
    Optional<Artista> findById(long id);
    Optional<Artista> findByNomeArtista(String nomeArtista);
}
