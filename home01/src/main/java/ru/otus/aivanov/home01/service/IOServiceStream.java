package ru.otus.aivanov.home01.service;

import java.io.PrintStream;


import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_RESET;

public class IOServiceStream implements IOService {
    private final PrintStream printStream;

    public IOServiceStream(PrintStream printStream) {

        this.printStream = printStream;
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
    public void printFormattedLine(String s, String colorLine, Object... args) {
        printStream.printf(colorLine.isEmpty() ? CONSOLE_TEXT_COLOR_RESET : colorLine + s + "%n", args);
    }
}
