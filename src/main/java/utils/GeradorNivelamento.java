package utils;

import java.math.RoundingMode;
import java.util.List;

import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.model.enums.TendenciaNivel;

public class GeradorNivelamento {
    
    // Implementar variância de acordo com os últimas 5 aferições de nível do rio
    public static float gerarNovoNivel(List<Float> ultimosCincoNiveis, Clima ultimoClima, Clima climaAtual, float ultimoNivel, float ultimoIndicePluviometrico, float indicePluviometricoAtual){
        
        // ((desvio padrão * tendencia.valor) * diferencaIndicePluviometrico)

        float desvioPadrao = calculoDesvioPadrao(ultimosCincoNiveis);
        TendenciaNivel tendenciaByClima = analisaTendenciaClimas(ultimoClima, climaAtual);
        TendenciaNivel tendenciaByIndicePluviometrico = analisaTendenciaIndicesPluviometricos(ultimoIndicePluviometrico, indicePluviometricoAtual);

        float novaDiferencaByTendencia = tendenciaByClima.getValor() * tendenciaByIndicePluviometrico.getValor();

        float novoNivel = ultimoNivel + novaDiferencaByTendencia;
        
        return FormatUtils.formataValor(novoNivel, 2, 2, RoundingMode.HALF_UP);
        
    }

    // Raíz de ((e1 - M)² + (e2 - M)² ... + (en - M)²) / n
    private static float calculoDesvioPadrao(List<Float> ultimosCincoNiveis){
        
        long n = ultimosCincoNiveis.stream().count();

        float media = (float) ultimosCincoNiveis.stream().mapToDouble(Float::doubleValue).average().getAsDouble();
        float somaTermos = 0;
        
        for(float nivel : ultimosCincoNiveis){
            somaTermos += Math.pow(nivel - media, 2); 
        }

        float variancia = somaTermos / n;
        float desvioPadrao = (float) Math.sqrt(variancia);

        return FormatUtils.formataValor(desvioPadrao, 2, 2, RoundingMode.HALF_UP);
    }

    private static float calculaNovoNivel(TendenciaNivel tendenciaByClima, TendenciaNivel tendenciaByIndicePluviometrico, float desvioPadrao){

        boolean tendenciaIgual = (tendenciaByClima.getCodigo() == tendenciaByIndicePluviometrico.getCodigo());

        /*if(tendenciaIgual && tendenciaByClima.equals(TendenciaNivel.AUMENTAR)){
            return (tendenciaByClima.getValor() * tendenciaByIndicePluviometrico.getValor() * desvioPadrao);
        } else if (tendenciaIgual && tendenciaByClima.equals(TendenciaNivel.MANTER)){
            return (tendenciaByClima.getValor() * tendenciaByIndicePluviometrico.getValor());
        } else if (tendenciaIgual && tendenciaByClima.equals(TendenciaNivel.DIMINUIR)){

        } else if ((tendenciaByClima.equals(TendenciaNivel.AUMENTAR) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.MANTER)) ||
                    (tendenciaByClima.equals(TendenciaNivel.MANTER) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.AUMENTAR))){

        } else if ((tendenciaByClima.equals(TendenciaNivel.AUMENTAR) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.DIMINUIR)) ||
                    (tendenciaByClima.equals(TendenciaNivel.DIMINUIR) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.MANTER))){

        } else if ((tendenciaByClima.equals(TendenciaNivel.MANTER) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.DIMINUIR)) ||
                    (tendenciaByClima.equals(TendenciaNivel.DIMINUIR) && tendenciaByIndicePluviometrico.equals(TendenciaNivel.MANTER))){
        }*/

        return 0.00f;
    }


    private static TendenciaNivel analisaTendenciaClimas(Clima ultimoClima, Clima climaAtual){
        
        boolean climaPermaneceuIgual = (ultimoClima.getCodigo() == climaAtual.getCodigo());

        TendenciaNivel tendenciaNivel = null;

        // Bom e continuou bom
        if(climaAtual.equals(Clima.BOM) && climaPermaneceuIgual){
            tendenciaNivel = TendenciaNivel.DIMINUIR;
            tendenciaNivel.setValor(-1.00f);
            return tendenciaNivel;
        }
        // Nublado e continuou nublado
        else if(climaAtual.equals(Clima.NUBLADO) && climaPermaneceuIgual){
            tendenciaNivel = TendenciaNivel.MANTER;
            tendenciaNivel.setValor(0.01f);
            return tendenciaNivel;
        }
        // Chuvoso e continuou chuvoso
        else if(climaAtual.equals(Clima.CHUVOSO) && climaPermaneceuIgual){
            tendenciaNivel = TendenciaNivel.AUMENTAR;
            tendenciaNivel.setValor(1.00f);
            return tendenciaNivel;
        }
        // Bom e mudou para nublado
        else if(ultimoClima.equals(Clima.BOM) && climaAtual.equals(Clima.NUBLADO)){
            tendenciaNivel = TendenciaNivel.MANTER;
            tendenciaNivel.setValor(0.01f);
            return tendenciaNivel;
        }
        // Bom e mudou para chuvoso
        else if(ultimoClima.equals(Clima.BOM) && climaAtual.equals(Clima.CHUVOSO)){
            tendenciaNivel = TendenciaNivel.AUMENTAR;
            tendenciaNivel.setValor(0.25f);
            return tendenciaNivel;
        }
        // Nublado e mudou para bom
        else if(ultimoClima.equals(Clima.NUBLADO) && climaAtual.equals(Clima.BOM)){
            tendenciaNivel = TendenciaNivel.DIMINUIR;
            tendenciaNivel.setValor(-0.25f);
            return tendenciaNivel;
        }
        // Nublado e mudou para chuvoso
        else if(ultimoClima.equals(Clima.NUBLADO) && climaAtual.equals(Clima.CHUVOSO)){
            tendenciaNivel = TendenciaNivel.AUMENTAR;
            tendenciaNivel.setValor(0.50f);
            return tendenciaNivel;
        }
        // Chuvoso e mudou para nublado
        else if(ultimoClima.equals(Clima.NUBLADO) && climaAtual.equals(Clima.CHUVOSO)){
            tendenciaNivel = TendenciaNivel.MANTER;
            tendenciaNivel.setValor(0.01f);
            return tendenciaNivel;
        }
        // Chuvoso e mudou para bom
        else {
            tendenciaNivel = TendenciaNivel.DIMINUIR;
            tendenciaNivel.setValor(-0.50f);
            return tendenciaNivel;
        }
    }

    private static TendenciaNivel analisaTendenciaIndicesPluviometricos(float ultimoIndicePluviometrico, float indicePluviometricoAtual){

        float diferenca = indicePluviometricoAtual - ultimoIndicePluviometrico;
        TendenciaNivel tendenciaNivel;

        // Indice diminuiu
        if(indicePluviometricoAtual < ultimoIndicePluviometrico){
            tendenciaNivel = TendenciaNivel.DIMINUIR;
            tendenciaNivel.setValor(diferenca);
        }
        // Indice aumentou
        else if(indicePluviometricoAtual > ultimoIndicePluviometrico){
            tendenciaNivel = TendenciaNivel.AUMENTAR;
            tendenciaNivel.setValor(diferenca);
        }
        // Indice manteve
        else {
            tendenciaNivel = TendenciaNivel.MANTER;
            tendenciaNivel.setValor(diferenca);
        }

        return tendenciaNivel;

    }

}
