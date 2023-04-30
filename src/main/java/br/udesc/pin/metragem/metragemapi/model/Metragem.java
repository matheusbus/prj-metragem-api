package br.udesc.pin.metragem.metragemapi.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbmetragem")
public class Metragem implements Serializable{
    
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metcodigo")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "metdata")
    private Date data;

    @Temporal(TemporalType.TIME)
    @Column(name = "methora")
    private Date hora;

    @Column(name = "metnivel")
    private float nivel;

    @Column(name = "metdiferenca")
    private float diferenca;

    @Column(name = "metindipluv")
    private float indicePluviometrico;

    @Column(name = "metclima")
    private Integer clima;

    @ManyToOne
    @JoinColumn(name = "cidcodigo")
    private Cidade cidade;

    public Metragem(){

    }

    public long getId(){
        return this.id;
    }

    public Date getData() {
        return data;
    }

    public Date getHora() {
        return hora;
    }

    public float getNivel() {
        return nivel;
    }

    public float getDiferenca() {
        return diferenca;
    }

    public float getIndicePluviometrico() {
        return indicePluviometrico;
    }

    public Clima getClima() {
        return Clima.valueOf(this.clima);
    }

    public void setId(long id){
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public void setNivel(float nivel) {
        this.nivel = nivel;
    }

    public void setDiferenca(float diferenca) {
        this.diferenca = diferenca;
    }

    public void setIndicePluviometrico(float indicePluviometrico) {
        this.indicePluviometrico = indicePluviometrico;
    }

    public void setClima(Clima clima) {
        if(clima != null){
            this.clima = clima.getCodigo();
        }
    }

    @Override
    public String toString() {
        return "Metragem [data=" + data + ", hora=" + hora + ", nivel=" + nivel + ", diferenca=" + diferenca
                + ", indicePluviometrico=" + indicePluviometrico + ", clima=" + clima + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Metragem other = (Metragem) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
