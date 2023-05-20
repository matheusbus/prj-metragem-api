package br.udesc.pin.metragem.metragemapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.udesc.pin.metragem.metragemapi.models.Metragem;
import br.udesc.pin.metragem.metragemapi.services.MetragemService;

@RestController
@RequestMapping(value = "/metragem")
public class MetragemResource {
    
    @Autowired
    private MetragemService service;

    @GetMapping
    public ResponseEntity<List<Metragem>> findAll(){
        return ResponseEntity.ok().body(service.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metragem> findById(@PathVariable long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/cidade")
    public ResponseEntity<List<Metragem>> findMetragemByCidadeIBGE(@RequestParam(name = "ibge") long codIbge){
        return ResponseEntity.ok().body(service.findByCidade(codIbge));
    }

    @GetMapping("/cidade/{ibge}/last")
    public ResponseEntity<Metragem> findLastMetragemByCidadeIBGE(@PathVariable long ibge){
        return ResponseEntity.ok().body(service.findLastMetragemByCidadeIBGE(ibge));
    }

}
