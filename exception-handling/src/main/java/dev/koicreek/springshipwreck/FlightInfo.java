package dev.koicreek.springshipwreck;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class FlightInfo {

    @Min(1)
    private long id;
    private String from;
    private String to;
    private String gate;

}
