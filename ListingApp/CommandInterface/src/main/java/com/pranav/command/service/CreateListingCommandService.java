package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.CreateListingResponse;
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
        if(!(command instanceof CreateListingCmd)){
            System.out.println("Create listing Command Service can only execute on RegisterUserCmd");
        }
        try {
            CreateListingCmd createLstCmd = (CreateListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(createLstCmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                CreateListingResponse response = listingAdapter.createListing(
                        createLstCmd.getUserName(),
                        createLstCmd.getTitle(),
                        createLstCmd.getDescription(),
                        createLstCmd.getPrice(),
                        createLstCmd.getCategory());
                if(response.getIsSuccess()){
                    System.out.println(response.getLisitngId());
                }else {
                    System.out.println(response.getMessage());
                }
            } else {
                System.out.println("Error - unknown user");
            }
        }catch (Exception ex){
            System.out.println("Error while CreateListingCommandService");
            ex.printStackTrace();
        }
    }
}
