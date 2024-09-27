package com.example.leontis.repository;

import com.example.leontis.models.ArtistaGenero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaGeneroRepository extends JpaRepository<ArtistaGenero, Long> {
    List<ArtistaGenero> findByIdGenero(Long idGenero);
    List<ArtistaGenero> findByIdArtista(Long idArtista);
    List<ArtistaGenero> findAll();
}
