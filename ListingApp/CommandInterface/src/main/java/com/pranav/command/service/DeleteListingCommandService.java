package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.DeleteListingResponse;
import com.pranav.command.response.GetListingResponse;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.response.ListingDTO;
import com.pranav.command.type.DeleteListingCmd;
import com.pranav.command.type.GetListingCmd;
import com.pranav.command.type.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DeleteListingCommandService")
public class DeleteListingCommandService implements ICommandService {

    @Autowired
    private IListingAdapter listingAdapter;

    @Autowired
    private IUserAdapter userAdapter;

    public void execute(ICommand command){
        if(!(command instanceof DeleteListingCmd)){
            System.out.println("Get Delete Listing Command Service can only execute on DeleteListingCmd");
        }
        try {
            DeleteListingCmd cmd = (DeleteListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(cmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                DeleteListingResponse response =
                        listingAdapter.deleteListingByUserIdAndListingId(
                                cmd.getUserName(), cmd.getListingid());
                System.out.println(response.getMessage());
            }else{
                System.out.println("Error - unknown user");
            }

        }catch (Exception ex){
            System.out.println("Error while listing : DeleteListingCommandService");
            ex.printStackTrace();
        }
    }
}
