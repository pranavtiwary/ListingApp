package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

/**
 * Create Listing Command
 */
@Data
public class CreateListingCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 6;
    public static final String COMMAND_NAME = "CREATE_LISTING";

    private String userName;
    private String title;
    private String description;
    private String price;
    private String category;

    public CreateListingCmd(String[] commandArray) {
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }
        this.userName = commandArray[1];
        this.title = commandArray[2];
        this.description = commandArray[3];
        this.price = commandArray[4];
        this.category = commandArray[5];
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
