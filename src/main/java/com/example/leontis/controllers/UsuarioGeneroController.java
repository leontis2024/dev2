package com.example.leontis.controllers;

import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.models.UsuarioMuseu;
import com.example.leontis.services.UsuarioGeneroService;
import com.example.leontis.services.UsuarioMuseuService;
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

@Tag(name = "Usuário Gênero")
@RestController
@RequestMapping("/api/usuarioGenero")
public class UsuarioGeneroController {
    private final UsuarioGeneroService usuarioGeneroService;

    @Autowired
    public UsuarioGeneroController(UsuarioGeneroService usuarioGeneroService) {
        this.usuarioGeneroService = usuarioGeneroService;
    }


    @GetMapping("/buscarPorUsuario")
    @Operation(summary = "Lista todas as relações com gênero de um usuário", description = "Retorna uma lista com a relação com gênero de um usuário que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de relações retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioGenero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as relações",content = @Content)
    })
    public ResponseEntity<?> buscarPorUsuario(@Parameter(description = "ID do usuário que deve ser retornada a lista de relação")@Valid @RequestParam Long usuario) {
        List<UsuarioGenero> usuarios = usuarioGeneroService.buscarPorUsuario(usuario);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nâo encontrado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
    }

    @PostMapping("/inserir")
    @Operation(summary = "Cria a relação entre usuário/gênero", description = "Cria a relação usuário/gênero a partir de parametros e retorna o id se for inserido com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "A relação foi criada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(example = "12345"))),
            @ApiResponse(responseCode = "400",description = "Não foi possivel criar a relação",content = @Content)
    })
    public ResponseEntity<?> seguirMuseu(@Parameter(description = "ID do usuário que deve se relacionar com o gênero")@Valid @RequestParam Long id_user,@Parameter(description = "ID do genero que deve se relacionar com o usuário") @RequestParam Long id_genero) {
        Long id = usuarioGeneroService.adicionar(id_user, id_genero);
        if (id!=0l) {
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel seguir o genero");
        }
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Exclui a relação entre usuário/gênero", description = "Exclui a relação usuário/gênero a partir de parametros e retorna o id se for inserido com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "A relação foi excluida com sucesso",content = @Content),
            @ApiResponse(responseCode = "400",description = "Não foi possivel excluir a relação",content = @Content)
    })
    public ResponseEntity<?> deixarSeguirMuseu(@Parameter(description = "ID do usuário que deve se relacionar com o gênero")@Valid @RequestParam Long id_user,@Parameter(description = "ID do genero que deve se relacionar com o usuário") @RequestParam Long id_genero) {
        Boolean apagar = usuarioGeneroService.excluir(id_user,id_genero);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.OK).body("O usuário deixou de seguir o museu");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel deixar de seguir o museu");
        }
    }

    @GetMapping("/buscarSeExiste")
    @Operation(summary = "Verifica relação", description = "Se a relação existir retorna um objeto usário/genero se não devolve um not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de relações retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioGenero.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possivel encontrar as relações",content = @Content)
    })
    public ResponseEntity<?> buscarSeEsxiste(@Parameter(description = "ID do usuário que deve ser retornada a lista de relação")@Valid @RequestParam Long usuario,@Valid @RequestParam Long genero) {

       try {
           UsuarioGenero relacao = usuarioGeneroService.buscarSeExiste(usuario,genero);
           return ResponseEntity.status(HttpStatus.OK).body(relacao);

       }catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar relação");
       }
    }
}
