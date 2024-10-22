package com.example.leontis.controllers;

import com.example.leontis.models.Guia;
import com.example.leontis.models.Obra;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Guia")
@RestController
@RequestMapping("/api/guia")
public class GuiaController {
    private final GuiaService guiaService;

    @Autowired
    public GuiaController(GuiaService guiaService) {
        this.guiaService = guiaService;
    }

    @GetMapping("/selecionarGuiaPorID/{id}")
    @Operation(summary = "Retorna o guia pelo ID", description = "Retorna um guia de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Guia retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Guia.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o guia",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "ID do guia a ser retornado")@PathVariable Long id) {
        try {
            Guia guia = guiaService.buscarPorId(id);
            if (guia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(guia);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todas os guias", description = "Retorna uma lista com todos os guias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de guias retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Guia.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar os guias",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodosOrdenadoPorNome() {
        List<Guia> guias= guiaService.buscarTudo();
        if (guias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não econtrada");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(guias);
        }
    }

    @GetMapping("/selecionarGuiaPorMuseu/{id}")
    @Operation(summary = "Retorna o guia pelo ID do museu", description = "Retorna um guia de acordo com ID do que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Guia retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Guia.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o guia",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorMuseu(@Parameter(description = "ID do museu com relação ao guia a ser retornado")@PathVariable Long id) {
        try {
            List<Guia> guia = guiaService.buscarPorMuseu(id);
            if (guia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(guia);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
        }
    }

    @GetMapping("/selecionarGuiaBuscarPorNome/{nome}")
    @Operation(summary = "Retorna o guia pelo nome", description = "Retorna um guia de acordo com o nome que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Guia retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Guia.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o guia",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?>buscarPorNome(@Parameter(description = "Nome do guia a ser retornado")@PathVariable String nome) {
        try {
            Guia guia = guiaService.buscarPorNome(nome);
            if (guia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(guia);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este guia não existe");
        }
    }
    @GetMapping("/pesquisarGuia")
    @Operation(summary = "Pesquisa guias por nome", description = "Retorna uma lista de guias cujo nome contém as letras fornecidas na ordem digitada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "guias retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Guia.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum guia encontrado com os critérios fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<List<Guia>> pesquisarGuias(
            @Parameter(description = "Parte do nome do guia para pesquisa") @RequestParam String pesquisa,@Parameter(description = "ID do guia") @RequestParam Long id ) {
        List<Guia> guias = guiaService.pesquisarGuias(id,pesquisa);
        if (guias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guias, HttpStatus.OK);
    }

}
