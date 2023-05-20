package br.udesc.pin.metragem.metragemapi.generator;

import java.util.Random;

import br.udesc.pin.metragem.metragemapi.models.enums.Clima;

public class GeradorClimatico {
    
    public static Clima gerarNovoClima(Clima climaAnterior){

        if(climaAnterior.equals(Clima.BOM)){
            float[] tendencias = {0.60f, 0.30f, 0.10f}; // ÚLT. CLIMA = BOM 
            return calculaNovoClima(tendencias);

        }
        else if(climaAnterior.equals(Clima.NUBLADO)){
            float[] tendencias = {0.20f, 0.60f, 0.20f}; // ÚLT. CLIMA = NUBLADO
            return calculaNovoClima(tendencias);
        }
        else {  
            float[] tendencias = {0.15f, 0.30f, 0.55f}; // ÚLT. CLIMA = CHUVOSO
            return calculaNovoClima(tendencias);
        }

    }

    private static Clima calculaNovoClima(float[] tendencias){
        Clima[] climasPossiveis = {Clima.BOM, Clima.NUBLADO, Clima.CHUVOSO};
        Random r = new Random();
        
        float total = 0;
        float chance = r.nextFloat();
        
        for(int i = 0; i < climasPossiveis.length; i++){
            total += tendencias[i];
            if(chance <= total){
                return climasPossiveis[i];
            }    
        }
        return null;
    }

}
