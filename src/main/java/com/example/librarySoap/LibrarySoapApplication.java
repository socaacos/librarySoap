package com.example.librarySoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LibrarySoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySoapApplication.class, args);
	}

}
