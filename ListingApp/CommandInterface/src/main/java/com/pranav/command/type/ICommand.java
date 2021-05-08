package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import org.springframework.stereotype.Component;

@Component
public interface ICommand {
    default boolean isValid(String[] commandArray) throws CommandNotValidException{
        return true;
    }
    String getName();
    int getCommandSize();
    void execute();
}
