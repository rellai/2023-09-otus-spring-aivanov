package ru.otus.aivanov.home08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		//Console.main(args);
		SpringApplication.run(Main.class, args);

	}

}
