package dev.koicreek.springshipwreck.pokemon.settlements;

import dev.koicreek.springshipwreck.pokemon.settlements.constants.PokemonRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementsAPI {

    @GetMapping
    public ResponseEntity<List<PokemonRegion>> getSettlements() {
        return ResponseEntity.noContent().build();
    }
}
