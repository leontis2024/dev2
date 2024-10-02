package com.example.leontis.services;

import com.example.leontis.models.Usuario;
import com.example.leontis.models.UsuarioGenero;
import com.example.leontis.models.UsuarioMuseu;
import com.example.leontis.repository.UsuarioMuseuRepository;
import com.example.leontis.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioMuseuService {
    private final UsuarioMuseuRepository usuarioMuseuRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsuarioMuseuService(UsuarioMuseuRepository usuarioMuseuRepository) {
        this.usuarioMuseuRepository = usuarioMuseuRepository;
    }

//    método para buscar relação por id do usuário
    public List<UsuarioMuseu> buscarPorUsuario(Long usuario) {

         List<UsuarioMuseu> usuarios = usuarioMuseuRepository.findByIdUsuario(usuario);
        return usuarios;
    }

    @Transactional
    public Long seguir(Long id_user, Long id_museu ){
        UsuarioMuseu usuarioMuseu = usuarioMuseuRepository.findUsuarioMuseuByIdUsuarioAndIdMuseu(id_user, id_museu);
        if (usuarioMuseu != null) {
            return 0l;
        }
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
            Optional<UsuarioMuseu> consta = usuarioMuseuRepository.findById(idNumero);
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
        // Insere na tabela associativa utilizando a procedure gerenciar_seguidores_museu
        jdbcTemplate.update("CALL gerenciar_seguidores_museu(?, ?,?)",idNumero, id_user, id_museu);
        usuarioMuseu = usuarioMuseuRepository.findUsuarioMuseuByIdUsuarioAndIdMuseu(id_user, id_museu);

        if (usuarioMuseu != null) {
            return usuarioMuseu.getId(); // Retorna o ID gerado automaticamente
        } else {
//            se não foi inserido retorna um 0 do tipo long
            return 0l;
        }
    }

//    método para excluir relação usuario/museu
    @Transactional
    public Boolean deixarSeguir(Long id_user, Long id_museu){

        UsuarioMuseu usuarioMuseu = usuarioMuseuRepository.findUsuarioMuseuByIdUsuarioAndIdMuseu(id_user, id_museu);
        if (usuarioMuseu == null) {
            return false;
        }
        jdbcTemplate.update("CALL gerenciar_seguidores_museu(?,?,?)",
                0,
                id_user,
                id_museu);
//        verifica se realmente foi excluido
        usuarioMuseu = usuarioMuseuRepository.findUsuarioMuseuByIdUsuarioAndIdMuseu(id_user, id_museu);
        if (usuarioMuseu == null) {
            return true;
        }else {
            return false;
        }

    }
    public UsuarioMuseu buscarSeExiste(Long id_user, Long id_museu){
        UsuarioMuseu usuarioMuseu = usuarioMuseuRepository.findByIdMuseuAndIdUsuario(id_user,id_museu).orElseThrow(RuntimeException::new);
        return usuarioMuseu;
    }
}
