package com.pranav.command.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class GetUserResponse extends Response{
    private UserDataDTO data;
}
