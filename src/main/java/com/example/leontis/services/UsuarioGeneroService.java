package com.example.leontis.services;

import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.models.UsuarioMuseu;
import com.example.leontis.repository.UsuarioGeneroRepository;
import com.example.leontis.repository.UsuarioMuseuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioGeneroService {
    private final UsuarioGeneroRepository usuarioGeneroRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsuarioGeneroService(UsuarioGeneroRepository usuarioGeneroRepository) {
        this.usuarioGeneroRepository = usuarioGeneroRepository;
    }

    //    método para buscar relação por id do usuário
    public List<UsuarioGenero> buscarPorUsuario(Long usuario) {

        List<UsuarioGenero> usuarios = usuarioGeneroRepository.findByIdUsuario(usuario);
        return usuarios;
    }

    //    método para adicionar relação usuario/genero
    @Transactional
    public Long adicionar(Long id_user, Long id_genero ){
        UsuarioGenero usuarioGenero = usuarioGeneroRepository.findUsuarioMuseuByIdUsuarioAndIdGenero(id_user, id_genero);
        if (usuarioGenero != null) {
            return 0l;
        }
        //        logica para gerar o id da relação aleatorio com 5 digitos
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
            Optional<UsuarioGenero> consta = usuarioGeneroRepository.findById(idNumero);
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
        // Insere na tabela associativa utilizando a procedure gerenciar_generos_usuario
        jdbcTemplate.update("CALL gerenciar_generos_usuario(?, ?,?)",idNumero, id_user, id_genero);
        usuarioGenero = usuarioGeneroRepository.findUsuarioMuseuByIdUsuarioAndIdGenero(id_user, id_genero);

        if (usuarioGenero!= null) {
            return usuarioGenero.getId(); // Retorna o ID gerado automaticamente
        } else {
//            se não foi inserido retorna um 0 do tipo long
            return 0l;
        }
    }

    //    método para excluir relação usuario/genero
    @Transactional
    public Boolean excluir(Long id_user, Long id_genero){

        UsuarioGenero usuarioGenero = usuarioGeneroRepository.findUsuarioMuseuByIdUsuarioAndIdGenero(id_user, id_genero);
        if (usuarioGenero == null) {
            return false;
        }
        jdbcTemplate.update("CALL gerenciar_generos_usuario(?,?,?)",
                0,
                id_user,
                id_genero);
//        verifica se realmente foi excluido
        usuarioGenero = usuarioGeneroRepository.findUsuarioMuseuByIdUsuarioAndIdGenero(id_user, id_genero);
        if (usuarioGenero == null) {
            return true;
        }else {
            return false;
        }

    }

    public UsuarioGenero buscarSeExiste(Long id_user, Long id_genero){
        UsuarioGenero usuarioGenero = usuarioGeneroRepository.findByIdGeneroAndIdUsuario(id_genero,id_user).orElseThrow(RuntimeException::new);
        return usuarioGenero;
    }

}
