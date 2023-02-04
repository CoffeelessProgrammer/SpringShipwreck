package dev.koicreek.springshipwreck.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@AllArgsConstructor
public class ConfigClientApplication {

	private final Environment env;
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@GetMapping("/secret")
	private String getSecretToken() {
		return env.getProperty("token.secret");
	}

}
