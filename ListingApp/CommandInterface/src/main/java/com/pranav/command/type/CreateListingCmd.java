package com.pranav.command.type;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.ICommandService;
import lombok.Data;

import java.util.List;

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
    private Double price;
    private String category;


    public CreateListingCmd(final List<String> commandArray, ICommandService service) {
        super(service);
        if(!isValid(commandArray)){
            throw new CommandNotValidException();
        }
        this.userName = commandArray.get(1);
        this.title = commandArray.get(2);
        this.description = commandArray.get(3);
        this.price =new Double(commandArray.get(4));
        this.category = commandArray.get(5);
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
