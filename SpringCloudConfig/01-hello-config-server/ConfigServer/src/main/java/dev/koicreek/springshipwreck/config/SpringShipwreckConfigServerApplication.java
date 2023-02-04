package dev.koicreek.springshipwreck.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigServer
public class SpringShipwreckConfigServerApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SpringShipwreckConfigServerApplication.class, args);
	}

	@Bean
	CommandLineRunner displayPropertiesFilePath() {
		return args -> {
			System.out.printf("User home: %s\n", env.getProperty("user.home"));
			System.out.printf("Path: %s\n", env.getProperty("spring.cloud.config.server.native.search-locations"));
		};
	}

}
