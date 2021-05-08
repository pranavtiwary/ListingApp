package com.pranav.command.service;

import com.pranav.command.adapter.UserServiceAdapter;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegisterCommandService")
public class RegisterCommandService implements ICommandService {

    @Autowired
    private UserServiceAdapter adpater;

    public void execute(ICommand command){
        if(!(command instanceof RegisterUserCmd)){
            System.out.println("Register Command Service can only execute on RegisterUserCmd");
        }
        adpater.registerUser(((RegisterUserCmd)command).getUserName());
    }
}
