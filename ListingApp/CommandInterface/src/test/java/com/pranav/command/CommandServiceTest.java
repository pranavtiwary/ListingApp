package com.pranav.command;

import com.pranav.command.service.CommandFactoryService;
import com.pranav.command.service.RegisterCommandService;
import com.pranav.command.type.RegisterUserCmd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommandServiceTest {

    @Autowired
    private RegisterCommandService service;

    @Autowired
    private CommandFactoryService factory;

    @Test
    public void testRegsiterUser() {
        RegisterUserCmd cmd = (RegisterUserCmd) factory.createCommand("REGISTER PRANAV");
        service.execute(cmd);
    }
}
