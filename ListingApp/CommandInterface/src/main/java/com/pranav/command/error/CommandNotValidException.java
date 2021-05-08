package com.pranav.command.error;

/**
 * CommandNotValidException : Exception when command not valid
 *
 */
public class CommandNotValidException extends RuntimeException {
    public CommandNotValidException(){
        super("Command is not valid!");
    }
}
