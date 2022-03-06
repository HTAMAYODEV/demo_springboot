package com.tamayodev.springboot.caseuse;

import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableUser {
    private UserService userService;

    public PageableUser(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAll(int page, int size) {
        return userService.getAllUsers(page, size);
    }
}
