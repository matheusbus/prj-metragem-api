package br.udesc.pin.metragem.metragemapi.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;

@RestController
@RequestMapping(value = "/metragem")
public class MetragemResource {
    
    @Autowired
    private MetragemRepository metragemRepository;

    @GetMapping
    public ResponseEntity<Metragem> findAll(){
        //List<Metragem> metragens = metragemRepository.findAll(); 
        Metragem metragem = new Metragem();
        metragem.setId(1);
        metragem.setClima(Clima.BOM);
        metragem.setData(null);
        metragem.setHora(null);
        metragem.setNivel(8.14f);
        metragem.setIndicePluviometrico(0.26f);
        return ResponseEntity.ok().body(metragem);
    }

}
