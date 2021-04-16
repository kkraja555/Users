package com.genesys.users.controller;

import com.genesys.users.model.User;
import java.util.*;
import com.genesys.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;


    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

    @PostMapping("/newUser")
    User newUser(@RequestBody User newUser) {
        return service.save(newUser);
    }
    @PostMapping("/validateUser")
    String validate(@RequestBody User loginUser) {
        Optional<User> user = this.list().stream().filter((usr->
                usr.getEmail().equalsIgnoreCase(loginUser.getEmail()))).findAny();
        if (user.isPresent() &&
                user.get().getPassword().equals(loginUser.getPassword())) {
            return "Login Successful";
        } else {
            return "Invalid UserName/Password";
        }
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable int id) {
        Optional<User> user = service.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            logger.error("User not found: {}", id);
            return null;
        }
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable int id) {

        return service.getUserById(id)
                .map(User -> {
                    if (newUser.getName() != null) {
                        User.setName(newUser.getName());
                    }
                    if (newUser.getEmail() != null) {
                        User.setEmail(newUser.getEmail());
                    }
                    if (newUser.getPassword() != null) {
                        User.setPassword(newUser.getPassword());
                    }
                    if (newUser.getLastLoginTime() != null) {
                        User.setLastLoginTime(newUser.getLastLoginTime());
                    }
                    return service.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return service.save(newUser);
                });
    }

    @DeleteMapping("/deleteuser/{id}")
    void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }
}