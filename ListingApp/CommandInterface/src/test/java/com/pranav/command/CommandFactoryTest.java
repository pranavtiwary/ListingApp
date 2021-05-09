package com.pranav.command;

import com.pranav.command.error.CommandNotValidException;
import com.pranav.command.service.CommandFactoryService;
import com.pranav.command.type.CreateListingCmd;
import com.pranav.command.type.ICommand;
import com.pranav.command.type.RegisterUserCmd;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommandFactoryTest {

    @Autowired
    private CommandFactoryService factory ;

    @Test
    public void tetsRegex(){

        List<String> list = new ArrayList<String>();
        String input="CREATE_LISTING user1 'Black shoes' 'Training shoes' 100 'Sports'";
        //"([^\"]\\S*|\".+?\")\\s*"
        Matcher m = Pattern.compile("([^\']\\S*|\'.+?\')\\s*").matcher(input);
        while (m.find()){
            list.add(m.group(1).replace("'", ""));
        }
        Assert.assertTrue(list.size()==6);
    }

    @Test
    public void testRegisterCommand_Success(){
        String str = "REGISTER pranav";
        ICommand cmd = factory.createCommand(str);
        Assert.assertNotNull(cmd);
        Assert.assertTrue(cmd instanceof RegisterUserCmd);
    }

    @Test
    public void testRegisterCommand_Failure_CMD_DIFF(){
        String str = "REGISTER pranav";
        ICommand cmd = factory.createCommand(str);
        Assert.assertNotNull(cmd);
        Assert.assertFalse(cmd instanceof CreateListingCmd);
    }

    @Test(expected = CommandNotValidException.class)
    public void testRegisterCommand_Failure_NULL(){
        String str = "REGISTER_XX pranav";
        ICommand cmd = factory.createCommand(str);
    }

}
