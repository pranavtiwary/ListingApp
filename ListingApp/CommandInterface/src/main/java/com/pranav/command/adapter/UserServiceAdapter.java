package com.pranav.command.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserServiceAdapter{

    @Value("${user.service.url}")
    private String userServiceUrl;

    public void registerUser(String username){
        System.out.println("Going to Register user : "+ username);

    }

    @PostConstruct
    public void init(){
        System.out.println("Connecting to user service at : " + userServiceUrl);
    }

}
