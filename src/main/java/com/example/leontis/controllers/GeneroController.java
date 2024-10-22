package com.example.leontis.controllers;

import com.example.leontis.config.Views;
import com.example.leontis.models.Genero;
import com.example.leontis.services.GeneroService;
import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gênero")
@RestController
@RequestMapping("/api/genero")
public class GeneroController {

    private final GeneroService generoService;

    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @Operation(summary = "Retorna o gênero pelo ID", description = "Retorna um gênero de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Gênero retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Genero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o gênero",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    @JsonView(Views.Completo.class)
    @GetMapping("/selecionarGeneroPorID/{id}")
    public ResponseEntity<?>buscarPorId(@Parameter(description = "ID do genero a ser retornado")@PathVariable Long id) {
        try {
            Genero genero = generoService.buscarPorId(id);
            if (genero == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este genero não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(genero);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este genero não existe");
        }
    }

    @Operation(summary = "Retorna todos os gêneros parcialmente", description = "Retorna uma lista com todos os gêneros trazendo apenas os campos id, nomeGenero e introdução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Gêneros retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Genero.class))),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    @JsonView(Views.Resumo.class)
    @GetMapping("/selecionarTodosGenerosParcial")
    public List<Genero> buscarPorTodosParcial() {
        return generoService.buscarTodas();
    }

    @Operation(summary = "Retorna todos os gêneros", description = "Retorna uma lista com todos os gêneros trazendo apenas os campos id, nomeGenero e introdução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Gêneros retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Genero.class))),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    @JsonView(Views.Completo.class)
    @GetMapping("/selecionarTodosGeneros")
    public List<Genero> buscarTodos() {
        return generoService.buscarTodas();
    }

    @GetMapping("/pesquisarGenero")
    @Operation(summary = "Pesquisa generos por nome", description = "Retorna uma lista de generos cujo nome contém as letras fornecidas na ordem digitada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Generos retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Genero.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum genero encontrado com os critérios fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<List<Genero>> pesquisarGeneros(
            @Parameter(description = "Parte do nome do genero para pesquisa") @RequestParam String pesquisa) {
        List<Genero> generos = generoService.pesquisarGeneros(pesquisa);
        if (generos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(generos, HttpStatus.OK);
    }

}
