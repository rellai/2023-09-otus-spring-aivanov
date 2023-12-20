package ru.otus.aivanov.home09.mapper;

import org.mapstruct.Mapper;
import ru.otus.aivanov.home09.dto.GenreDto;
import ru.otus.aivanov.home09.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toModel(GenreDto genre);
}
