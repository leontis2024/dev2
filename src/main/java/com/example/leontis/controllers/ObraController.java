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
    public ResponseEntity<?> buscarPorGenero(@Parameter(description = "ID do genero para retorno da lista de obras")@Valid @PathVariable Long id) {
        List<Obra> obras= obraService.buscarPorGenero(id);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada");
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
    public ResponseEntity<?> buscarPorMuseu(@Parameter(description = "ID do museu para retorno da lista de obras")@Valid @PathVariable Long id) {
        List<Obra> obras= obraService.buscarPorMuseu(id);
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
    public ResponseEntity<?> buscarPorArtista(@Parameter(description = "ID do artista para retorno da lista de obras")@Valid @PathVariable Long id) {
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

    @GetMapping("/selecionarPorGeneros")
    @Operation(summary = "Lista todas as obras por gêneros", description = "Retorna uma lista com as obras que possuem os gêneros informados mas se a lista estiver vazia retorna todas as obras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de obras retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar obras com os gêneros informados", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<?> buscarPorGeneros(
            @Parameter(description = "Lista de gêneros para retorno das obras") @Valid @RequestParam List<Long> generos) {
        List<Obra> obras = obraService.buscarObrasPorGeneros(generos);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas obras com os gêneros informados");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }

    @GetMapping("/selecionarPorMuseus")
    @Operation(summary = "Lista todas as obras por museus", description = "Retorna uma lista com as obras que possuem os museus informados mas se a lista estiver vazia retorna todas as obras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de obras retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar obras com os museus informados", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<?> buscarPorMuseus(
            @Parameter(description = "Lista de museus para retorno das obras") @Valid @RequestParam List<Long> museus) {
        List<Obra> obras = obraService.buscarObrasPorMuseus(museus);
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas obras com os museus s informados");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(obras);
        }
    }


}
