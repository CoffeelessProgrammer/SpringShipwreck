package dev.koicreek.springshipwreck.pokemon.settlements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceDetailsAPI {

    @Autowired
    private Environment env;

    @GetMapping("/port")
    public ResponseEntity<String> getPort() {
        final String port = env.getProperty("local.server.port");
        return ResponseEntity.ok("Running on port: " + port);
    }
}
