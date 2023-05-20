package br.udesc.pin.metragem.metragemapi.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.models.Cidade;
import br.udesc.pin.metragem.metragemapi.models.Leitura;
import br.udesc.pin.metragem.metragemapi.repositories.LeituraRepository;

@Service
public class LeituraService {
    
    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private MetragemService metragemService;

    @Autowired
    private CidadeService cidadeService;

    public List<Leitura> findAll(){
        return leituraRepository.findAll();
    }

    public Leitura findById(long id){
        return leituraRepository.findById(id).get();
    }

    public Leitura save(Leitura metragem){
        return leituraRepository.save(metragem);
    }

    public List<Leitura> saveAll(Iterable<Leitura> leituras){
        return leituraRepository.saveAll(leituras);
    }

    public Leitura findLastLeitura(){

        return leituraRepository.findAllDesc()
                .stream()
                .findFirst()
                .get();

    }

    public Leitura findByIdentifier(String identifier){
        return leituraRepository.findByIdentifier(identifier);
    }

    @Scheduled(cron = "0/10 * * * * *")
    public Leitura gravarNovaLeitura(){

        Leitura leitura = new Leitura(LocalDate.now(), LocalTime.now());
        this.save(leitura).setLeiidentificador();

        List<Cidade> cidades = cidadeService.findAll();

        for (Cidade cidade : cidades) {
            metragemService.gravarNovaMetragem(cidade, leitura);
        }

        return this.save(leitura);

    }

}
