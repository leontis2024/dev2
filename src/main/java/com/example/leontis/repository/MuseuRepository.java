package com.example.leontis.repository;

import com.example.leontis.models.Museu;
import com.example.leontis.models.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MuseuRepository extends JpaRepository<Museu, Long> {
    @Override
    Optional<Museu> findById(Long id);
    Optional<Museu> findByNomeMuseu(String name);
    List<Museu> findAll();
    List<Museu> findByNomeMuseuContainingIgnoreCase(String nomeMuseu);


}
