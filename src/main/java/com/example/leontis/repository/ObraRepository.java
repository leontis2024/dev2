package com.example.leontis.repository;

import com.example.leontis.models.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra, Long> {
    Optional<Obra> findById(Long Id);
    Obra findByNomeObra(String obraName);
    List<Obra> findByIdGenero(Long idGenero);
    List<Obra> findByIdMuseu(Long idMuseu);
    List<Obra> findByIdArtista(Long idArtista);


}
