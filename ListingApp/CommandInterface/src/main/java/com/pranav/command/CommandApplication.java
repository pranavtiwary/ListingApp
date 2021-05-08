package com.pranav.command;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.factory.CommandFactory;
import com.pranav.command.type.ICommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class CommandApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommandApplication.class, args);

		Console console = System.console();

		String input = "";
		while (!"q".equalsIgnoreCase(input)) {
			System.out.print("Enter Command ( or q to quite): ");
			input = console.readLine();
			try{
				ICommand command = CommandFactory.createCommand(input);
			}catch (CommandNotValidException ex){
				System.out.print(ex.getMessage());
			}
		}

		System.out.println("bye bye!");

	}

}
