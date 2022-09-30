package dev.coffeelessprogrammer.jetbrainsacademy.hellospringsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonCenterAPI {

    @GetMapping("/public")
    public String welcome() {
        return "Welcome to the Pokemon Center.";
    }

    @GetMapping("/trade")
    public String globalTradeStation() {
        return "Would you like to connect to the GTS?";
    }

    @GetMapping("/heal")
    public String healPokemon() {
        return "Okay, I'll take your Pokémon for a few seconds... your pokemon are now healed.";
    }

    @GetMapping("/service")
    public String serviceRecoveryMachine() {
        return "The Pokemon recovery machine needs a tune up... servicing complete.";
    }

}
