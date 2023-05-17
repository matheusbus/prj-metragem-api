package br.udesc.pin.metragem.metragemapi.config;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.repositories.CidadeRepository;
import br.udesc.pin.metragem.metragemapi.repositories.MetragemRepository;
import br.udesc.pin.metragem.metragemapi.services.CidadeService;
import br.udesc.pin.metragem.metragemapi.services.MetragemService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private MetragemRepository metragemRepository;

    @Autowired
    private MetragemService metragemService;

    // Método que executará quando a aplicação iniciar com profile "test"
    @Override
    public void run(String... args) throws Exception {
        
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);

        Cidade riodosul = new Cidade(null, "Rio do Sul", "SC", 4214805);
        Cidade ibirama = new Cidade(null, "Ibirama", "SC", 4206900);
        Cidade atalanta = new Cidade(null, "Atalanta", "SC", 4201802);
        Cidade pousoredondo = new Cidade(null, "Pouso Redondo", "SC", 4213708);
        Cidade taio = new Cidade(null, "Taió", "SC", 4217808);

        cidadeRepository.saveAll(Arrays.asList(riodosul, ibirama, atalanta, pousoredondo, taio));

        Metragem metragemRioDoSul1 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))), 
            8.14f, 0.00f, 0.1f, Clima.BOM, riodosul);
        
        Metragem metragemRioDoSul2 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(1), 
            8.21f, 0.07f, 0.00f, Clima.BOM, riodosul);
        
        Metragem metragemRioDoSul3 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(2), 
            8.28f, 0.07f, 0.00f, Clima.BOM, riodosul);
        
        Metragem metragemRioDoSul4 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(3), 
            8.31f, 0.04f, 0.00f, Clima.BOM, riodosul);
        
        Metragem metragemRioDoSul5 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(4), 
            8.33f, 0.02f, 0.00f, Clima.BOM, riodosul);
        
        Metragem metragemRioDoSul6 = new Metragem(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(5), 
            8.35f, 0.02f, 0.00f, Clima.BOM, riodosul);
        
        metragemRepository.saveAll(Arrays.asList(metragemRioDoSul1, 
                                                 metragemRioDoSul2, 
                                                 metragemRioDoSul3, 
                                                 metragemRioDoSul4, 
                                                 metragemRioDoSul5, 
                                                 metragemRioDoSul6));

    }

    @Scheduled(cron = "0/10 * * * * *")
    public void gerarMetragem(){
        System.out.println(metragemService.gravarNovaLeitura(cidadeService.findByCodIbge(4214805)));
    }


}
