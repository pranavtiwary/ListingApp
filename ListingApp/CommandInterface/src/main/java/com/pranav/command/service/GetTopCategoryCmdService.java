package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.GetListingResponse;
import com.pranav.command.response.GetTopCategoryResponse;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.response.ListingDTO;
import com.pranav.command.type.GetCategoryListingCmd;
import com.pranav.command.type.GetListingCmd;
import com.pranav.command.type.GetTopCategoryListingCmd;
import com.pranav.command.type.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GetTopCategoryListingCmdService")
public class GetTopCategoryCmdService implements ICommandService {

    @Autowired
    private IListingAdapter listingAdapter;

    @Autowired
    private IUserAdapter userAdapter;

    public void execute(ICommand command){
        if(!(command instanceof GetTopCategoryListingCmd)){
            System.out.println("Get Listing Command Service can only execute on GetTopCategoryListingCmd");
        }
        try {
            GetTopCategoryListingCmd cmd = (GetTopCategoryListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(cmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                GetTopCategoryResponse response =
                        listingAdapter.getListingByTopCategoryByUser(
                                cmd.getUserName());
                if(response.isSuccess()){
                    System.out.println(response.getCategory());
                }else {
                    System.out.println(response.getMessage());
                }
            }else{
                System.out.println("Error - unknown user");
            }

        }catch (Exception ex){
            System.out.println("Error while listing : GetCategoryListingCmdService");
            ex.printStackTrace();
        }
    }
}
