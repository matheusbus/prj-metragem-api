package br.udesc.pin.metragem.metragemapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.udesc.pin.metragem.metragemapi.models.Leitura;

public interface LeituraRepository extends JpaRepository<Leitura, Long>{
 
    @Query("select L from Leitura as L order by L.leicodigo DESC")
    public List<Leitura> findAllDesc();

    @Query("select L from Leitura as L where L.leiidentificador = :identifier")
    public Leitura findByIdentifier(String identifier);

}
