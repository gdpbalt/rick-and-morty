package com.example.rickandmorty.service;

import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.dto.external.ApiResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.repository.MovieCharacterRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    @Value("${character.external.api.url}")
    private String externalApiUrl;
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper mapper;

    @Scheduled(cron = "0 30 7 * * *")
    @Override
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters method was invoked at " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = null;
        String currentUrl = null;
        do {
            currentUrl = currentUrl == null ? externalApiUrl : apiResponseDto.getInfo().getNext();
            apiResponseDto = httpClient.get(currentUrl, ApiResponseDto.class);
            log.debug("API response {}", apiResponseDto);
            saveDtosToDb(apiResponseDto);
        } while (apiResponseDto.getInfo().getNext() != null);
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        int randomCharacter = (int) (Math.random() * movieCharacterRepository.count());
        PageRequest pageRequest = PageRequest.of(randomCharacter, 1, Sort.by("id"));
        Page<MovieCharacter> questionPage = movieCharacterRepository.findAll(pageRequest);
        MovieCharacter movieCharacter = null;
        if (questionPage.hasContent()) {
            movieCharacter = questionPage.getContent().get(0);
        }
        return movieCharacter;
    }

    @Override
    public List<MovieCharacter> findAllByNameContains(String partName) {
        return movieCharacterRepository.findAllByNameContainsIgnoreCase(partName);
    }

    @Override
    public Long countCharacters() {
        return movieCharacterRepository.count();
    }

    private void saveDtosToDb(ApiResponseDto responseDto) {
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(responseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();

        Map<Long, MovieCharacter> existingCharacters =
                movieCharacterRepository.getAllByExternalIdIn(externalIds).stream()
                        .collect(Collectors.toMap(MovieCharacter::getExternalId,
                                Function.identity()));

        externalIds.removeAll(existingCharacters.keySet());
        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(id -> mapper.toModel(externalDtos.get(id)))
                .collect(Collectors.toList());
        movieCharacterRepository.saveAll(charactersToSave);
    }
}
