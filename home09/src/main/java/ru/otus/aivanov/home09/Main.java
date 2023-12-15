package ru.otus.aivanov.home09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		//Console.main(args);
		SpringApplication.run(Main.class, args);
		System.out.println("Server run http://localhost:8080");

	}

}
