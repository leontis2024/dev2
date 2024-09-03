package com.example.leontis.controllers;

import com.example.leontis.models.Genero;
import com.example.leontis.models.Obra;
import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.services.GeneroService;
import com.example.leontis.services.ObraService;
import io.swagger.v3.oas.annotations.Parameter;
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


}
