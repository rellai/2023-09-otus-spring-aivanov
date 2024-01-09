package ru.otus.aivanov.home10.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.aivanov.home10.dto.AuthorCreateDto;
import ru.otus.aivanov.home10.dto.AuthorDto;
import ru.otus.aivanov.home10.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toModel(AuthorDto author);

    @Mapping(target = "id", ignore = true)
    Author toModel(AuthorCreateDto author);
}
