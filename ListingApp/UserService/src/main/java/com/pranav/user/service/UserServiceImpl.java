package com.pranav.user.service;

import com.pranav.user.response.CreateUserResponse;
import com.pranav.user.response.GetUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public CreateUserResponse createUser(final String uname){
        System.out.println("Inside createUser service : " + uname);
        CreateUserResponse  response = null;
        try{
            response = CreateUserResponse.builder()
                    .isSuccess(true)
                    .message("Successfull created")
                    .build();
        }catch (Exception ex){
            System.out.println("Error in creating user");
            ex.printStackTrace();
            response = CreateUserResponse.builder()
                    .isSuccess(false)
                    .message("Error At Server")
                    .build();
        }
        return response;
    }

    @Override
    public GetUserResponse find(String uname) {
        System.out.println("Inside find  user service: " + uname);
        GetUserResponse  response = null;
        try{
            response = GetUserResponse.builder()
                    .isSuccess(true)
                    .message("User is available")
                    .userId(uname)
                    .build();
        }catch (Exception ex){
            System.out.println("Error in creating user");
            ex.printStackTrace();
            response = GetUserResponse.builder()
                    .isSuccess(false)
                    .message("Error At Server")
                    .build();
        }
        return response;
    }
}
