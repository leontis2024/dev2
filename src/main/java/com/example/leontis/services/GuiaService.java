package com.example.leontis.services;

import com.example.leontis.models.Guia;
import com.example.leontis.models.Obra;
import com.example.leontis.repository.GuiaRepository;
import com.example.leontis.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuiaService {
    private GuiaRepository guiaRepository;
    public GuiaService(GuiaRepository guiaRepository) {
        this.guiaRepository = guiaRepository;
    }

    public Guia buscarPorId(Long id) {
        Guia guia = guiaRepository.findById(id).orElseThrow(RuntimeException::new);
        return guia;
    }

    public List<Guia> buscarPorMuseu(Long museuId) {
        return guiaRepository.findByIdMuseu(museuId);
    }

    public List<Guia> buscarTudo() {
        return guiaRepository.findAll();
    }
}
