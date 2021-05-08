package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;

public interface ICommand {
    default boolean isValid(String[] commandArray) throws CommandNotValidException{
        return true;
    }
    String getName();
    int getCommandSize();
}
