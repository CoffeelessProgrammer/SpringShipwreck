package dev.koicreek.springshipwreck.hellojpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static dev.koicreek.springshipwreck.hellojpa.StringifyUtil.wrap;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PokemonTrainer {");

        sb.append(String.format("\n\tid: %d", this.trainerId));
        sb.append(String.format(",\n\tname: %s", wrap(this.name)));
        sb.append(String.format(",\n\thometown: %s", wrap(this.hometown)));
        sb.append(String.format(",\n\tisGymLeader: %s", isGymLeader));
        sb.append("\n}");

        return sb.toString();
    }

}
