package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.ICommandService;
import lombok.Data;

import java.util.List;

/**
 * Get Top Category of listing Command
 */
@Data
public class GetTopCategoryListingCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 2;
    public static final String COMMAND_NAME = "GET_TOP_CATEGORY";

    private String userName;

    public GetTopCategoryListingCmd(final List<String> commandArray, ICommandService service) {
        super(service);
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }

        this.userName = commandArray.get(1);
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
