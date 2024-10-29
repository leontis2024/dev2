package com.example.leontis.controllers;

import com.example.leontis.models.MuseuAdm;
import com.example.leontis.models.Usuario;
import com.example.leontis.services.MuseuAdmService;
import com.example.leontis.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "http://arearestritaleontis.s3.us-east-1.amazonaws.com")
@Tag(name="Museu ADM")
@RestController
@RequestMapping("/api/museuAdm")
public class MuseuAdmController {
    private final MuseuAdmService MuseuAdmService;

    @Autowired
    public MuseuAdmController(MuseuAdmService MuseuAdmService) {
        this.MuseuAdmService = MuseuAdmService;
    }

    @PostMapping("/inserir")
    @Operation(summary = "Inserir museu adm",description = "Insere um museu adm no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Museu ADM inserido com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(example = "12345"))),
            @ApiResponse(responseCode = "500",description = "Erro no servidor",content = @Content)
    })
    public ResponseEntity<?> inserirMuseu(@Valid @RequestBody MuseuAdm museuAdm) {
        try {
            String museuId = MuseuAdmService.criarMuseuAdm(museuAdm.getEmailAdm(),museuAdm.getSenhaAdm());
            return ResponseEntity.status(HttpStatus.CREATED).body(museuId);
        } catch (RuntimeException e) {
            // Tratamento da exceção RuntimeException lançada pelo serviço
            String log = e.getMessage();
            Pattern pattern = Pattern.compile("ERROR:.*");
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                String mesage =matcher.group();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir museu adm: " + mesage);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir museu adm: " + e.getMessage());
        }

    }

}
