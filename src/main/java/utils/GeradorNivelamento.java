package utils;

import java.math.RoundingMode;
import java.util.List;

import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.model.enums.TendenciaNivel;

public class GeradorNivelamento {
    
    public static float gerarNovoNivel(List<Float> ultimosCincoNiveis, Clima ultimoClima, Clima climaAtual, float ultimoNivel, float ultimoIndicePluviometrico, float indicePluviometricoAtual){
        
        TendenciaNivel tendenciaByClima = analisaTendenciaClimas(ultimoClima, climaAtual);
        TendenciaNivel tendenciaByIndicePluviometrico = analisaTendenciaIndicesPluviometricos(ultimoIndicePluviometrico, indicePluviometricoAtual);

        System.out.println("Tendencia Clima: " + tendenciaByClima.getValor() + " - Tendencia Ind.Pluvio.: " + tendenciaByIndicePluviometrico.getValor());
        float novaDiferencaByTendencia = tendenciaByClima.getValor() * tendenciaByIndicePluviometrico.getValor();

        float novoNivel = ultimoNivel + novaDiferencaByTendencia;
        
        return FormatUtils.formataValor(novoNivel, 2, 2, RoundingMode.HALF_UP);
        
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
            tendenciaNivel.setValor(0.50f);
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
            tendenciaNivel.setValor(-1.00f);
            return tendenciaNivel;
        }
        // Nublado e mudou para chuvoso
        else if(ultimoClima.equals(Clima.NUBLADO) && climaAtual.equals(Clima.CHUVOSO)){
            tendenciaNivel = TendenciaNivel.AUMENTAR;
            tendenciaNivel.setValor(0.25f);
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
            tendenciaNivel.setValor(-1.00f);
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
