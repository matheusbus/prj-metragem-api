package br.udesc.pin.metragem.metragemapi.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import utils.MD5Encoder;

@Entity
@Table(name = "tbleitura")
public class Leitura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leicodigo")
    private long leicodigo;

    @Column(name = "leidata")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate leidata;

    @Column(name = "leihora")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime leihora;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leiidentificador", length = 32, unique = true)
    private String leiidentificador;

    @OneToMany(mappedBy = "leitura")
    private List<Metragem> metragens;

    public Leitura(){

    }

    public Leitura(LocalDate leidata, LocalTime leihora){
        this.leidata = leidata;
        this.leihora = leihora;
    }

    public long getLeicodigo() {
        return leicodigo;
    }

    public void setLeicodigo(long leicodigo) {
        this.leicodigo = leicodigo;
    }

    public LocalDate getLeidata() {
        return leidata;
    }

    public void setLeidata(LocalDate leidata) {
        this.leidata = leidata;
    }

    public LocalTime getLeihora() {
        return leihora;
    }

    public void setLeihora(LocalTime leihora) {
        this.leihora = leihora;
    }

    public String getLeiidentificador() {
        return leiidentificador;
    }

    public void setLeiidentificador() {
        this.leiidentificador = MD5Encoder.getMD5Hash(String.valueOf(this.leicodigo));
    }

    public List<Metragem> getMetragens(){
        return this.metragens;
    }

    @Override
    public String toString() {
        return "Leitura [leicodigo=" + leicodigo + ", leidata=" + leidata + ", leihora=" + leihora
                + ", leiidentificador=" + leiidentificador + "]";
    }



}
