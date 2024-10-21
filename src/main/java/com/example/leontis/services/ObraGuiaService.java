package com.example.leontis.services;

import com.example.leontis.models.ObraGuia;
import com.example.leontis.repository.ObraGuiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraGuiaService {

    private ObraGuiaRepository obraGuiaRepository;
    public ObraGuiaService(ObraGuiaRepository obraGuiaRepository) {
        this.obraGuiaRepository = obraGuiaRepository;
    }

    public List<ObraGuia> buscarObraGuia(Long guia) {
        return obraGuiaRepository.findByIdGuia(guia);
    }
}
