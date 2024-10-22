package com.example.leontis.controllers;

import com.example.leontis.models.Museu;
import com.example.leontis.models.Obra;
import com.example.leontis.services.MuseuService;
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

@Tag(name="Museu")
@RestController
@RequestMapping("/api/museu")
public class MuseuController {
    private final MuseuService museuService;

    @Autowired
    public MuseuController(MuseuService museuService) {
        this.museuService = museuService;
    }
    @GetMapping("/selecionarMuseuPorId/{id}")
    @Operation(summary = "Retorna o museu pelo ID", description = "Retorna uma museu de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Museu retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Museu.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o museu",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "ID do museu a ser retornado")@PathVariable Long id) {
        try {
            Museu museu = museuService.buscarPorId(id);
            if (museu == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este museu não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(museu);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este museu não existe");
        }
    }

    @GetMapping("/selecionarMuseuBuscarPorNome/{nome}")
    @Operation(summary = "Retorna o museu pelo nome", description = "Retorna um museu de acordo com o nome que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Museu retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Museu.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o museu",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?>buscarPorNome(@Parameter(description = "Nome do museu a ser retornado")@PathVariable String nome) {
        try {
            Museu museu = museuService.buscarPorNome(nome);
            if (museu == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este museu não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(museu);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este museu não existe");
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todos os museus", description = "Retorna uma lista com todos os museus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de museus retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Museu.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar os museus",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodos() {
        List<Museu> museus= museuService.buscarTudo();
        if (museus.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Museu não econtrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(museus);
        }
    }

    @GetMapping("/pesquisarMuseu")
    @Operation(summary = "Pesquisa museus por nome", description = "Retorna uma lista de museus cujo nome contém as letras fornecidas na ordem digitada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Museus retornados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Museu.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum museu encontrado com os critérios fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public ResponseEntity<List<Museu>> pesquisarMuseus(
            @Parameter(description = "Parte do nome do museu para pesquisa") @RequestParam String pesquisa) {
        List<Museu> museus = museuService.pesquisarMuseus(pesquisa);
        if (museus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(museus, HttpStatus.OK);
    }
}
