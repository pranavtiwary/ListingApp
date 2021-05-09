package com.pranav.user.response;

import com.pranav.user.model.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class GetUserResponse extends Response{
    private UserData data;
}
