package com.tamayodev.springboot.caseuse;

import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }


    public User save(User user) {
        return userService.save(user);
    }
}
