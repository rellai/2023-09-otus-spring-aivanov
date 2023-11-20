package ru.otus.aivanov.home05;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		Console.main(args);
		SpringApplication.run(Main.class, args);

	}

}
