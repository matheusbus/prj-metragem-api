package br.udesc.pin.metragem.metragemapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.repositories.CidadeRepository;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    public Cidade findById(long id){
        return cidadeRepository.findById(id).get();
    }

    public Cidade findByCodIbge(long codIbge){
        return cidadeRepository.findByCodIbgeEquals(codIbge);
    }

    public Cidade findByNomeAndUf(String nome, String uf){
        return cidadeRepository.findByNomeAndUfAllIgnoringCase(nome, uf);
    }

    public Cidade save(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> saveAll(Iterable<Cidade> cidades){
        return cidadeRepository.saveAll(cidades);
    }    

}
