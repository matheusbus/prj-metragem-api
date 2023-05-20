package br.udesc.pin.metragem.metragemapi.model;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbcidade")
public class Cidade implements Serializable{
    
    private static final long SerialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cidcodigo")
    private Long codigo;

    @Column(name = "cidnome")
    private String nome;

    @Column(name = "ciduf")
    private String uf;

    @Column(name = "cidibge")
    private long codIbge;

    
    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    private java.util.List<Metragem> metragens = new ArrayList<>();

    public Cidade(){

    }

    public Cidade(Long codigo, String nome, String uf, long codIbge) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
        this.codIbge = codIbge;
        this.metragens = new ArrayList<>();
    }

    public static long getSerialversionuid() {
        return SerialVersionUID;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(long codIbge) {
        this.codIbge = codIbge;
    }

    public java.util.List<Metragem> getMetragens() {
        return metragens;
    }

    @Override
    public String toString() {
        return "Cidade [codigo=" + codigo + ", nome=" + nome + ", uf=" + uf + ", codIbge=" + codIbge + ", metragens="
                + metragens + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
        Cidade other = (Cidade) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }




}
