package dev.coffeelessprogrammer.h2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class PokemonTrainer {

    @Id
    @Column
    private long trainerId;
    @Column
    private String name;
    @Column
    private String hometown;
    @Column
    private boolean isGymLeader;

}
