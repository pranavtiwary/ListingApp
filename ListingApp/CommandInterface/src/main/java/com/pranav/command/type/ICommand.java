package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICommand {
    default boolean isValid(final List<String> commandArray) throws CommandNotValidException{
        return true;
    }
    String getName();
    int getCommandSize();
    void execute();
}
