package com.example.rickandmorty.config;

import com.example.rickandmorty.service.MovieCharacterService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final MovieCharacterService movieCharacterService;

    @PostConstruct
    public void dataInitialisation() {
        if (movieCharacterService.countCharacters() == 0) {
            movieCharacterService.syncExternalCharacters();
        }
    }
}
