package ru.otus.aivanov.home10.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.aivanov.home10.dto.GenreCreateDto;
import ru.otus.aivanov.home10.dto.GenreDto;
import ru.otus.aivanov.home10.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toModel(GenreDto genre);

    @Mapping(target = "id", ignore = true)
    Genre toModel(GenreCreateDto genre);
}
