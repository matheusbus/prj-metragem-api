package br.udesc.pin.metragem.metragemapi.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.repositories.CidadeRepository;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;
import jakarta.persistence.Temporal;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private MetragemRepository metragemRepository;

    // Método que executará quando a aplicação iniciar com profile "test"
    @Override
    public void run(String... args) throws Exception {
        
        Cidade riodosul = new Cidade(null, "Rio do Sul", "SC", 4214805);
        Cidade ibirama = new Cidade(null, "Ibirama", "SC", 4206900);
        Cidade atalanta = new Cidade(null, "Atalanta", "SC", 4201802);
        Cidade pousoredondo = new Cidade(null, "Pouso Redondo", "SC", 4213708);
        Cidade taio = new Cidade(null, "Taió", "SC", 4217808);

        cidadeRepository.saveAll(Arrays.asList(riodosul, ibirama, atalanta, pousoredondo, taio));

        Metragem metragemRiodoSul1 = new Metragem();
        metragemRiodoSul1.setData(LocalDate.now());
        metragemRiodoSul1.setHora(LocalTime.now());
        metragemRiodoSul1.setClima(Clima.BOM);
        metragemRiodoSul1.setNivel(9.14f);
        metragemRiodoSul1.setIndicePluviometrico(0.15f);

        System.out.println(metragemRiodoSul1);

    }



}
