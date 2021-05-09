package com.pranav.command;

import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.response.GetUserResponse;
import com.pranav.command.service.ICommandService;
import com.pranav.command.type.RegisterUserCmd;
import org.junit.Assert;
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
public class RegisterUserCmdTest {

    @Autowired
    @Qualifier("RegisterUserCommandService")
    private ICommandService registerCommandService;

    @Test
    public void testRegisterCommand_Success(){
        String str[] = {"REGISTER", "pranav"};
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
        Assert.assertNotNull(cmd);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_FAILURE_ARG(){
        String str[] = {"REGISTER", "pranav", "tiwary"};
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_FAILURE_CMD(){
        String str[] = {"REGISTER_WRONG", "pranav"};
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_FAILURE_NULL(){
        String str[] = null;
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_FAILURE_EMPTY(){
        String str[] = {};
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_FAILURE_SIZE(){
        String str[] = {"REGISTER"};
        RegisterUserCmd cmd = new RegisterUserCmd(str, registerCommandService);
    }
}
