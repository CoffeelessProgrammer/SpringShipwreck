package dev.coffeelessprogrammer.h2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTrainerDAO extends CrudRepository<PokemonTrainer, Long> {
}
