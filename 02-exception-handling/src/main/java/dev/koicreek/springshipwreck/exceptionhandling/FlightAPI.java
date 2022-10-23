package dev.koicreek.springshipwreck.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightAPI {

    // Main - Kanto, Johto, Hoenn, Sinnoh, Hisui, Unova, Kalos, Alola, Galar, Paldea
    // Side - Orre, Fiore, Almia, Ransei, Ferrum, Oblivia, Pasio, Lental
    // Source: https://pokemon.fandom.com/wiki/Regions

    private final List<FlightInfoCM> flightInfoList = new ArrayList<>();

    public FlightAPI() {
        flightInfoList.add(
                new FlightInfoCM(1, "Kanto", "Johto", "D80"));
        flightInfoList.add(
                new FlightInfoCM(2, "Kanto", "Hoenn", "110"));
        flightInfoList.add(
                new FlightInfoCM(3, "Johto", "Sinnoh", "15"));
        flightInfoList.add(
                new FlightInfoCM(4, "Hoenn", "Sinnoh", "A5"));
    }

    @GetMapping("/{id}")
    FlightInfoCM getFlightInfo(@PathVariable long id) {
        for (FlightInfoCM flightInfo : flightInfoList) {
            if (flightInfo.getId() == id) {
                if (flightInfo.getFrom().equals("Hoenn") || flightInfo.getTo().equals("Hoenn")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Air travel through Hoenn is grounded due to volcanic eruption");
                } else return flightInfo;
            }
        }
        throw new FlightNotFoundException(id);
    }

    @PostMapping
    public void addNewFlightInfo(@RequestBody @Valid FlightInfoCM flightInfo) {
        flightInfoList.add(flightInfo);
    }
}
