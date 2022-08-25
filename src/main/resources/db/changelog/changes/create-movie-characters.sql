-- liquibase formatted sql

-- changeset <gdp>:<create-movie-characters>
CREATE TABLE IF NOT EXISTS public.movie_characters
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    PRIMARY KEY (id)
);

-- rollback drop table public.movie_characters;
