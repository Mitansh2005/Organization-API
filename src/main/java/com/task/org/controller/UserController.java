package com.task.org.controller;

import com.task.org.domain.Users;
import com.task.org.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public void addUser(@RequestBody Users users) {
        userService.addUser(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        System.out.println(users);
        return userService.verify(users);
    }
}
