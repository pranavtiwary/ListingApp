package com.pranav.user.service;

import com.pranav.user.dao.UserDaoRespository;
import com.pranav.user.dao.model.User;
import com.pranav.user.dto.UserDataDTO;
import com.pranav.user.response.CreateUserResponse;
import com.pranav.user.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private static final String USER_ALREADY_PRESENT="Error - user already existing";
    private static final String SUCCESS="Success";

    @Autowired
    private UserDaoRespository respository;

    @Override
    public CreateUserResponse createUser(final String uname){
        System.out.println("Inside createUser service : " + uname);
        CreateUserResponse  response = null;
        try {
            String message = null;
            String userId = null;
            Optional<User> userfromDb = respository.findById(uname);
            if (userfromDb.isPresent()) {
                message = USER_ALREADY_PRESENT;
            } else {
                User user = User.builder().userId(uname).build();
                respository.save(user);
                message = SUCCESS;
                userId = user.getUserId();
            }
            response = CreateUserResponse.builder()
                    .isSuccess(true)
                    .message(message)
                    .userId(userId)
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
            String message = null;
            Optional<User> user = respository.findById(uname);
            UserDataDTO data = null;
            if(user.isPresent()){
                message = "User is available";
                data = UserDataDTO.builder().userId(user.get().getUserId()).build();
            }else{
                message = "User is not available";
            }
            response = GetUserResponse.builder()
                    .isSuccess(true)
                    .message(message)
                    .data(data)
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
