package dev.koicreek.springshipwreck.pokemon.contracts;

import lombok.Data;

import java.util.List;

@Data
public class PartyPokemonCM {
    private final int pokedexId;
    private final String pokedexName;
    private final List<String> types;
}
