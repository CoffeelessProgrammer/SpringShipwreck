package dev.koicreek.springshipwreck.pokemon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainers/{id}/party")
public class TrainerPartyAPI {

    private static Map<Long, List<PartyPokemonCM>> partyData;

    static {
        partyData = new HashMap<>();

        partyData.put(0L, new ArrayList<>(6));
        partyData.get(0L).add(new PartyPokemonCM(0, 723, "Dartrix", List.of("Grass", "Flying")));
        partyData.get(0L).add(new PartyPokemonCM(0, 84, "Smoliv", List.of("Grass", "Normal")));

        partyData.put(1L,  new ArrayList<>(6));
        partyData.get(1L).add(new PartyPokemonCM(1, 25, "Pikachu", List.of("Electric")));
        partyData.get(1L).add(new PartyPokemonCM(1, 12, "Butterfree", List.of("Bug", "Flying")));

        partyData.put(2L,  new ArrayList<>(6));
        partyData.get(2L).add(new PartyPokemonCM(2, 175, "Togepi", List.of("Fairy")));
        partyData.get(2L).add(new PartyPokemonCM(2, 120, "Staryu", List.of("Water")));

        partyData.put(3L,  new ArrayList<>(6));
        partyData.get(3L).add(new PartyPokemonCM(3, 74, "Geodude", List.of("Rock", "Ground")));
        partyData.get(3L).add(new PartyPokemonCM(3, 95, "Onix", List.of("Rock", "Ground")));
    }

    @GetMapping
    List<PartyPokemonCM> getTrainerParty(@PathVariable("id") long trainerId) {
        return partyData.get(trainerId);
    }

    @PostMapping
    List<PartyPokemonCM> addToParty(@RequestBody PartyPokemonCM pokemon, @PathVariable("id") long trainerId) {
        List<PartyPokemonCM> trainerParty = partyData.get(trainerId);

        if(trainerParty.size() >= 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainer party is full");

        pokemon.setTrainerId(trainerId);
        trainerParty.add(pokemon);
        return trainerParty;
    }
}
