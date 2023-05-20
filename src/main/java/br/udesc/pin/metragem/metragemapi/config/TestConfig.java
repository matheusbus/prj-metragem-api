package br.udesc.pin.metragem.metragemapi.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.udesc.pin.metragem.metragemapi.model.Cidade;
import br.udesc.pin.metragem.metragemapi.model.Leitura;
import br.udesc.pin.metragem.metragemapi.model.Metragem;
import br.udesc.pin.metragem.metragemapi.model.enums.Clima;
import br.udesc.pin.metragem.metragemapi.services.CidadeService;
import br.udesc.pin.metragem.metragemapi.services.LeituraService;
import br.udesc.pin.metragem.metragemapi.services.MetragemService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private MetragemService metragemService;

    @Autowired
    private LeituraService leituraService;

    private static Random r = new Random();

    // Método que executará quando a aplicação iniciar com profile "test"
    @Override
    public void run(String... args) throws Exception {
        
        // Instancia tudo automaticamente
        metragemService.saveAll(this.instanciaMetragens(leituraService.saveAll(this.instanciaLeituras()), cidadeService.saveAll(this.instanciaCidades())));

    }

    private List<Cidade> instanciaCidades(){

        Cidade riodosul = new Cidade(null, "Rio do Sul", "SC", 4214805);
        Cidade ibirama = new Cidade(null, "Ibirama", "SC", 4206900);
        Cidade atalanta = new Cidade(null, "Atalanta", "SC", 4201802);
        Cidade pousoredondo = new Cidade(null, "Pouso Redondo", "SC", 4213708);
        Cidade taio = new Cidade(null, "Taió", "SC", 4217808);
        Cidade presidentegetulio = new Cidade(null, "Presidente Getúlio", "SC", 4214003);

        List<Cidade> cidades = new ArrayList<>();
        cidades.addAll(Arrays.asList(riodosul, ibirama, atalanta, pousoredondo, taio, presidentegetulio));

        return cidades;
    }

    private List<Leitura> instanciaLeituras(){

        List<Leitura> leituras = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Leitura leitura = new Leitura(LocalDate.now(), LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(i));
            leituraService.save(leitura).setLeiidentificador();
            leituras.add(leitura);
        }

        return leituras;
    }

    private List<Metragem> instanciaMetragens(List<Leitura> leituras, List<Cidade> cidades){
        
        List<Metragem> metragens = new ArrayList<>();

        LocalDate data = LocalDate.now();
        
        for(Leitura leitura : leituras){
            for(Cidade cidade : cidades){

                for(int i = 0; i < 5; i++){
    
                    int clima = r.nextInt(1, 4);
                    float indicePluviometrico = r.nextFloat(0.00f, 3.00f);
    
                    metragens.add(new Metragem(data,LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).plusHours(i), i, i, indicePluviometrico, Clima.valueOf(clima), cidade, leitura));
                }
            }
        }

        return metragens;
    }

}
