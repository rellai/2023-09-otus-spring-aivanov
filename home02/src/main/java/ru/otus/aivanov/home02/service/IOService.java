package ru.otus.aivanov.home02.service;

public interface IOService {
    void printLine(String s);

    void printColoredLine(String s, String colorLine);

    public void printFormattedColoredLine(String s, String colorLine, Object... args);

    String readString();

    String readStringWithPrompt(String prompt);

    int readIntForRange(int min, int max, String errorMessage);

    int readIntForRangeWithPrompt(int min, int max, String prompt, String color, String errorMessage);
}
