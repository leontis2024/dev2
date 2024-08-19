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
    public Usuario buscarUsuarioPorId(String id) {
        return  usuarioRepository.findById(id).orElseThrow(()->
                new RuntimeException("Usuario não encontrado"));
    }
    public List<Usuario> buscarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuario salvar(Usuario usuario) {
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
    public Usuario excluirUsuario(String id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return usuario.get();
        }
        return null;
    }

}
