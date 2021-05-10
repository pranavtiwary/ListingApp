package com.pranav.command.service;


import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.type.CreateListingCmd;
import com.pranav.command.type.DeleteListingCmd;
import com.pranav.command.type.GetCategoryListingCmd;
import com.pranav.command.type.GetListingCmd;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CommandFactory : Class used to build all commands
 *
 */
@Component
public class CommandFactoryService {

    @Autowired
    @Qualifier("RegisterUserCommandService")
    private ICommandService registerUserCommandService;

    @Autowired
    @Qualifier("CreateListingCommandService")
    private ICommandService createListingCommandService;

    @Autowired
    @Qualifier("GetListingCommandService")
    private ICommandService getListingCommandService;

    @Autowired
    @Qualifier("DeleteListingCommandService")
    private ICommandService getDeleteListingCmdService;

    @Autowired
    @Qualifier("GetCategoryListingCmdService")
    private ICommandService getCategoryListingCmdService;


    public ICommand createCommand(String input) {
        if(!StringUtils.hasLength(input)){
            throw new CommandNotValidException();
        }
        ICommand command = null;
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\']\\S*|\'.+?\')\\s*").matcher(input);
        while (m.find()){
            list.add(m.group(1).replace("'", ""));
        }
        System.out.println("Command List : " + list);
        String commandName = list.get(0);
        switch (commandName){
            case RegisterUserCmd.COMMAND_NAME:
                command = new RegisterUserCmd(list, registerUserCommandService);
                break;
            case CreateListingCmd.COMMAND_NAME:
                command = new CreateListingCmd(list, createListingCommandService);
                break;
            case GetListingCmd.COMMAND_NAME:
                command = new GetListingCmd(list, getListingCommandService);
                break;
            case DeleteListingCmd.COMMAND_NAME:
                command = new DeleteListingCmd(list, getDeleteListingCmdService);
                break;
            case GetCategoryListingCmd.COMMAND_NAME:
                command = new GetCategoryListingCmd(list, getCategoryListingCmdService);
                break;
        }
        if(Objects.isNull(command)){
            throw new CommandNotValidException();
        }
        return command;
    }
}
