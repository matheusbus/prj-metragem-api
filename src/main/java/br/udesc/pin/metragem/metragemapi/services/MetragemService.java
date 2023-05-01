package br.udesc.pin.metragem.metragemapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;

@Service
public class MetragemService {
    
    @Autowired
    private MetragemRepository metragemRepository;

    public List<Metragem> findAll(){
        return metragemRepository.findAll();
    }

    public Metragem findById(long id){
        return metragemRepository.findById(id).get();
    }

}
