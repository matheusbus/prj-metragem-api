package br.udesc.pin.metragem.metragemapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metcodigo")
    private long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "metdata")
    private LocalDate data;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "methora")
    private LocalTime hora;

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
    @JsonIgnoreProperties("metragens")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "leicodigo")
    @JsonIgnoreProperties("metragens")
    private Leitura leitura;

    public Metragem(){

    }

    public Metragem(LocalDate data, LocalTime hora, float nivel, float diferenca, float indicePluviometrico,
            Clima clima, Cidade cidade, Leitura leitura) {
        this.data = data;
        this.hora = hora;
        this.nivel = nivel;
        this.indicePluviometrico = indicePluviometrico;
        this.clima = clima.getCodigo();
        this.cidade = cidade;
        this.diferenca = diferenca;
        this.leitura = leitura;
    }

    public long getId(){
        return this.id;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
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

    public Cidade getCidade(){
        return this.cidade;
    }

    public void setCidade(Cidade cidade){
        this.cidade = cidade;
    }

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }

    @Override
    public String toString() {
        return "Metragem [codigo= "+ id + ", cidade=" + cidade.getNome() + ", data=" + data + ", hora=" + hora + ", nivel=" + nivel + ", diferenca=" + diferenca
                + ", indicePluviometrico=" + indicePluviometrico + ", clima=" + Clima.valueOf(clima) + "]";
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
