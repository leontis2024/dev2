package com.example.leontis.controllers;

import com.example.leontis.models.Artista;
import com.example.leontis.models.Guia;
import com.example.leontis.models.Obra;
import com.example.leontis.services.ArtistaService;
import com.example.leontis.services.GuiaService;
import com.example.leontis.services.ObraService;
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

@Tag(name="Artista")
@RestController
@RequestMapping("/api/artista")
public class ArtistaController {
    private final ArtistaService artistaService;

    @Autowired
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping("/selecionarPorID/{id}")
    @Operation(summary = "Retorna o artista pelo ID", description = "Retorna um artista de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Artista retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Artista.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o artista",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "ID do artista a ser retornado")@PathVariable Long id) {
        try {
            Artista artista = artistaService.buscarPorId(id);
            if (artista == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este artista não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este artista não existe");
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todas os artistas", description = "Retorna uma lista com todos os artistas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de artistas retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Artista.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar os artistas",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodos() {
        List<Artista> artista= artistaService.buscarTodos();
        if (artista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista não econtrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }
    }

    @GetMapping("/selecionarPorNome")
    @Operation(summary = "Retorna o artista pelo nome", description = "Retorna um artista de acordo com o nome que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Artista retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Artista.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o artista",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorNome(@Parameter(description = "Nome do artista a ser retornado")@PathVariable String nome) {
        try {
            Artista artista = artistaService.buscarPorNome(nome);
            if (artista == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este artista não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(artista);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este artista não existe");
        }
    }

    }
