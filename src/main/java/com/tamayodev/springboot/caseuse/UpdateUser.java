package com.tamayodev.springboot.caseuse;

import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }


    public User update(User user, Long id) {
        return userService.update(user, id);
    }
}
