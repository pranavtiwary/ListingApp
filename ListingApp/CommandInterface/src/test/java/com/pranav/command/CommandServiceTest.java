package com.pranav.command;

import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.response.CreateUserResponse;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.service.CommandFactoryService;
import com.pranav.command.service.ICommandService;
import com.pranav.command.service.RegisterUserCommandService;
import com.pranav.command.type.RegisterUserCmd;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommandServiceTest {

    @Autowired
    @Qualifier("RegisterUserCommandService")
    private ICommandService service;

    @Autowired
    private CommandFactoryService factory;

    @MockBean
    private IUserAdapter adpater;

    @Before
    public void setUp() {
        GetUserResponse response = GetUserResponse.builder()
                .isSuccess(true).build();
        Mockito.when(adpater.getUser(Mockito.anyString()))
                .thenReturn(response);
        CreateUserResponse responseCreate = CreateUserResponse.builder()
                .isSuccess(true).userId("test").build();
        Mockito.when(adpater.registerUser(Mockito.anyString()))
                .thenReturn(responseCreate);
    }

    @Test
    public void testRegsiterUser() {
        RegisterUserCmd cmd = (RegisterUserCmd) factory.createCommand("REGISTER PRANAV");
        service.execute(cmd);
    }
}
