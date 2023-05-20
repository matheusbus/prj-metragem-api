package br.udesc.pin.metragem.metragemapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.udesc.pin.metragem.metragemapi.model.Leitura;
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


}
