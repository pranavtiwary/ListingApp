package com.pranav.command.service;

import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.adapter.UserAdapterImpl;
import com.pranav.command.response.CreateUserResponse;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegisterUserCommandService")
public class RegisterUserCommandService implements ICommandService {

    @Autowired
    private IUserAdapter adpater;

    public void execute(ICommand command){
        if(!(command instanceof RegisterUserCmd)){
            System.out.println("Register Command Service can only execute on RegisterUserCmd");
        }
        RegisterUserCmd usrCmd = (RegisterUserCmd)command;
        CreateUserResponse userResponse = adpater.registerUser(usrCmd.getUserName());
        if(null == userResponse){
            System.out.println("Error : Not able to execute command");
        }else{
            System.out.println(userResponse.getMessage());
        }
    }
}
