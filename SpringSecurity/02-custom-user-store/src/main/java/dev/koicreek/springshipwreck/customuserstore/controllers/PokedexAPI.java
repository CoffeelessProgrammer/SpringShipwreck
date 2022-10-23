package dev.koicreek.springshipwreck.customuserstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokedex")
public class PokedexAPI {

    @GetMapping("/hoenn")
    String hoennPokemonCount() {
        return "Region: Hoenn - 202 native pokemon, 135 newly discovered";
    }
}
