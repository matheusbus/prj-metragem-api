package br.udesc.pin.metragem.metragemapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @OneToMany(mappedBy = "cidade")
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

    public long getCodigoIbge() {
        return codigo;
    }

    public void setCodigoIbge(long codigo) {
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

    @Override
    public String toString() {
        return "Cidade [codigo=" + codigo + ", nome=" + nome + ", uf=" + uf + ", codIbge=" + codIbge + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (codigo ^ (codigo >>> 32));
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
        if (codigo != other.codigo)
            return false;
        return true;
    }



}
