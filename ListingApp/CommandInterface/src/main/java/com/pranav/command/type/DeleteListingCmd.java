package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.ICommandService;
import lombok.Data;

import java.util.List;

/**
 * DeleteListingCmd
 */
@Data
public class DeleteListingCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 3;
    public static final String COMMAND_NAME = "DELETE_LISTING";

    private String userName;
    private String listingid;

    public DeleteListingCmd(final List<String> commandArray, ICommandService service) {
        super(service);
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }
        this.userName = commandArray.get(1);
        this.listingid = commandArray.get(2);
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
