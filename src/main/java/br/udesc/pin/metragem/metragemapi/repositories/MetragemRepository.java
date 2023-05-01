package br.udesc.pin.metragem.metragemapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;

public interface MetragemRepository extends JpaRepository<Metragem, Long>{
    
    @Query(value = "select M from Metragem as M where M.cidade = :cidade order by M.id DESC") // JPQL
    public List<Metragem> buscarMetragensPorCidade(Cidade cidade);

}
