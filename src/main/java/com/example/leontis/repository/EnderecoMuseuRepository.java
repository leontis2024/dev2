package com.example.leontis.repository;

import com.example.leontis.models.Artista;
import com.example.leontis.models.EnderecoMuseu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoMuseuRepository extends JpaRepository<EnderecoMuseu, Long> {

    List<EnderecoMuseu> findAll();
    EnderecoMuseu findById(long id);
}
