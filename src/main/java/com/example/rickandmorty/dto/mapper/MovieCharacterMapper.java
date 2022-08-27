package com.example.rickandmorty.dto.mapper;

import com.example.rickandmorty.dto.MovieCharacterResponseDto;
import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter toModel(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setExternalId(dto.getId());
        movieCharacter.setName(dto.getName());
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setSpecies(dto.getSpecies());
        movieCharacter.setType(dto.getType());
        movieCharacter.setImage(dto.getImage());
        return movieCharacter;
    }

    public MovieCharacterResponseDto toDto(MovieCharacter movieCharacter) {
        MovieCharacterResponseDto dto = new MovieCharacterResponseDto();
        dto.setId(movieCharacter.getId());
        dto.setExternalId(movieCharacter.getExternalId());
        dto.setName(movieCharacter.getName());
        dto.setStatus(movieCharacter.getStatus().getValue());
        dto.setGender(movieCharacter.getGender().getValue());
        dto.setSpecies(movieCharacter.getSpecies());
        dto.setType(movieCharacter.getType());
        dto.setImage(movieCharacter.getImage());
        return dto;
    }
}
