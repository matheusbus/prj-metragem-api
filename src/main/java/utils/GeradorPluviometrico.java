package utils;

import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public class GeradorPluviometrico {
    
    private static Random r;


    public static float aferirNovoIndice(List<Integer> ultimosCincoClimas){
        
        r = new Random();
        int mediaDosClimas = climaMedio(ultimosCincoClimas);  

        float novoIndice;
        switch(mediaDosClimas){
            case 1: // BOM
                // De 0.00 à 0.10
                novoIndice = (r.nextFloat(0.00f, 100.00f) / 1000);
                break;
            case 2: // NUBLADO, 3.00f);
                // De 0.00 à 1.00
                novoIndice = r.nextFloat(0.00f, 1.00f);
                break;
            case 3: // CHUVOSO
                // De 0.00 à 3.00
                novoIndice = r.nextFloat(0.00f, 3.00f);
                break;
            default:
                novoIndice = 0.00f;
        }
        return novoIndice;
    }

    private static int climaMedio(List<Integer> ultimosCincoClimas){
        DoubleStream doubleStream = ultimosCincoClimas.stream().mapToDouble(Integer::doubleValue);
        
        return (int) Math.round(doubleStream.average().getAsDouble());
    }

}
