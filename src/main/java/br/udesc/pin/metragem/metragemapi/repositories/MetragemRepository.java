package br.udesc.pin.metragem.metragemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.udesc.pin.metragem.metragemapi.model.Metragem;

public interface MetragemRepository extends JpaRepository<Metragem, Long>{
    
}
