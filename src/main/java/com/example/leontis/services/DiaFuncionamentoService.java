package com.example.leontis.services;

import com.example.leontis.models.DiaFuncionamento;
import com.example.leontis.models.EnderecoMuseu;
import com.example.leontis.repository.DiaFuncionamentoRepository;
import com.example.leontis.repository.EnderecoMuseuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaFuncionamentoService {
    private final DiaFuncionamentoRepository diaFuncionamentoRepository;

    public DiaFuncionamentoService(DiaFuncionamentoRepository diaFuncionamentoRepository) {
        this.diaFuncionamentoRepository = diaFuncionamentoRepository;
    }


    public List<DiaFuncionamento> buscarTodos() {
        return diaFuncionamentoRepository.findAll();
    }

    public List<DiaFuncionamento> buscarPorId(Long id) {
        return diaFuncionamentoRepository.findByIdMuseu(id);
    }
}
