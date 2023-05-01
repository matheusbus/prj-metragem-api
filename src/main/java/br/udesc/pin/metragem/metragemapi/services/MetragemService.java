package br.udesc.pin.metragem.metragemapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;
import utils.GeradorMetragem;

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

    public List<Integer> findLastFiveWeatherByCidadeIBGE(long codIbge){
        Cidade cidade = cidadeService.findByCodIbge(codIbge);

        if(cidade == null){
            return null;
        } else {
            return metragemRepository.buscarClimaDasMetragensPorCidade(cidade)
            .stream()
            .limit(5)
            .collect(Collectors.toList());
        }
    }

    //@Scheduled(cron = "0/10 * * * * * *")
    public Metragem gravarNovaLeitura(Cidade cidade){
        return this.save(GeradorMetragem.gerarNovaMetragem(cidade, 
                                                       this.findLastMetragemByCidadeIBGE(cidade.getCodIbge()),
                                                       this.findLastFiveWeatherByCidadeIBGE(cidade.getCodIbge())));
    }

}
