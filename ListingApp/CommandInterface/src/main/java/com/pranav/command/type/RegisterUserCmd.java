package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import lombok.Data;

/**
 * RegisterUserCmd
 */
@Data
public class RegisterUserCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 2;
    public static final String COMMAND_NAME = "REGISTER";

    private String userName;

    public RegisterUserCmd(String[] commandArray) {
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }
        this.userName = commandArray[1];
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public int getCommandSize() {
        return COMMAND_SIZE;
    }
}
