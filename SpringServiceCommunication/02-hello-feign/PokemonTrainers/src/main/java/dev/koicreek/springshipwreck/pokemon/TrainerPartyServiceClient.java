package dev.koicreek.springshipwreck.pokemon;

import dev.koicreek.springshipwreck.pokemon.contracts.PartyPokemonCM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="TrainerParty-Server-V0")
public interface TrainerPartyServiceClient {

    @GetMapping("/trainers/{trainerId}/party")
    public List<PartyPokemonCM> getTrainerParty(@PathVariable long trainerId);

    @PostMapping("/trainers/{trainerId}/party")
    public List<PartyPokemonCM> addToTrainerParty(PartyPokemonCM pokemon, @PathVariable long trainerId);
}
