package com.example.leontis.repository;

import com.example.leontis.models.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra, Long> {
    Optional<Obra> findById(Long id);
    Optional<Obra> findByNomeObra(String obraName);
    List<Obra> findByIdGenero(Long idGenero);
    List<Obra> findByIdMuseu(Long idMuseu);
    List<Obra> findByIdArtista(Long idArtista);
    List<Obra> findAllByOrderByNomeObraAsc();
    @Query("SELECT o FROM Obra o WHERE o.idGenero IN :generos")
    List<Obra> findAllByGeneros(List<Long> generos);
    @Query("SELECT o FROM Obra o WHERE o.idMuseu IN :museus")
    List<Obra> findAllByMuseus(List<Long> museus);
    List<Obra> findByNomeObraContainingIgnoreCase(String nomeObra);

}
