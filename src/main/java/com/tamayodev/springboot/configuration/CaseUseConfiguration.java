package com.tamayodev.springboot.configuration;

import com.tamayodev.springboot.caseuse.GetUser;
import com.tamayodev.springboot.caseuse.GetUserImpl;
import com.tamayodev.springboot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    public GetUser getUser(UserService userService) {
        return new GetUserImpl(userService);
    }
}
