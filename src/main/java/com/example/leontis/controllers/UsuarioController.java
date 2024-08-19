package com.example.leontis.controllers;

import com.example.leontis.models.Usuario;
import com.example.leontis.services.UsuarioService;
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

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/selecionarTodosUsuarios")
    public List<Usuario> buscarUsuarios() {
        return usuarioService.buscarTodosOsUsuarios();
    }
    @GetMapping("/selecionarUsuarioPorID/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable String id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuário não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
    @GetMapping("/selecionarPorEmail")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam  String email) {
        Usuario usuario=usuarioService.buscarUsuarioPorEmail(email);
        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o usuário pelo email que foi informado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
    }
    @GetMapping("/selecionarPorTelefone")
    public ResponseEntity<?> buscarUsuarioPorTelefone(@RequestParam  String telefone) {
        Usuario usuario=usuarioService.buscarUsuarioPorEmail(telefone);
        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o usuário pelo email que foi informado");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
    }
    @PostMapping("/inserir")
    public ResponseEntity<?> inserirUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario1.getId());
    }
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirConta(@PathVariable String id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario!=null){
            usuarioService.excluirUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario excluido com sucesso");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuário não existe");
        }
    }
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable String id, @Valid @RequestBody Map<String, Object> updates) {
        try {
            Usuario usuario1 = usuarioService.buscarUsuarioPorId(id);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            if (updates.containsKey("nm_usuario")) {
                usuario1.setNm_usuario(updates.get("nm_usuario").toString());
            }
            if (updates.containsKey("sobrenome")){
                usuario1.setSobrenome(updates.get("sobrenome").toString());
            }
            if (updates.containsKey("email_usuario")) {
                usuario1.setEmail_usuario(updates.get("email_usuario").toString());
            }
            if (updates.containsKey("nr_tel_usuario")) {
                usuario1.setNr_tel_usuario(updates.get("nr_tel_usuario").toString());
            }
            if (updates.containsKey("dt_nasci_usuario")) {
                usuario1.setDt_nasci_usuario(updates.get("dt_nasci_usuario").toString() );
            }
            if (updates.containsKey("biografia")){
                usuario1.setBiografia(updates.get("biografia").toString());
            }
            if (updates.containsKey("sexo")) {
                usuario1.setSexo(updates.get("sexo").toString());
            }
            if (updates.containsKey("apelido")) {
                usuario1.setApelido(updates.get("apelido").toString());
            }
            if (updates.containsKey("senha_usuario")) {
                usuario1.setSenha_usuario(updates.get("senha_usuario").toString());
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
            return ResponseEntity.status(HttpStatus.OK).body("Usuário alterado com sucesso!");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handler(MethodArgumentNotValidException ex) {
        StringBuilder mensagemDeErro = new StringBuilder("Erros de validação nos campos: ");
        for (FieldError error : ex.getFieldErrors()) {
            mensagemDeErro.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(", ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemDeErro.toString());
    }


}
