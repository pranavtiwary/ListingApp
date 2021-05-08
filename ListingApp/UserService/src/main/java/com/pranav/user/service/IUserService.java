package com.pranav.user.service;

import com.pranav.user.response.CreateUserResponse;
import com.pranav.user.response.GetUserResponse;

public interface IUserService {

    CreateUserResponse createUser(final String uname);

    GetUserResponse find(String uname);
}
