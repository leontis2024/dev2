package com.example.leontis.services;

import com.example.leontis.models.Artista;
import com.example.leontis.models.Genero;
import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.repository.ArtistaRepository;
import com.example.leontis.repository.GeneroRepository;
import com.example.leontis.repository.UsuarioGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public Artista buscarPorId(Long id) {

        Artista artista = artistaRepository.findById(id).orElseThrow(RuntimeException::new);
        return artista;
    }
    public List<Artista> buscarTodos() {
        return artistaRepository.findAll();
    }
    public Artista buscarPorNome(String nome) {
        Artista artista=artistaRepository.findByNomeArtista(nome).orElseThrow(RuntimeException::new);
        return artista;

    }
}