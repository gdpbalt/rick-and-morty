package com.example.rickandmorty.controller;

import com.example.rickandmorty.dto.MovieCharacterResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-character")
@RequiredArgsConstructor
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    @GetMapping("/random")
    @ApiOperation(value = "Get a random movie character "
            + "in the universe the animated series Rick & Morty")
    public MovieCharacterResponseDto getRandomCharacter() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return mapper.toDto(character);
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Find a movie character "
            + "in the universe the animated series Rick & Morty by the part of the name")
    public List<MovieCharacterResponseDto> findAllByName(
            @RequestParam(value = "name", defaultValue = "") String partName) {
        return movieCharacterService.findAllByNameContains(partName).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
