package com.example.leontis.services;

import com.example.leontis.models.Genero;
import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.repository.GeneroRepository;
import com.example.leontis.repository.UsuarioGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    private final GeneroRepository GeneroRepository;
    public GeneroService(GeneroRepository GeneroRepository) {
        this.GeneroRepository = GeneroRepository;
    }

    //    método para buscar genero por id
    public Genero buscarPorId(Long id) {

        Genero generos = GeneroRepository.findById(id).orElseThrow(RuntimeException::new);
        return generos;
    }

//    método para buscar todos os generos
    public List<Genero> buscarTodas() {
        return GeneroRepository.findAll();
    }

    public List<Genero> pesquisarGeneros(String pesquisa) {
        return GeneroRepository.findByNomeGeneroContainingIgnoreCase(pesquisa);
    }
}
