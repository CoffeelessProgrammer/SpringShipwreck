package dev.koicreek.springshipwreck.customuserstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/trainer/party")
public class TrainerPartyAPI {
    private final Map<String, List<String>> party = new ConcurrentHashMap<>();

    @GetMapping
    List<String> getParty(@AuthenticationPrincipal UserDetails details) {
        return party.getOrDefault(details.getUsername(), List.of("No pokemon found. Start your adventure!"));
    }

    @PostMapping
    List<String> addPokemon(@AuthenticationPrincipal UserDetails details, @RequestParam String pokemon) {
        String username = details.getUsername();
        pokemon = StringUtils.capitalize(pokemon.toLowerCase());

        party.putIfAbsent(username, new ArrayList<>());
        List<String> trainerParty = party.get(username);

        if(trainerParty.size() < 6)
            trainerParty.add(pokemon);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Party is full, pokemon sent to PC.");

        return trainerParty;
    }

}
