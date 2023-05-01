package dev.koicreek.springshipwreck.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokemonTrainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonTrainersApplication.class, args);
	}

}
