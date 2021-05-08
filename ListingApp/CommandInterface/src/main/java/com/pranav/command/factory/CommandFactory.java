package com.pranav.command.factory;


import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * CommandFactory : Class used to build all commands
 *
 */
public class CommandFactory {

    public static ICommand createCommand(String input) {
        if(!StringUtils.hasLength(input)){
            throw new CommandNotValidException();
        }
        ICommand command = null;
        String[] commandArray = input.split(" ");
        String commandName = commandArray[0];
        switch (commandName){
            case RegisterUserCmd.COMMAND_NAME:
                command = new RegisterUserCmd(commandArray);
                break;
            case "":
                command = new RegisterUserCmd(commandArray);
                break;
        }
        if(Objects.isNull(command)){
            throw new CommandNotValidException();
        }
        return command;
    }
}
