package com.example.leontis.services;

import com.example.leontis.models.Usuario;
import com.example.leontis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
           this.usuarioRepository = usuarioRepository;
        }

// método para buscar usuario por id, se o usuario não for encontrado uma
//  runtimeexception será lançada para ser tratada no controler
    public Usuario buscarUsuarioPorId(String id) {
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

//    método para salvar usuario que pode ser usado  para atualizar
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //    método para salvar usuario que pode ser usado para inserir
    public Usuario inserir(Usuario usuario) {

//        logica para gerar o id do usuário aleatorio com 5 digitos
        Random random = new Random();
        int num1 = random.nextInt(0, 9);
        int num2 = random.nextInt(0, 9);
        int num3 = random.nextInt(0, 9);
        int num4 = random.nextInt(0, 9);
        int verificador = (num1+num2+num3+num4)%10;
        boolean continuar=true;
        String numeroConta = ""+num1+num2+num3+num4+verificador;

        while (continuar) {
            Optional<Usuario> consta = usuarioRepository.findById(numeroConta);
            if (consta.isPresent()) {
                continuar=true;
                num1 = random.nextInt(0, 9);
                num2 = random.nextInt(0, 9);
                num3 = random.nextInt(0, 9);
                num4 = random.nextInt(0, 9);
                verificador = (num1 + num2 + num3 + num4) % 10;
                numeroConta = ""+num1+num2+num3+num4+verificador;
            }else {
                continuar=false;
            }
        }
        usuario.setId(numeroConta);
        return usuarioRepository.save(usuario);
    }

//    método para excluir usuario
    public Usuario excluirUsuario(String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return usuario.get();
        }
        return null;
    }

}
