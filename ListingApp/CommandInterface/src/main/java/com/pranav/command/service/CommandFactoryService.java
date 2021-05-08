package com.pranav.command.service;


import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * CommandFactory : Class used to build all commands
 *
 */
@Component
public class CommandFactoryService {

    @Autowired
    @Qualifier("RegisterCommandService")
    private ICommandService registerCommandService;


    public ICommand createCommand(String input) {
        if(!StringUtils.hasLength(input)){
            throw new CommandNotValidException();
        }
        ICommand command = null;
        String[] commandArray = input.split(" ");
        String commandName = commandArray[0];
        switch (commandName){
            case RegisterUserCmd.COMMAND_NAME:
                command = new RegisterUserCmd(commandArray, registerCommandService);
                break;
            case "":
                command = null;
                break;
        }
        if(Objects.isNull(command)){
            throw new CommandNotValidException();
        }
        return command;
    }
}
