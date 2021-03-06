package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.CreateListingResponse;
import com.pranav.command.response.GetListingResponse;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.response.ListingDTO;
import com.pranav.command.type.CreateListingCmd;
import com.pranav.command.type.GetListingCmd;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GetListingCommandService")
public class GetListingCommandService implements ICommandService {

    @Autowired
    private IListingAdapter listingAdapter;

    @Autowired
    private IUserAdapter userAdapter;

    public void execute(ICommand command){
        if(!(command instanceof GetListingCmd)){
            System.out.println("Get Listing Command Service can only execute on GetListingCmd");
            return;
        }
        try {
            GetListingCmd cmd = (GetListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(cmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                GetListingResponse response =
                        listingAdapter.getListingByUserIdAndListingId(
                                cmd.getUserName(), cmd.getListingid());
                if(response.isSuccess() && null!=response.getListings()){
                    for(ListingDTO dto : response.getListings()){
                        StringBuilder sb = new StringBuilder();
                        sb.append(dto.getTitle());
                        sb.append("|");
                        sb.append(dto.getDescription());
                        sb.append("|");
                        sb.append(dto.getPrice());
                        sb.append("|");
                        sb.append(dto.getCreatedOn());
                        sb.append("|");
                        sb.append(dto.getCategory());
                        sb.append("|");
                        sb.append(dto.getUname());
                        System.out.println(sb.toString());
                    }
                }else {
                    System.out.println(response.getMessage());
                }
            }else{
                System.out.println("Error - unknown user");
            }

        }catch (Exception ex){
            System.out.println("Error while listing : GetListingCommandService");
            ex.printStackTrace();
        }
    }
}
