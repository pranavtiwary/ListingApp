package com.pranav.command.service;

import com.pranav.command.adapter.IListingAdapter;
import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.GetListingResponse;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.response.ListingDTO;
import com.pranav.command.type.GetCategoryListingCmd;
import com.pranav.command.type.GetListingCmd;
import com.pranav.command.type.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GetCategoryListingCmdService")
public class GetCategoryListingCmdService implements ICommandService {

    @Autowired
    private IListingAdapter listingAdapter;

    @Autowired
    private IUserAdapter userAdapter;

    public void execute(ICommand command){
        if(!(command instanceof GetCategoryListingCmd)){
            System.out.println("Get Listing Command Service can only execute on GetCategoryListingCmd");
            return;
        }
        try {
            GetCategoryListingCmd cmd = (GetCategoryListingCmd) command;
            GetUserResponse userCheck = userAdapter.getUser(cmd.getUserName());
            if (null != userCheck && null != userCheck.getData()) {
                GetListingResponse response =
                        listingAdapter.getListingByCategory(
                                cmd.getUserName(), cmd.getCategory(), cmd.getSortby(), cmd.getOrder());
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
            System.out.println("Error while listing : GetCategoryListingCmdService");
            ex.printStackTrace();
        }
    }
}
