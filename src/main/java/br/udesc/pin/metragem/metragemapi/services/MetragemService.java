package br.udesc.pin.metragem.metragemapi.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;

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

    public void novaLeitura(){
        gerarLeitura(cidadeService.findByNomeAndUf("Rio do Sul", "SC"));
    }

    public void gerarLeitura(Cidade cidade){

    }

    public void gerarMetragem(){

    }

    public void calculaProxMetragem(){

    }

    public float calculaIndicePluviometrico(Clima clima){
        Random gerador = new Random();

        if(clima.equals(Clima.BOM)){
            return 0.50f;
        } 
        else if (clima.equals(Clima.NUBLADO)){
            return gerador.nextFloat(1.00f);
        }
        else if (clima.equals(Clima.CHUVOSO)){
            return gerador.nextFloat(5.00f);
        } else {
            return 100.00f;
        }    
    }

}
