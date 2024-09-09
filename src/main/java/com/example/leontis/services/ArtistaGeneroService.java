package com.example.leontis.services;

import com.example.leontis.models.Artista;
import com.example.leontis.models.ArtistaGenero;
import com.example.leontis.models.Genero;
import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.repository.ArtistaGeneroRepository;
import com.example.leontis.repository.ArtistaRepository;
import com.example.leontis.repository.GeneroRepository;
import com.example.leontis.repository.UsuarioGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaGeneroService {
    private final ArtistaGeneroRepository artistaGeneroRepository;

    public ArtistaGeneroService(ArtistaGeneroRepository artistaGeneroRepository) {
        this.artistaGeneroRepository = artistaGeneroRepository;
    }

    public List<ArtistaGenero> buscarTodos() {
        return artistaGeneroRepository.findAll();
    }


}