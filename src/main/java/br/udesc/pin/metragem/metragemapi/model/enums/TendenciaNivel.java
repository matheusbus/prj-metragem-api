package br.udesc.pin.metragem.metragemapi.model.enums;

public enum TendenciaNivel {
    
    DIMINUIR(1),
    MANTER(2),
    AUMENTAR(3);

    private int codigo;
    private float valor;

    private TendenciaNivel(int codigo){
        this.codigo = codigo;
    }

    public void setValor(float valor){
        this.valor = valor;
    }

    public float getValor(){
        return this.valor;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public static TendenciaNivel valueOf(int codigo){
        for(TendenciaNivel t : TendenciaNivel.values()){
            if(t.getCodigo() == codigo){
                return t;
            }
        }
        throw new IllegalArgumentException("Código da tendência inválido.");
    }

}
