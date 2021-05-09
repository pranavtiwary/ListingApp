package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.type.CreateListingCmd;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CreateListingCommandService")
public class CreateListingCommandService implements ICommandService {

    @Autowired
    private IListingAdapter listingAdapter;

    @Autowired
    private IUserAdapter userAdapter;

    public void execute(ICommand command){
        if(!(command instanceof RegisterUserCmd)){
            System.out.println("Register Command Service can only execute on RegisterUserCmd");
        }
        try {
            CreateListingCmd createLstCmd = (CreateListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(createLstCmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                listingAdapter.createListing(
                        createLstCmd.getUserName(),
                        createLstCmd.getTitle(),
                        createLstCmd.getDescription(),
                        createLstCmd.getPrice(),
                        createLstCmd.getCategory());
            } else {
                System.out.println("User does not exists");
            }
        }catch (Exception ex){
            System.out.println("Error while creating listing");
            ex.printStackTrace();
        }
    }
}
