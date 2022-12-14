package com.example.rickandmorty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_characters")
public class MovieCharacter {
    @Id
    @GeneratedValue(generator = "movie_characters_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "movie_characters_id_seq",
            sequenceName = "movie_characters_id_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "external_id")
    private Long externalId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String species;
    private String type;
    private String image;
}
