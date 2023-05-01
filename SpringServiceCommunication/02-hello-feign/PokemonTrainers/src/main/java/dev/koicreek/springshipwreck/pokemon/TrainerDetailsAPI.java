package dev.koicreek.springshipwreck.pokemon;


import dev.koicreek.springshipwreck.pokemon.contracts.PartyPokemonCM;
import dev.koicreek.springshipwreck.pokemon.contracts.TrainerCM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainers")
@AllArgsConstructor
public class TrainerDetailsAPI {

    private static final List<TrainerCM> trainersData;

    static {
        trainersData = new ArrayList<>();
        trainersData.add(new TrainerCM(0, "Laura"));
        trainersData.add(new TrainerCM(1, "Ash"));
        trainersData.add(new TrainerCM(2, "Misty"));
        trainersData.add(new TrainerCM(3, "Brock"));
    }

    private final TrainerPartyServiceClient trainerPartyClient;

    @GetMapping("/{id}")
    TrainerCM getTrainerDetails(@PathVariable long id) {
        TrainerCM trainer = trainersData.get((int) id);
        trainer.setParty(trainerPartyClient.getTrainerParty(id));
        return trainer;
    }

    @PostMapping("/{id}")
    TrainerCM addToTrainerParty(@RequestBody PartyPokemonCM pokemon, @PathVariable long id) {
        TrainerCM trainer = trainersData.get((int) id);
        trainer.setParty(trainerPartyClient.addToTrainerParty(pokemon, id));
        return trainer;
    }
}
