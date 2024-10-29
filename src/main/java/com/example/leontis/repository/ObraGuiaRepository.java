package com.example.leontis.repository;

import com.example.leontis.models.ObraGuia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObraGuiaRepository extends JpaRepository<ObraGuia, Long> {
    List<ObraGuia> findByIdGuiaOrderByNrOrdem(Long guia);
}
