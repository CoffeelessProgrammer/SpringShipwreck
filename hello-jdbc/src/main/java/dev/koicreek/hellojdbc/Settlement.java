package dev.koicreek.hellojdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Settlement {
    private int cityId;
    @NotBlank
    private String cityName;
    @NotBlank
    private String region;
    private String slogan;
}
