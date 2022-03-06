package com.tamayodev.springboot.controller;

import com.tamayodev.springboot.caseuse.*;
import com.tamayodev.springboot.entity.User;
import com.tamayodev.springboot.repository.UserRepository;
import org.apache.coyote.Response;
import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    //
    private PageableUser pageableUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser,
                              PageableUser pageableUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        //
        this.pageableUser = pageableUser;
    }

    @GetMapping("/")
    public List<User> get() {
        return getUser.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.update(user, id), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public List<User> getPageableUsers(@RequestParam int page, @RequestParam int size) {
        return pageableUser.getAll(page, size);
    }
}
