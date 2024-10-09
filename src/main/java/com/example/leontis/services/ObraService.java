package com.example.leontis.services;

import com.example.leontis.models.Genero;
import com.example.leontis.models.Obra;
import com.example.leontis.repository.GeneroRepository;
import com.example.leontis.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService {
    private ObraRepository obraRepository;
    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    public Obra buscarPorID(Long id){
         Obra obra = obraRepository.findById(id).orElseThrow(RuntimeException::new);
         return obra;
    }

    public Obra buscarPorNome(String nome){
        Obra obra = obraRepository.findByNomeObra(nome).orElseThrow(RuntimeException::new);
        return obra;
    }

    public List<Obra> buscarPorGenero(Long id){
        return obraRepository.findByIdGenero(id);
    }

    public List<Obra> buscarPorMuseu(long id){
        return obraRepository.findByIdMuseu(id);
    }

    public List<Obra> buscarPorArtista(long id){
        return obraRepository.findByIdArtista(id);
    }

    public List<Obra> buscarTudo(){
        return obraRepository.findAllByOrderByNomeObraAsc();
    }

    public List<Obra> buscarObrasPorGeneros(List<Long> generos) {
        if (generos.isEmpty()){
            return obraRepository.findAllByOrderByNomeObraAsc();
        }else {
            return obraRepository.findAllByGeneros(generos);
        }
    }

    public List<Obra> buscarObrasPorMuseus(List<Long> museus) {
        if (museus.isEmpty()){
            return obraRepository.findAllByOrderByNomeObraAsc();
        }else {
            return obraRepository.findAllByMuseus(museus);
        }
    }
}
