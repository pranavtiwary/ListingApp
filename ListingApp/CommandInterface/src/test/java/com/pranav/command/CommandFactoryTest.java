package com.pranav.command;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.factory.CommandFactory;
import com.pranav.command.type.CreateListingCmd;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommandFactoryTest {

    @Test
    public void testRegisterCommand_Success(){
        String str = "REGISTER pranav";
        ICommand cmd = CommandFactory.createCommand(str);
        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd instanceof RegisterUserCmd);
    }

    @Test
    public void testRegisterCommand_Failure_CMD_DIFF(){
        String str = "REGISTER pranav";
        ICommand cmd = CommandFactory.createCommand(str);
        Assert.assertNotNull(cmd);
        Assert.assertFalse(cmd instanceof CreateListingCmd);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_Failure_NULL(){
        String str = "REGISTER_XX pranav";
        ICommand cmd = CommandFactory.createCommand(str);
    }

}
