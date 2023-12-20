package ru.otus.aivanov.home09.mapper;

import org.mapstruct.Mapper;
import ru.otus.aivanov.home09.dto.AuthorDto;
import ru.otus.aivanov.home09.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toModel(AuthorDto author);
}
