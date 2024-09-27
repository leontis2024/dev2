package com.example.leontis.controllers;

import com.example.leontis.models.Artista;
import com.example.leontis.models.EnderecoMuseu;
import com.example.leontis.services.ArtistaService;
import com.example.leontis.services.EnderecoMuseuService;
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
@Tag(name="Endereço museu")
@RestController
@RequestMapping("/api/enderecoMuseu")
public class EnderecoMuseuController {
    private final EnderecoMuseuService enderecoMuseuService;

    @Autowired
    public EnderecoMuseuController(EnderecoMuseuService enderecoMuseuService) {
        this.enderecoMuseuService = enderecoMuseuService;
    }

    @GetMapping("/selecionarPorID/{id}")
    @Operation(summary = "Retorna o endereço do museu pelo ID", description = "Retorna um endereço do museu de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Endereço do museu retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = EnderecoMuseu.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o endereço do museu",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "ID do endereço do museu a ser retornado")@PathVariable Long id) {
        try {
            EnderecoMuseu enderecoMuseu = enderecoMuseuService.buscarPorId(id);
            if (enderecoMuseu == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este endereço não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(enderecoMuseu);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este endereço não existe");
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todos os endereços de museus", description = "Retorna uma lista com todos os endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de endereços retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = EnderecoMuseu.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar os endereços",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarTodos() {
        List<EnderecoMuseu> enderecoMuseu= enderecoMuseuService.buscarTodos();
        if (enderecoMuseu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não econtrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(enderecoMuseu);
        }
    }

}
