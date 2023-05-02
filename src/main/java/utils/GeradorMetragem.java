package utils;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;

public class GeradorMetragem {
   
    public static Metragem gerarNovaMetragem(Cidade cidade, 
                                              Metragem ultimaMetragemRegCidade, 
                                              List<Integer> ultimosCincoClimas, 
                                              List<Float> ultimosCincoNiveis){

        Metragem ultimaMetragemCidade = ultimaMetragemRegCidade;
        Clima novoClima = GeradorClimatico.gerarNovoClima(ultimaMetragemCidade.getClima());
        float novoIndicePluviometrico = GeradorPluviometrico.aferirNovoIndice(ultimosCincoClimas);
        float novoNivel = GeradorNivelamento.gerarNovoNivel(ultimosCincoNiveis, 
                                                            ultimaMetragemCidade.getClima(), 
                                                            novoClima, 
                                                            ultimaMetragemCidade.getIndicePluviometrico(), 
                                                            novoIndicePluviometrico);

        float diferenca;                                                            
        if(novoNivel > 0){
            diferenca = novoNivel - ultimaMetragemCidade.getNivel();
            novoNivel = ultimaMetragemCidade.getNivel() + novoNivel;
        } else if(novoNivel == 0){
            diferenca = 0;
        } else {
            diferenca = novoNivel - ultimaMetragemCidade.getNivel();
            novoNivel = ultimaMetragemCidade.getNivel() + novoNivel;
        }

        novoNivel = FormatUtils.formataValor(novoNivel, 2, 2, RoundingMode.HALF_UP);

        Metragem novaMetragem = new Metragem(LocalDate.now(), LocalTime.now(), novoNivel, diferenca, novoIndicePluviometrico, novoClima, cidade);

        return novaMetragem;

    }

}
