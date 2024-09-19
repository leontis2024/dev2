package com.example.leontis.controllers;

import com.example.leontis.models.DiaFuncionamento;
import com.example.leontis.services.DiaFuncionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Dia de Funcionamento do Museu")
@RestController
@RequestMapping("/api/diaFuncionamento")
public class DiaFuncionamentoController {

    private final DiaFuncionamentoService diaFuncionamentoService;

    public DiaFuncionamentoController(DiaFuncionamentoService diaFuncionamentoService) {
        this.diaFuncionamentoService = diaFuncionamentoService;
    }

    @GetMapping("/selecionarPorIdMuseu/{id}")
    @Operation(summary = "Retorna os dias de funcionamento pelo ID do museu",
            description = "Retorna uma lista de dias de funcionamento de acordo com o ID do museu passado como parâmetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dias de funcionamento retornados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiaFuncionamento.class))),
            @ApiResponse(responseCode = "404", description = "Não foi possível encontrar dias de funcionamento para o museu",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    public ResponseEntity<?> buscarPorIdMuseu(@Parameter(description = "ID do museu cujos dias de funcionamento serão retornados")
                                              @PathVariable Long id) {
        try {
            List<DiaFuncionamento> diasFuncionamento = diaFuncionamentoService.buscarPorId(id);
            if (diasFuncionamento.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dias de funcionamento não encontrados para este museu.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(diasFuncionamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar dias de funcionamento.");
        }
    }

    @GetMapping("/selecionarTudo")
    @Operation(summary = "Lista todos os dias de funcionamento",
            description = "Retorna uma lista com todos os dias de funcionamento cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de dias de funcionamento retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiaFuncionamento.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum dia de funcionamento encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    public ResponseEntity<?> buscarTodos() {
        List<DiaFuncionamento> diasFuncionamento = diaFuncionamentoService.buscarTodos();
        if (diasFuncionamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dia de funcionamento encontrado.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(diasFuncionamento);
        }
    }
}
