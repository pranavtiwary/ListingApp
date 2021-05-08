package com.pranav.user.controller;

import com.pranav.user.response.CreateUserResponse;
import com.pranav.user.response.GetUserResponse;
import com.pranav.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("/create")
    public CreateUserResponse createUser(@RequestParam(required = true) String uname){
        System.out.println("Received a request to create an user");
        CreateUserResponse response = service.createUser(uname);
        return response;
    }

   @GetMapping("/{uname}")
    public GetUserResponse find(@PathVariable String uname){
        System.out.println("Received a request to create an user");
       GetUserResponse response = service.find(uname);
        return response;
    }
}
