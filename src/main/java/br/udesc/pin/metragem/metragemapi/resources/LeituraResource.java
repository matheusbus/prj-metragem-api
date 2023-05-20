package br.udesc.pin.metragem.metragemapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.udesc.pin.metragem.metragemapi.models.Leitura;
import br.udesc.pin.metragem.metragemapi.services.LeituraService;

@RestController
@RequestMapping("/leitura")
public class LeituraResource {
    
    @Autowired
    private LeituraService leituraService;

    @GetMapping
    public ResponseEntity<List<Leitura>> findAll(){
        return ResponseEntity.ok().body(leituraService.findAll());
    }

    @GetMapping("/last")
    public ResponseEntity<Leitura> findLast(){
        return ResponseEntity.ok().body(leituraService.findLastLeitura());
    }

    @GetMapping("/lastByValue")
    public ResponseEntity<List<Leitura>> findLastByValue(@RequestParam("qtd") long qtd){
        return ResponseEntity.ok().body(leituraService.findLastsLeiturasByValue(qtd));
    }

    @GetMapping("/identifier")
    public ResponseEntity<Leitura> findByIdentifier(@RequestParam("value") String identifier){
        return ResponseEntity.ok().body(leituraService.findByIdentifier(identifier));
    }

}
