package dev.koicreek.springshipwreck.pokemon.settlements;

import dev.koicreek.springshipwreck.pokemon.settlements.constants.PokemonRegion;
import lombok.Data;

@Data
public class SettlementCM {
    private String name;
    private String nameJpn;
    private PokemonRegion region;
    private String slogan;
}
