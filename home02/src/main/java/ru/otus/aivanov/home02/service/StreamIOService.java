package ru.otus.aivanov.home02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static ru.otus.aivanov.home02.utils.Const.CONSOLE_TEXT_COLOR_RESET;

@Service
public class StreamIOService implements IOService {

    private static final int MAX_ATTEMPTS = 10;

    private final PrintStream printStream;

    private final Scanner scanner;

    public StreamIOService(@Value("#{T(System).out}") PrintStream printStream,
                           @Value("#{T(System).in}") InputStream inputStream) {

        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printColoredLine(String s, String colorLine) {
        printStream.println(colorLine.isEmpty() ? CONSOLE_TEXT_COLOR_RESET : colorLine + s);
    }

    @Override
    public void printFormattedColoredLine(String s, String colorLine, Object... args) {
        printStream.printf(colorLine.isEmpty() ? CONSOLE_TEXT_COLOR_RESET : colorLine + s + "%n", args);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        printLine(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readIntForRange(int min, int max, String errorMessage) {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                var stringValue = scanner.nextLine();
                int intValue = Integer.parseInt(stringValue);
                if (intValue < min || intValue > max) {
                    throw new IllegalArgumentException();
                }
                return intValue;
            } catch (IllegalArgumentException e) {
                printLine(errorMessage);
            }
        }
        throw new IllegalArgumentException("Error during reading int value");
    }


}
