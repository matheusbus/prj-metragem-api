package br.udesc.pin.metragem.metragemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.udesc.pin.metragem.metragemapi.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    
    Cidade findByCodIbgeEquals(long codIbge);
    
    Cidade findByNomeAndUfAllIgnoringCase(String nome, String uf);

}
