package com.example.leontis.controllers;

import com.example.leontis.models.*;
import com.example.leontis.services.GeneroService;
import com.example.leontis.services.ObraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Obra")
@RestController
@RequestMapping("/api/obra")
public class ObraController {
    private final ObraService obraService;

    @Autowired
    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping("/selecionarObraPorID/{id}")
    @Operation(summary = "Retorna o obra pelo ID", description = "Retorna uma obra de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Obra retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o obra",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?>buscarPorId(@Parameter(description = "ID da obra a ser retornada")@PathVariable Long id) {
        try {
            Obra obra = obraService.buscarPorID(id);
            if (obra == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta obra não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(obra);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta obra não existe");
        }
    }

    @GetMapping("/selecionarObraBuscarPorNome/{nome}")
    @Operation(summary = "Retorna a obra pelo nome", description = "Retorna uma obra de acordo com o nome que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Obra retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar a obra",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?>buscarPorNome(@Parameter(description = "Nome da obra a ser retornada")@PathVariable String nome) {
        try {
            Obra obra = obraService.buscarPorNome(nome);
            if (obra == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta obra não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(obra);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta obra não existe");
        }
    }

    @GetMapping("/selecionarPorGenero/{id}")
    @Operation(summary = "Lista todas as obras por genero", description = "Retorna uma lista com as obras por genero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de obras retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as obras",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorGenero(@Parameter(description = "ID do genero para retorno da lista de obras")@Valid @RequestParam Long id) {
        List<Obra> obras= obraService.buscarPorGenero(id);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada nâo encontrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }

    @GetMapping("/selecionarPorMuseu/{id}")
    @Operation(summary = "Lista todas as obras por museu", description = "Retorna uma lista com as obras por museu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de obras retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as obras",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorMuseu(@Parameter(description = "ID do museu para retorno da lista de obras")@Valid @RequestParam Long id) {
        List<Obra> obras= obraService.buscarPorGenero(id);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada nâo encontrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }

    @GetMapping("/selecionarPorArtista/{id}")
    @Operation(summary = "Lista todas as obras por artista", description = "Retorna uma lista com as obras por artistas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de obras retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as obras",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorArtista(@Parameter(description = "ID do artista para retorno da lista de obras")@Valid @RequestParam Long id) {
        List<Obra> obras= obraService.buscarPorArtista(id);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada nâo encontrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todas as obras ordenadas por nome", description = "Retorna uma lista com as obras ordenadas por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de obras retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as obras",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodosOrdenadoPorNome() {
        List<Obra> obras= obraService.buscarTudo();
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }

}
