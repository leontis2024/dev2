package com.example.leontis.controllers;

import com.example.leontis.models.Artista;
import com.example.leontis.models.ArtistaGenero;
import com.example.leontis.models.Guia;
import com.example.leontis.services.ArtistaGeneroService;
import com.example.leontis.services.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Artista Gênero")
@RestController
@RequestMapping("/api/artistaGenero")
public class ArtistaGeneroController {
    private final ArtistaGeneroService artistaGeneroService;

    @Autowired
    public ArtistaGeneroController(ArtistaGeneroService artistaGeneroService) {
        this.artistaGeneroService = artistaGeneroService;
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todas as ralações de genero/artista", description = "Retorna uma lista com as relações de genero/artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de relações retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ArtistaGenero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as relações",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodos() {
        List<ArtistaGenero> artista= artistaGeneroService.buscarTodos();
        if (artista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relação não econtrada");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }
    }

    @GetMapping("/selecionarPorGenero/{id}")
    @Operation(summary = "Retorna uma lista de relação pelo ID do genero", description = "Retorna uma lista de relações de acordo com ID do que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relação retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ArtistaGenero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar a relação",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorGenero(@Parameter(description = "ID do genero com relação ao artista a ser retornado")@PathVariable Long id) {
        try {
            List<ArtistaGenero> artista = artistaGeneroService.buscarPorGenero(id);
            if (artista == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
        }
    }

    @GetMapping("/selecionarPorArtista/{id}")
    @Operation(summary = "Retorna uma lista de relação pelo ID do artista", description = "Retorna uma lista de relações de acordo com ID do que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Relação retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ArtistaGenero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar a relação",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorArtista(@Parameter(description = "ID do artista com relação ao genero a ser retornado")@PathVariable Long id) {
        try {
            List<ArtistaGenero> artista = artistaGeneroService.buscarPorArtista(id);
            if (artista == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
        }
    }


}
