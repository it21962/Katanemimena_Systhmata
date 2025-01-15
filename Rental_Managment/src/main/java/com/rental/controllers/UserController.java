package com.rental.controllers;

import com.rental.Entities.User;
import com.rental.Repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get-user")
    public User getUserByNameAndId(@RequestParam String name, @RequestParam Long id) {
        return userRepository.findByNameAndId(name, id);
    }
}