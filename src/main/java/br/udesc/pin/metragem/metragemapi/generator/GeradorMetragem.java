package br.udesc.pin.metragem.metragemapi.generator;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.udesc.pin.metragem.metragemapi.models.Cidade;
import br.udesc.pin.metragem.metragemapi.models.Leitura;
import br.udesc.pin.metragem.metragemapi.models.Metragem;
import br.udesc.pin.metragem.metragemapi.models.enums.Clima;
import utils.FormatUtils;

public class GeradorMetragem {
   
    public static Metragem gerarNovaMetragem(Cidade cidade, List<Metragem> metragensCidade, Leitura leitura){

        List<Metragem> ultimasCincoMetragens = metragensCidade.stream().limit(5).collect(Collectors.toList());

        Metragem ultimaMetragemCidade = ultimasCincoMetragens.stream().findFirst().get();

        Clima novoClima = GeradorClimatico.gerarNovoClima(ultimaMetragemCidade.getClima());

        float novoIndicePluviometrico = getNovoIndicePluviometrico(ultimasCincoMetragens);
        novoIndicePluviometrico = FormatUtils.formataValor(novoIndicePluviometrico, 2, 2, RoundingMode.HALF_UP);

        float novoNivel = getNovoNivel(ultimaMetragemCidade, novoClima, novoIndicePluviometrico, ultimasCincoMetragens);

        float diferenca = FormatUtils.formataValor(getDiferenca(ultimaMetragemCidade.getNivel(), novoNivel), 2, 2, RoundingMode.HALF_UP);


        Metragem novaMetragem = new Metragem(LocalDate.now(), LocalTime.now(), novoNivel, diferenca, novoIndicePluviometrico, novoClima, cidade, leitura);
        
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

        return GeradorNivelamento.gerarNovoNivel(ultimosCincoNiveis, ultimaMetragem.getClima(), novoClima, ultimaMetragem.getNivel(), ultimaMetragem.getIndicePluviometrico(), novoIndicePluviometrico);
    }

    private static float getDiferenca(float ultimoNivel, float novoNivel){
        
        if(novoNivel != ultimoNivel){
            return (novoNivel - ultimoNivel);
        } else {
            return 0.00f;
        }
    }

}
