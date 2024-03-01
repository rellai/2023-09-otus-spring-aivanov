package ru.otus.aivanov.home18.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreateDto {

    @NotBlank
    private String title;

    @NotNull
    private Long genreId;

    @NotNull
    private Long authorId;
}
