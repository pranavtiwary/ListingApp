package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.ICommandService;
import lombok.Data;

import java.util.List;

/**
 * Get Category of listing Command
 */
@Data
public class GetCategoryListingCmd extends AbstractCommand{

    private static final int COMMAND_SIZE = 5;
    public static final String COMMAND_NAME = "GET_CATEGORY";

    private String userName;
    private String category;
    private String sortby;
    private String order;


    public GetCategoryListingCmd(final List<String> commandArray, ICommandService service) {
        super(service);
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }

        this.userName = commandArray.get(1);
        this.category = commandArray.get(2);
        this.sortby = commandArray.get(3);
        this.order = commandArray.get(4);
        if(!(sortby.equalsIgnoreCase("SORT_PRICE")
                || sortby.equalsIgnoreCase("SORT_TIME"))){
            throw new CommandNotValidException();
        }
        if(!(order.equalsIgnoreCase("ASC")
                || order.equalsIgnoreCase("DSC"))){
            throw new CommandNotValidException();
        }
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
