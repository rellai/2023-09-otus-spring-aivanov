package ru.otus.aivanov.home01.service;

import ru.otus.aivanov.home01.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class GetFileFromResourceService {

    public static BufferedReader getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class


        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("Файл не найден " + fileName);
        } else {

            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new BufferedReader(streamReader);
        }

    }
}
