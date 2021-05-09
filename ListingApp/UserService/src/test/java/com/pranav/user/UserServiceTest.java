package com.pranav.user;

import com.pranav.user.response.CreateUserResponse;
import com.pranav.user.response.GetUserResponse;
import com.pranav.user.service.IUserService;
import com.pranav.user.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService service;

    @Test
    public void testCreateUser_Success(){
        CreateUserResponse userCreateResponse = service.createUser("Pranav");
        Assert.assertNotNull(userCreateResponse);
        Assert.assertTrue(userCreateResponse.isSuccess());
        Assert.assertNotNull(userCreateResponse.getUserId());
        Assert.assertEquals("Pranav", userCreateResponse.getUserId());
        GetUserResponse getUserResponse = service.find("pranav");
        Assert.assertNotNull(getUserResponse);
        Assert.assertTrue(getUserResponse.isSuccess());
        Assert.assertNotNull(getUserResponse.getData());
        Assert.assertEquals("Pranav", getUserResponse.getData().getUserId());
    }

    @Test
    public void testCreateUser_FAILUREOnDubplicate(){
        CreateUserResponse userCreateResponse = service.createUser("Pranav1");
        CreateUserResponse userCreateResponse2 = service.createUser("pranav1");
        Assert.assertNotNull(userCreateResponse);
        Assert.assertNotNull(userCreateResponse2);
        Assert.assertTrue(userCreateResponse.isSuccess());
        Assert.assertTrue(userCreateResponse2.isSuccess());
        Assert.assertNotNull(userCreateResponse.getUserId());
        Assert.assertNull(userCreateResponse2.getUserId());
        Assert.assertEquals(UserServiceImpl.SUCCESS, userCreateResponse.getMessage());
        Assert.assertEquals(UserServiceImpl.USER_ALREADY_PRESENT, userCreateResponse2.getMessage());
        Assert.assertEquals("Pranav1", userCreateResponse.getUserId());
        GetUserResponse getUserResponse = service.find("pranav1");
        Assert.assertNotNull(getUserResponse);
        Assert.assertTrue(getUserResponse.isSuccess());
        Assert.assertNotNull(getUserResponse.getData());
        Assert.assertEquals("Pranav1", getUserResponse.getData().getUserId());
    }
}
