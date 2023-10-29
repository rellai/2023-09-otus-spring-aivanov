package ru.otus.aivanov.home03.service;

public interface IOService {
    void printLine(String s);

    void printColoredLine(String s, String colorLine);

    void printFormattedColoredLine(String s, String colorLine, String... args);

    String readString();

    String readStringWithPrompt(String prompt);

    int readIntForRange(int min, int max, String errorMessage);

}
