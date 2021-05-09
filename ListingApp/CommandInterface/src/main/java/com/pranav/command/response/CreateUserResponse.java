package com.pranav.command.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class CreateUserResponse extends Response {
    private String userId;
}
