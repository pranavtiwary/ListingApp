package com.pranav.command;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.CommandFactoryService;
import com.pranav.command.type.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private CommandFactoryService factory;

    @Override
    public void run(String...args) throws Exception {
        System.out.println("Application started");
        Scanner scanner = new Scanner(System.in);
        String commandString = "";
        while (true) {
            System.out.print("Enter Command ( or q to quite): ");
            commandString = scanner.nextLine();
            if("q".equalsIgnoreCase(commandString)){
                break;
            }
            try{
                ICommand command = factory.createCommand(commandString);
                command.execute();
            }catch (CommandNotValidException ex){
                System.out.print(ex.getMessage());
            }catch (Exception ex){
                System.out.println("Error while executing command");
                System.out.print(ex.getMessage());
                ex.printStackTrace();
            }
        }
        System.out.println("Bye Bye");
    }
}