package br.udesc.pin.metragem.metragemapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.services.CidadeService;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeResource {
    
    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Cidade> findByCodIbge(@RequestParam("ibge") long codIbge){
        return ResponseEntity.ok().body(service.findByCodIbge(codIbge));
    }

    @GetMapping("/filter")
    public ResponseEntity<Cidade> findByNomeAndUf(@RequestParam("nome") String nome, @RequestParam("uf") String uf){
        return ResponseEntity.ok().body(service.findByNomeAndUf(nome, uf));
    }
}
