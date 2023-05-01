package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;

public class GeradorMetragem {
   
    public static Metragem gerarNovaMetragem(Cidade cidade, Metragem ultimaMetragemRegCidade, List<Integer> ultimosCincoClimas){

        Metragem ultimaMetragemCidade = ultimaMetragemRegCidade;
        Clima novoClima = GeradorClimatico.gerarNovoClima(ultimaMetragemCidade.getClima());
        float novoIndicePluviometrico = GeradorPluviometrico.aferirNovoIndice(ultimosCincoClimas);

        Metragem novaMetragem = new Metragem(LocalDate.now(), LocalTime.now(), 0, novoIndicePluviometrico, novoClima, cidade);

        return novaMetragem;

    }

}
