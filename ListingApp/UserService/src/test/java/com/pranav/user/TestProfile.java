package com.pranav.user;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan("com.pranav")
public class TestProfile {

}
