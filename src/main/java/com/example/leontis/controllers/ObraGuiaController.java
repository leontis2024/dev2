package com.example.leontis.controllers;

import com.example.leontis.models.Obra;
import com.example.leontis.models.ObraGuia;
import com.example.leontis.services.ObraGuiaService;
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

@Tag(name="Obra/Guia")
@RestController
@RequestMapping("/api/obraGuia")
public class ObraGuiaController {

    private final ObraGuiaService obraGuiaService;
    @Autowired
    public ObraGuiaController(ObraGuiaService obraGuiaService) {
        this.obraGuiaService = obraGuiaService;
    }

    @GetMapping("/selecionarObraGuiaPorID/{id}")
    @Operation(summary = "Retorna o obra/guia pelo ID", description = "Retorna uma obra/guia de acordo com ID do guia que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Obra/guia retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ObraGuia.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar a obra/guia",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarPorIdGuia(@Parameter(description = "ID da relação obra/guia a ser retornada")@PathVariable Long id) {
        try {
            List<ObraGuia> obra = obraGuiaService.buscarObraGuia(id);
            if (obra.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(obra);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta relação não existe");
        }
    }
}
