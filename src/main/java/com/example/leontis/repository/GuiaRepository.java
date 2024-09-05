package com.example.leontis.repository;

import com.example.leontis.models.Guia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuiaRepository extends JpaRepository<Guia, Long> {
    List<Guia> findByIdMuseu(Long id);
    Optional<Guia> findById(Long id);
    List<Guia> findAll();


}
