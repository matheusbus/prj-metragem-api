package br.udesc.pin.metragem.metragemapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.generator.GeradorMetragem;
import br.udesc.pin.metragem.metragemapi.models.Cidade;
import br.udesc.pin.metragem.metragemapi.models.Leitura;
import br.udesc.pin.metragem.metragemapi.models.Metragem;
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

    public Metragem save(Metragem metragem){
        return metragemRepository.save(metragem);
    }

    public List<Metragem> saveAll(Iterable<Metragem> metragens){
        return metragemRepository.saveAll(metragens);
    }

    public List<Metragem> findByCidade(long codIbge){
        Cidade cidade = cidadeService.findByCodIbge(codIbge);

        if(cidade == null){
            return null;
        } else {
            return metragemRepository.buscarMetragensPorCidade(cidade);
        }
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

    public List<Metragem> findLastFiveMetragemByCidade(long codIbge){
        Cidade cidade = cidadeService.findByCodIbge(codIbge);

        if(cidade == null){
            return null;
        } else {
            return metragemRepository.buscarMetragensPorCidade(cidade)
                    .stream()
                    .limit(5)
                    .collect(Collectors.toList());
        }
    }

    public Metragem gravarNovaMetragem(Cidade cidade, Leitura leitura){
        return this.save(GeradorMetragem.gerarNovaMetragem(cidade, this.findLastFiveMetragemByCidade(cidade.getCodIbge()), leitura));  
    }

}
