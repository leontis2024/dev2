package com.example.leontis.repository;

import com.example.leontis.models.DiaFuncionamento;
import com.example.leontis.models.EnderecoMuseu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaFuncionamentoRepository extends JpaRepository<DiaFuncionamento, Long> {
    List<DiaFuncionamento> findAll();
    List<DiaFuncionamento> findByIdMuseu(long id);
}
