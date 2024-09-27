package com.example.leontis.services;

import com.example.leontis.models.Museu;
import com.example.leontis.repository.GuiaRepository;
import com.example.leontis.repository.MuseuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuseuService {
    private MuseuRepository museuRepository;
    public MuseuService(MuseuRepository museuRepository) {
        this.museuRepository = museuRepository;
    }

    public Museu buscarPorId(long id) {
        return museuRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Museu buscarPorNome(String nome) {
        return museuRepository.findByNomeMuseu(nome).orElseThrow(RuntimeException::new);
    }

    public List<Museu> buscarTudo(){
        return museuRepository.findAll();
    }
}
