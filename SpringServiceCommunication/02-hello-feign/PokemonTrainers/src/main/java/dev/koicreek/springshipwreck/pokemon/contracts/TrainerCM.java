package dev.koicreek.springshipwreck.pokemon.contracts;

import lombok.Data;

import java.util.List;

@Data
public class TrainerCM {
    private final long id;
    private final String name;

    private List<PartyPokemonCM> party;
}
