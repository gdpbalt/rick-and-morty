package com.example.rickandmorty.repository;

import com.example.rickandmorty.model.MovieCharacter;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> getAllByExternalIdIn(Set<Long> externalIds);

    List<MovieCharacter> findAllByNameContainsIgnoreCase(String partName);
}
