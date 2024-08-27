package com.example.leontis.controllers;

import com.example.leontis.models.Usuario;
import com.example.leontis.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Tag(name="Usuario")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/selecionarTodosUsuarios")
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de usuários retornada com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public List<Usuario> buscarUsuarios() {
        return usuarioService.buscarTodosOsUsuarios();
    }

    @GetMapping("/selecionarUsuarioPorID/{id}")
    @Operation(summary = "Retorna o usuário pelo ID", description = "Retorna um usuário de acordo com ID que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o usuário",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarUsuarioPorId(@Parameter(description = "ID do usuário a ser retornado")@PathVariable Long id){
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuário não existe");
            }
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuário não existe");
        }
    }

    @GetMapping("/selecionarPorEmail")
    @Operation(summary = "Retorna o usuário pelo email", description = "Retorna um usuário de acordo com email que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o usuário",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarUsuarioPorEmail(@Parameter(description = "Email do usuário a ser retornado")@RequestParam  String email) {
        Usuario usuario=usuarioService.buscarUsuarioPorEmail(email);
        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o usuário pelo email que foi informado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
    }

    @GetMapping("/selecionarPorTelefone")
    @Operation(summary = "Retorna o usuário pelo telefone", description = "Retorna um usuário de acordo com o telefone que foi passado como parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário retornado com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404",description = "Não foi possível encontrar o usuário",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<?> buscarUsuarioPorTelefone(@Parameter(description = "Telefone do usuário a ser retornado")@RequestParam  String telefone) {
        Usuario usuario=usuarioService.buscarUsuarioPorTelefone(telefone);
        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o usuário pelo telefone que foi informado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
    }

    @PostMapping("/inserir")
    @Operation(summary = "Inserir usuário",description = "Insere um usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário inserido com sucesso",content = @Content(mediaType = "application/json",schema = @Schema(example = "12345"))),
            @ApiResponse(responseCode = "404",description = "Campos com entrada inesperada: Erro no campo preço:preço deve ser númerico",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro no servidor",content = @Content)
    })
    public ResponseEntity<?> inserirUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            String usuarioId = usuarioService.inserir(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioId);
        } catch (RuntimeException e) {
            // Tratamento da exceção RuntimeException lançada pelo serviço
            String log = e.getMessage();
            Pattern pattern = Pattern.compile("ERROR:.*");
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                String mesage =matcher.group();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir usuário: " + mesage);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir usuário");
        }

    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir usuário por ID",description = "Remove um usuário do sistema pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluído com sucesso",content = @Content),
            @ApiResponse(responseCode = "404",description = "Usuário não encontrado",content = @Content)
    })
    public ResponseEntity<String> excluirConta(@Parameter(description = "ID do usuário a ser excluído")@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuário não existe");
        }
        try{
                usuarioService.excluirUsuario(id);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario excluido com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.OK).body("Usuario excluido com sucesso");
        }



    }

    @PatchMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza um usuário parciamente por ID",description = "Atualiza apenas os campos que o usuário quer de um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario alterado com sucesso",content = @Content),
            @ApiResponse(responseCode = "400",description = "Campo com valor inesperado",content = @Content),
            @ApiResponse(responseCode = "404",description = "O usuário não foi encontrado",content = @Content),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",content = @Content)
    })
    public ResponseEntity<String> atualizarUsuario(@Parameter(description = "ID do usuário a ser atualizado")@PathVariable Long id,@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Mapeamento de campos a serem atualizados com os novos valores",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = "object",example="{\"nome\":\"Samira\",\"sobrenome\":\"Souza\",\"email\":\"samira.souza@gmail.com\",\"telefone\":\"(11)96313-2047\",\"dataNascimento\":\"2007-08-04\",\"biografia\":\"Oi, eu sou a Samira e estou usando o Leontis\",\"sexo\":\"Feminino\",\"apelido\":\"Sam\",\"senha\":\"12345Sam\",\"urlImagem\":\"https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/%s?alt=media\"}"))
    ) @Valid @RequestBody Map<String, Object> updates) {
        Usuario usuario1;
        try {
            usuario1 = usuarioService.buscarUsuarioPorId(id);
        }catch (RuntimeException re){
            re.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        try {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            if (updates.containsKey("nome")) {
                usuario1.setNome(updates.get("nome").toString());
            }
            if (updates.containsKey("sobrenome")) {
                usuario1.setSobrenome(updates.get("sobrenome").toString());
            }
            if (updates.containsKey("email")) {
                usuario1.setEmail(updates.get("email").toString());
            }
            if (updates.containsKey("telefone")) {
                usuario1.setTelefone(updates.get("telefone").toString());
            }
            if (updates.containsKey("dataNascimento")) {
                usuario1.setDataNascimento(updates.get("dataNascimento").toString());
            }
            if (updates.containsKey("biografia")) {
                usuario1.setBiografia(updates.get("biografia").toString());
            }
            if (updates.containsKey("sexo")) {
                usuario1.setSexo(updates.get("sexo").toString());
            }
            if (updates.containsKey("apelido")) {
                usuario1.setApelido(updates.get("apelido").toString());
            }
            if (updates.containsKey("senha")) {
                usuario1.setSenha(updates.get("senha").toString());
            }
            Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario1);
            if (!violations.isEmpty()) {
                // Se houver violações de validação, lance uma exceção ou trate conforme necessário
                StringBuilder errorMessage = new StringBuilder("Erros de validação:");
                for (ConstraintViolation<Usuario> violation : violations) {
                    errorMessage.append(" /  ").append(violation.getMessage());
//            throw new RuntimeException("Erro de validação no campo '" + campo + "': " + violations.iterator().next().getMessage());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
            }
            usuarioService.salvar(usuario1);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado com sucesso");

        }catch (RuntimeException re){
            // Tratamento da exceção RuntimeException lançada pelo serviço
            String log = re.getMessage();
            Pattern pattern = Pattern.compile("ERROR:.*");
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                String mesage =matcher.group();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário: " + mesage);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário");
        }

    }

//    método generico para erros do @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handler(MethodArgumentNotValidException ex) {
        StringBuilder mensagemDeErro = new StringBuilder("Erros de validação nos campos: ");
        for (FieldError error : ex.getFieldErrors()) {
            mensagemDeErro.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemDeErro.toString());
    }


}
