package com.example.leontis.services;

import com.example.leontis.models.Artista;
import com.example.leontis.models.EnderecoMuseu;
import com.example.leontis.repository.ArtistaRepository;
import com.example.leontis.repository.EnderecoMuseuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoMuseuService {
    private final EnderecoMuseuRepository enderecoMuseuRepository;

    public EnderecoMuseuService(EnderecoMuseuRepository enderecoMuseuRepository) {
        this.enderecoMuseuRepository = enderecoMuseuRepository;
    }

    public EnderecoMuseu buscarPorId(Long id) {

        EnderecoMuseu enderecoMuseu = enderecoMuseuRepository.findById(id).orElseThrow(RuntimeException::new);
        return enderecoMuseu;
    }
    public List<EnderecoMuseu> buscarTodos() {
        return enderecoMuseuRepository.findAll();
    }
}
