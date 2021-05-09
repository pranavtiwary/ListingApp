package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.ICommandService;
import lombok.Data;

import java.util.List;

/**
 * Get Listing Command
 */
@Data
public class GetListingCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 3;
    public static final String COMMAND_NAME = "GET_LISTING";

    private String userName;
    private String listingid;


    public GetListingCmd(final List<String> commandArray, ICommandService service) {
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
