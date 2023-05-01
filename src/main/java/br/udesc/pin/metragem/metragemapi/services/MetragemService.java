package br.udesc.pin.metragem.metragemapi.services;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;
import utils.GeradorClimatico;
import utils.GeradorMetragem;
import utils.GeradorPluviometrico;

@Service
public class MetragemService {
    
    @Autowired
    private MetragemRepository metragemRepository;

    @Autowired
    private CidadeService cidadeService;

    public List<Metragem> findAll(){
        return metragemRepository.findAll();
    }

    public Metragem findById(long id){
        return metragemRepository.findById(id).get();
    }

    public Metragem save(Metragem metragem){
        return metragemRepository.save(metragem);
    }

    public Metragem findLastMetragemByCidadeIBGE(long codIbge){
        Cidade cidade = cidadeService.findByCodIbge(codIbge);

        if(cidade == null){
            return null;
        } else {
            return metragemRepository.buscarMetragensPorCidade(cidade)
            .stream()
            .findFirst()
            .get();
        }
         
    }

    public Metragem gravarNovaLeitura(Cidade cidade){
        Metragem novaMetragem = GeradorMetragem.gerarMetragem(cidade, this.findLastMetragemByCidadeIBGE(4214805), null);
        return this.save(novaMetragem);
    }

}
