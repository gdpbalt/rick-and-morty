package com.example.rickandmorty.dto.external;

import lombok.Data;

@Data
public class ApiCharacterDto {
    private long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
}
