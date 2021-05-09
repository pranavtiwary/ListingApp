package com.pranav.command;

import com.pranav.command.adapter.IUserAdapter;
import com.pranav.command.adapter.UserAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan("com.pranav")
public class TestProfile {

    @Bean
    @Primary
    public IUserAdapter userAdapter(){
        return new UserAdapterImpl();
    }
}
