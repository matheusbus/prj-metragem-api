package br.udesc.pin.metragem.metragemapi.models.enums;

public enum Clima {
    
    BOM(1),
    NUBLADO(2),
    CHUVOSO(3);

    private int codigo;

    private Clima(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public static Clima valueOf(int codigo){
        for(Clima cli : Clima.values()){
            if(cli.getCodigo() == codigo){
                return cli;
            }
        }
        throw new IllegalArgumentException("Código do Clima inválido.");
    }

}
