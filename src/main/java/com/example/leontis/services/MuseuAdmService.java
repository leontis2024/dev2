package com.example.leontis.services;

import com.example.leontis.models.MuseuAdm;
import com.example.leontis.models.Usuario;
import com.example.leontis.repository.MuseuAdmRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class MuseuAdmService {

    private MuseuAdmRepository museuAdmRepository;
    public MuseuAdmService(MuseuAdmRepository museuAdmRepository) {
        this.museuAdmRepository = museuAdmRepository;
    }

    public String criarMuseuAdm(String email, String senha) {
     try{
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
             Optional<MuseuAdm> consta = museuAdmRepository.findById(idNumero);
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
         MuseuAdm museuAdm = new MuseuAdm(idNumero,email,senha);
         museuAdmRepository.save(museuAdm);
         return museuAdm.getId().toString();
    }catch (
    DataAccessException e) {
//            se pegar uma exceção que foi enraizada pela procedure lança um throw para ser tratado no controller
        throw new RuntimeException("Erro ao inserir usuário no banco de dados: " + e.getMessage(), e);
    }


    }
}
