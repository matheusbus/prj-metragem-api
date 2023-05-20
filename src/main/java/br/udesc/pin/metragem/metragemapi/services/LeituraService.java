package br.udesc.pin.metragem.metragemapi.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Leitura;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
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

    public Leitura gravarNovaLeitura(){

        Leitura leitura = new Leitura(LocalDate.now(), LocalTime.now());

        List<Cidade> cidades = cidadeService.findAll();

        for (Cidade cidade : cidades) {
            metragemService.gravarNovaMetragem(cidade, leitura);
        }

        return this.save(leitura);

    }

}
