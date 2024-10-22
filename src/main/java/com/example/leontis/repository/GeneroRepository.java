package com.example.leontis.repository;

import com.example.leontis.models.Genero;
import com.example.leontis.models.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    List<Genero> findAllByOrderByNomeGeneroAsc();
    Genero findById(long id);
    List<Genero> findByNomeGeneroContainingIgnoreCase(String nomeGenero);
}
