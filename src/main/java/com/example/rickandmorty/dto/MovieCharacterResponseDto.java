package com.example.rickandmorty.dto;

import lombok.Data;

@Data
public class MovieCharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
}
