package dev.koicreek.springshipwreck.hellojdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SettlementCM {
    private int cityId;
    @NotBlank
    private String cityName;
    @NotBlank
    private String region;
    private String slogan;
}
