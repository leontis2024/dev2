package com.example.leontis.repository;

import com.example.leontis.models.Museu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MuseuRepository extends JpaRepository<Museu, Long> {
    @Override
    Optional<Museu> findById(Long id);
    Optional<Museu> findByNomeMuseu(String name);
    List<Museu> findAll();
}
