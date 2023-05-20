package br.udesc.pin.metragem.metragemapi.generator;

import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

import utils.FormatUtils;

public class GeradorPluviometrico {
    
    private static Random r = new Random();;

    public static float aferirNovoIndice(List<Integer> ultimosCincoClimas){
        
        int mediaDosClimas = climaMedio(ultimosCincoClimas);  

        float novoIndice;
        switch(mediaDosClimas){
            case 1: // BOM
                // De 0.00 à 0.10
                // Probabilidades: 70% de 0.00 | 30% de entre 0.00 e 0.10
                float[] valoresPossiveisClimaBom = {0.00f, (r.nextFloat(0.00f, 100.00f) / 1000)};
                float[] probabilidadesClimaBom = {0.7f, 0.3f};
                novoIndice = calculaNovoIndice(valoresPossiveisClimaBom, probabilidadesClimaBom);
                break;
            case 2: // NUBLADO
                // De 0.00 à 1.00
                // Probabilidades: 50% de 0.00 | 50% de 0.00 à 1.00 
                float[] valoresPossiveisClimaNublado = {0.00f, r.nextFloat(0.00f, 1.00f)};
                float[] probabilidadesClimaNublado = {0.5f, 0.5f};
                novoIndice = calculaNovoIndice(valoresPossiveisClimaNublado, probabilidadesClimaNublado);
                break;
            case 3: // CHUVOSO
                // De 0.00 à 3.00
                // Probabilidades: 10% de 0.00 | 90% de 0.00 à 3.00
                float[] valoresPossiveisClimaChuvoso = {0.00f, r.nextFloat(0.00f, 3.00f)};
                float[] probabilidadesClimaChuvoso = {0.05f, 0.95f};
                novoIndice = calculaNovoIndice(valoresPossiveisClimaChuvoso, probabilidadesClimaChuvoso);
                break;
            default:
                novoIndice = 0.00f;
        }
        return FormatUtils.formataValor(novoIndice, 2, 2, RoundingMode.HALF_UP);
    }

    private static float calculaNovoIndice(float[] valoresPossiveis, float[] probabilidades){
        
        float total = 0;
        float chance = r.nextFloat();

        for(int i = 0; i < valoresPossiveis.length; i++){
            total += probabilidades[i];
            if(chance <= total){
                return valoresPossiveis[i];
            }
        }
        return 100.00f;
    }

    private static int climaMedio(List<Integer> ultimosCincoClimas){
        DoubleStream doubleStream = ultimosCincoClimas.stream().mapToDouble(Integer::doubleValue);
        
        return (int) Math.round(doubleStream.average().getAsDouble());
    }

}
