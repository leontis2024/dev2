package com.example.leontis.services;

import com.example.leontis.models.Usuario;
import com.example.leontis.models.UsuarioMuseu;
import com.example.leontis.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsuarioService(UsuarioRepository usuarioRepository) {
           this.usuarioRepository = usuarioRepository;
        }

// método para buscar usuario por id, se o usuario não for encontrado uma
//  runtimeexception será lançada para ser tratada no controler
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
        return usuario;
//        return  usuarioRepository.findById(id).orElseThrow(()->
//                new RuntimeException("Usuario não encontrado"));
    }

// método para buscar usuario por email
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmailLikeIgnoreCase(email);
    }

// método para buscar usuario por telefone
    public Usuario buscarUsuarioPorTelefone(String telefone) {
      return   usuarioRepository.findByTelefone(telefone);
    }

//    método para buscar todos os usuarios
    public List<Usuario> buscarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }

//método para salvar usuario que pode ser usado  para atualizar
    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            // Atualiza na tabela utilizando a procedure aqtualizar_usuario
            jdbcTemplate.update("CALL atualizar_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getEmail(),
                    usuario.getTelefone(),
                    Date.valueOf(usuario.getDataNascimento()), // Converter a String para Date
                    usuario.getBiografia(),
                    usuario.getSexo(),
                    usuario.getApelido(),
                    usuario.getSenha(),
                    usuario.getUrlImagem());

            return usuario;


        }catch (DataAccessException e) {
//           se pegar uma exceção que foi enraizada pela procedure lança um throw para ser tratado no controller
            throw new RuntimeException("Erro ao atualizar usuário no banco de dados: " + e.getMessage(), e);
        }
    }

    //    método para salvar usuario que pode ser usado para inserir
    @Transactional
    public String inserir(Usuario usuario) {

        //        logica para gerar o id do usuário aleatorio com 5 digitos
        Random random = new Random();
        int num1 = random.nextInt(0, 9);
        int num2 = random.nextInt(0, 9);
        int num3 = random.nextInt(0, 9);
        int num4 = random.nextInt(0, 9);
        int verificador = (num1+num2+num3+num4)%10;
        boolean continuar=true;
        String numero = ""+num1+num2+num3+num4+verificador;
        Long idNumero = Long.parseLong(numero);

        while (continuar) {
            Optional<Usuario> consta = usuarioRepository.findById(idNumero);
            if (consta.isPresent()) {
                continuar=true;
                num1 = random.nextInt(0, 9);
                num2 = random.nextInt(0, 9);
                num3 = random.nextInt(0, 9);
                num4 = random.nextInt(0, 9);
                verificador = (num1 + num2 + num3 + num4) % 10;
                numero = ""+num1+num2+num3+num4+verificador;
            }else {
                continuar=false;
            }
        }
        idNumero = Long.parseLong(numero);
        // Insere na tabela utilizando a procedure inserir_usuarios
        try {
            jdbcTemplate.update("CALL inserir_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)",
                    idNumero,
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getEmail(),
                    usuario.getTelefone(),
                    Date.valueOf(usuario.getDataNascimento()), // Converter a String para Date
                    usuario.getBiografia(),
                    usuario.getSexo(),
                    usuario.getApelido(),
                    usuario.getSenha(),
                    usuario.getUrlImagem());
            usuario.setId(idNumero);
            return usuario.getId().toString();
        }catch (DataAccessException e) {
//            se pegar uma exceção que foi enraizada pela procedure lança um throw para ser tratado no controller
            throw new RuntimeException("Erro ao inserir usuário no banco de dados: " + e.getMessage(), e);
        }


    }

//    método para excluir usuario
    @Transactional
    public Usuario excluirUsuario(Long id){

       jdbcTemplate.update("CALL deletar_usuario(?)",id);

//       verifica se realmente foi excluido
       return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
