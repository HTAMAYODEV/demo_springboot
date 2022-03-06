package com.tamayodev.springboot.service;

import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final Log LOGGER = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOGGER.info("Insert: " + user))
                //.forEach(user -> userRepository.save(user));
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User user, Long id) {
        return userRepository.findById(id).map(
                currUser -> {
                    currUser.setEmail(user.getEmail());
                    currUser.setBirthdate(user.getBirthdate());
                    currUser.setName(user.getName());
                    return userRepository.save(currUser);
                }
        ).get();
    }

    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
