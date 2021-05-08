package com.pranav.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.pranav.command"})
public class CommandApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommandApplication.class, args);
	}
}
