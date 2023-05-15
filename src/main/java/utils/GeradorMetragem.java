package utils;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;

public class GeradorMetragem {
   
    public static Metragem gerarNovaMetragem(Cidade cidade, List<Metragem> metragensCidade){

        List<Metragem> ultimasCincoMetragens = metragensCidade.stream().limit(5).collect(Collectors.toList());

        Metragem ultimaMetragemCidade = ultimasCincoMetragens.stream().findFirst().get();

        Clima novoClima = GeradorClimatico.gerarNovoClima(ultimaMetragemCidade.getClima());

        float novoIndicePluviometrico = getNovoIndicePluviometrico(ultimasCincoMetragens);
        novoIndicePluviometrico = FormatUtils.formataValor(novoIndicePluviometrico, 2, 2, RoundingMode.HALF_UP);

        float novoNivel = getNovoNivel(ultimaMetragemCidade, novoClima, novoIndicePluviometrico, ultimasCincoMetragens);
        novoNivel = FormatUtils.formataValor(novoNivel, 2, 2, RoundingMode.HALF_UP);

        float diferenca;

        /*
        if(novoNivel > 0){
            diferenca = novoNivel - ultimaMetragemCidade.getNivel();
            novoNivel = ultimaMetragemCidade.getNivel() + novoNivel;
        } else if(novoNivel == 0){
            diferenca = 0;
        } else {
            diferenca = novoNivel - ultimaMetragemCidade.getNivel();
            novoNivel = ultimaMetragemCidade.getNivel() + novoNivel;
        } */



        Metragem novaMetragem = new Metragem(LocalDate.now(), LocalTime.now(), novoNivel, 0.0f, novoIndicePluviometrico, novoClima, cidade);
        
        return novaMetragem;

    }

    private static float getNovoIndicePluviometrico(List<Metragem> ultimasCincoMetragens){
        
        List<Integer> ultimosCincoClimas = new ArrayList<>();

        for (Metragem metragem : ultimasCincoMetragens) {
            ultimosCincoClimas.add(metragem.getClima().getCodigo());
        }

        return GeradorPluviometrico.aferirNovoIndice(ultimosCincoClimas);
    }

    private static float getNovoNivel(Metragem ultimaMetragem, Clima novoClima, float novoIndicePluviometrico, List<Metragem> ultimasCincoMetragens){
        
        List<Float> ultimosCincoNiveis = new ArrayList<>();

        for(Metragem metragem : ultimasCincoMetragens){
            ultimosCincoNiveis.add(metragem.getNivel());
        }

        return GeradorNivelamento.gerarNovoNivel(ultimosCincoNiveis, ultimaMetragem.getClima(), novoClima, ultimaMetragem.getIndicePluviometrico(), novoIndicePluviometrico);
    }

}
