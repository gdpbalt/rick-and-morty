-- liquibase formatted sql

-- changeset <gdp>:<create-movie-characters-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.movie_characters_id_seq INCREMENT 1 START 1 MINVALUE 1;

-- rollback drop table public.movie_characters_id_seq;
