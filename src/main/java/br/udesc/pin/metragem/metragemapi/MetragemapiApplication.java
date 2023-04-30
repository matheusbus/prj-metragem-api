package br.udesc.pin.metragem.metragemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetragemapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetragemapiApplication.class, args);
	}

}
