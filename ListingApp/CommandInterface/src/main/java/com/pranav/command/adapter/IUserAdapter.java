package com.pranav.command.adapter;

import com.pranav.command.response.CreateUserResponse;
import com.pranav.command.response.GetUserResponse;

public interface IUserAdapter {
    CreateUserResponse registerUser(final String username);
    GetUserResponse getUser(final String username);
}
