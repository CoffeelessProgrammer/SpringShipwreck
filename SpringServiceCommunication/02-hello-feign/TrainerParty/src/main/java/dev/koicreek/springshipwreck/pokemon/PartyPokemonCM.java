package dev.koicreek.springshipwreck.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PartyPokemonCM {

    private long trainerId;
    private final int pokedexId;
    private final String pokedexName;
    private final List<String> types;
}
